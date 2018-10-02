package seedu.address.model.jio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.model.restaurant.exceptions.DuplicateRestaurantException;
import seedu.address.model.restaurant.exceptions.RestaurantNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of restaurants that enforces uniqueness between its elements and does not allow nulls.
 * A restaurant is considered unique by comparing using
 * {@code Restaurant#isSameRestaurant(Restaurant)}. As such, adding and updating of
 * restaurants uses Restaurant#isSameRestaurant(Restaurant)
 * for equality so as to ensure that the restaurant being added or updated is
 * unique in terms of identity in the UniqueRestaurantList.
 * However, the removal of a restaurant uses Restaurant#equals(Object) so
 * as to ensure that the restaurant with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Restaurant#isSameRestaurant(Restaurant)
 */
public class UniqueList<E> implements Iterable<E> {

    private final ObservableList<E> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent restaurant as the given argument.
     */
    public boolean contains(E toCheck) {
        requireNonNull(toCheck);
//        return internalList.stream().anyMatch(toCheck::isSameRestaurant);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a restaurant to the list.
     * The restaurant must not already exist in the list.
     */
    public void add(E toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRestaurantException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the restaurant {@code target} in the list with {@code editedRestaurant}.
     * {@code target} must exist in the list.
     * The restaurant identity of {@code editedRestaurant}
     * must not be the same as another existing restaurant in the list.
     */
    public void setObject(E target, E editedObject) {
        requireAllNonNull(target, editedObject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RestaurantNotFoundException();
        }

//        if (!target.isSameRestaurant(editedObject) && contains(editedObject)) {
        if (!target.equals(editedObject) && contains(editedObject)) {
            throw new DuplicateRestaurantException();
        }

        internalList.set(index, editedObject);
    }

    /**
     * Removes the equivalent restaurant from the list.
     * The restaurant must exist in the list.
     */
    public void remove(E toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RestaurantNotFoundException();
        }
    }

    public void setObjects(seedu.address.model.jio.UniqueList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code restaurants}.
     * {@code restaurants} must not contain duplicate restaurants.
     */
    public void setObjects(List<E> objects) {
        requireAllNonNull(objects);
        if (!objectsAreUnique(objects)) {
            throw new DuplicateRestaurantException();
        }

        internalList.setAll(objects);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<E> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<E> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.restaurant.UniqueRestaurantList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.jio.UniqueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code restaurants} contains only unique restaurants.
     */
    private boolean objectsAreUnique(List<E> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            for (int j = i + 1; j < restaurants.size(); j++) {
//                if (restaurants.get(i).isSameRestaurant(restaurants.get(j))) {
                if (restaurants.get(i).equals(restaurants.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}


