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
import seedu.address.model.restaurant.Restaurant;

/**
 * Panel containing the list of restaurants.
 */
public class RestaurantListPanel extends UiPart<Region> {
    private static final String FXML = "RestaurantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RestaurantListPanel.class);

    @FXML
    private ListView<Restaurant> restaurantListView;

    public RestaurantListPanel(ObservableList<Restaurant> restaurantList) {
        super(FXML);
        setConnections(restaurantList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Restaurant> restaurantList) {
        restaurantListView.setItems(restaurantList);
        restaurantListView.setCellFactory(listView -> new RestaurantListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        restaurantListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in restaurant list panel changed to : '" + newValue + "'");
                        raise(new RestaurantPanelSelectionChangedEvent(newValue));
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
    class RestaurantListViewCell extends ListCell<Restaurant> {
        @Override
        protected void updateItem(Restaurant restaurant, boolean empty) {
            super.updateItem(restaurant, empty);

            if (empty || restaurant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RestaurantCard(restaurant, getIndex() + 1).getRoot());
            }
        }
    }

}
