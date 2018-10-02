package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.restaurant.Restaurant;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook<E> {

    /**
     * Returns an unmodifiable view of the restaurants list.
     * This list will not contain any duplicate restaurants.
     */
    ObservableList<E> getDataList();

}
