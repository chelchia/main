package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.jio.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameRestaurant comparison)
 */
public class AddressBook<E> implements ReadOnlyAddressBook<E> {

    private final UniqueList<E> data;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        data = new UniqueList<E>();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Restaurants in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the restaurant list with {@code data}.
     * {@code data} must not contain duplicate data.
     */
    public void setObjects(List<E> data) {
        this.data.setObjects(data);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setObjects(newData.getDataList());
    }

    //// restaurant-level operations

    /**
     * Returns true if a restaurant with the same identity as {@code restaurant} exists in the address book.
     */
    public boolean hasObject(E object) {
        requireNonNull(object);
        return data.contains(object);
    }

    /**
     * Adds a restaurant to the address book.
     * The restaurant must not already exist in the address book.
     */
    public void addRestaurant(E p) {
        data.add(p);
    }

    /**
     * Replaces the given restaurant {@code target} in the list with {@code editedRestaurant}.
     * {@code target} must exist in the address book.
     * The restaurant identity of {@code editedRestaurant} must not be the
     * same as another existing restaurant in the address book.
     */
    public void updateRestaurant(E target, E editedRestaurant) {
        requireNonNull(editedRestaurant);

        data.setObject(target, editedRestaurant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeRestaurant(E key) {
        data.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return data.asUnmodifiableObservableList().size() + " data";
        // TODO: refine later
    }

    @Override
    public ObservableList<E> getDataList() {
        return data.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && data.equals(((AddressBook) other).data));
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
