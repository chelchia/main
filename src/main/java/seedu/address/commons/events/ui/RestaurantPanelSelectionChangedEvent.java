package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.restaurant.Restaurant;

/**
 * Represents a selection change in the Restaurant List Panel
 */
public class RestaurantPanelSelectionChangedEvent extends BaseEvent {


    private final Restaurant newSelection;

    public RestaurantPanelSelectionChangedEvent(Restaurant newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Restaurant getNewSelection() {
        return newSelection;
    }
}
