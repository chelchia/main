package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.restaurant.Restaurant;

/**
 * Represents a selection change in the Restaurant List Panel
 */
public class RestaurantPanelSelectionChangedEvent<T> extends BaseEvent {


    private final T newSelection;

    public RestaurantPanelSelectionChangedEvent(T newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public T getNewSelection() {
        return newSelection;
    }
}
