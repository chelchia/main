package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.restaurant.Restaurant;

/**
 * Provides a handle for {@code RestaurantListPanel} containing the list of {@code RestaurantCard}.
 */
public class RestaurantListPanelHandle extends NodeHandle<ListView<Restaurant>> {
    public static final String PERSON_LIST_VIEW_ID = "#restaurantListView";

    private static final String CARD_PANE_ID = "#cardPane";

    private Optional<Restaurant> lastRememberedSelectedRestaurantCard;

    public RestaurantListPanelHandle(ListView<Restaurant> restaurantListPanelNode) {
        super(restaurantListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code RestaurantCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public RestaurantCardHandle getHandleToSelectedCard() {
        List<Restaurant> selectedRestaurantList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedRestaurantList.size() != 1) {
            throw new AssertionError("Restaurant list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(RestaurantCardHandle::new)
                .filter(handle -> handle.equals(selectedRestaurantList.get(0)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<Restaurant> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code restaurant}.
     */
    public void navigateToCard(Restaurant restaurant) {
        if (!getRootNode().getItems().contains(restaurant)) {
            throw new IllegalArgumentException("Restaurant does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(restaurant);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= getRootNode().getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code RestaurantCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Returns the restaurant card handle of a restaurant associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public RestaurantCardHandle getRestaurantCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(RestaurantCardHandle::new)
                .filter(handle -> handle.equals(getRestaurant(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Restaurant getRestaurant(int index) {
        return getRootNode().getItems().get(index);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Remembers the selected {@code RestaurantCard} in the list.
     */
    public void rememberSelectedRestaurantCard() {
        List<Restaurant> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedRestaurantCard = Optional.empty();
        } else {
            lastRememberedSelectedRestaurantCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code RestaurantCard} is different from the value remembered by the most recent
     * {@code rememberSelectedRestaurantCard()} call.
     */
    public boolean isSelectedRestaurantCardChanged() {
        List<Restaurant> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedRestaurantCard.isPresent();
        } else {
            return !lastRememberedSelectedRestaurantCard.isPresent()
                    || !lastRememberedSelectedRestaurantCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
