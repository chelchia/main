package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JioPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.jio.Jio;

import java.util.logging.Logger;

/**
 * Panel containing the list of restaurants.
 */
public class JioListPanel extends UiPart<Region> {
    private static final String FXML = "RestaurantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);

    @javafx.fxml.FXML
    private ListView<Jio> jioListView;

    public JioListPanel(ObservableList<Jio> jioList) {
        super(FXML);
        setConnections(jioList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Jio> jioList) {
        jioListView.setItems(jioList);
        jioListView.setCellFactory(jioListView -> new JioListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        jioListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in restaurant list panel changed to : '" + newValue + "'");
                        raise(new JioPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code RestaurantCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            jioListView.scrollTo(index);
            jioListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Restaurant} using a {@code RestaurantCard}.
     */
    class JioListViewCell extends ListCell<Jio> {
        @Override
        protected void updateItem(Jio jio, boolean empty) {
            super.updateItem(jio, empty);

            if (empty || jio == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JioCard(jio, getIndex() + 1).getRoot());
            }
        }
    }

}


