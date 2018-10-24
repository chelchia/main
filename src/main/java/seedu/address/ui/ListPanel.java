package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.RestaurantPanelSelectionChangedEvent;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;

/**
 * Panel containing the list of restaurants.
 */
public class ListPanel<T> extends UiPart<Region> {
    private static final String FXML = "RestaurantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);

    @FXML
    private ListView<T> restaurantListView;

    public ListPanel(ObservableList<T> restaurantList) {
        super(FXML);
        setConnections(restaurantList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<T> restaurantList) {
        restaurantListView.setItems(restaurantList);
        restaurantListView.setCellFactory(listView -> new RestaurantListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        restaurantListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in restaurant list panel changed to : '" + newValue + "'");
                        raise(new RestaurantPanelSelectionChangedEvent<>(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code RestaurantCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            restaurantListView.scrollTo(index);
            restaurantListView.getSelectionModel().clearAndSelect(index);
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
    class RestaurantListViewCell extends ListCell<T> {
        @Override
        protected void updateItem(T restaurant, boolean empty) {
            super.updateItem(restaurant, empty);

            if (empty || restaurant == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (restaurant instanceof Restaurant) {
                    setGraphic(new RestaurantCard((Restaurant) restaurant, getIndex() + 1).getRoot());
                }
                if (restaurant instanceof Jio) {
                    setGraphic(new JioCard((Jio) restaurant, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
