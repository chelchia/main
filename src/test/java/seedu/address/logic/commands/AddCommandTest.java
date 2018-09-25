package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.testutil.RestaurantBuilder;

public class AddCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullRestaurant_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddCommand(null);
    }

    @Test
    public void execute_restaurantAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRestaurantAdded modelStub = new ModelStubAcceptingRestaurantAdded();
        Restaurant validRestaurant = new RestaurantBuilder().build();

        CommandResult commandResult = new AddCommand(validRestaurant).execute(modelStub, commandHistory);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validRestaurant), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validRestaurant), modelStub.restaurantsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateRestaurant_throwsCommandException() throws Exception {
        Restaurant validRestaurant = new RestaurantBuilder().build();
        AddCommand addCommand = new AddCommand(validRestaurant);
        ModelStub modelStub = new ModelStubWithRestaurant(validRestaurant);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_PERSON);
        addCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Restaurant alice = new RestaurantBuilder().withName("Alice").build();
        Restaurant bob = new RestaurantBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different restaurant -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addRestaurant(Restaurant restaurant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRestaurant(Restaurant restaurant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRestaurant(Restaurant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateRestaurant(Restaurant target, Restaurant editedRestaurant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Restaurant> getFilteredRestaurantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRestaurantList(Predicate<Restaurant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single restaurant.
     */
    private class ModelStubWithRestaurant extends ModelStub {
        private final Restaurant restaurant;

        ModelStubWithRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            this.restaurant = restaurant;
        }

        @Override
        public boolean hasRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            return this.restaurant.isSameRestaurant(restaurant);
        }
    }

    /**
     * A Model stub that always accept the restaurant being added.
     */
    private class ModelStubAcceptingRestaurantAdded extends ModelStub {
        final ArrayList<Restaurant> restaurantsAdded = new ArrayList<>();

        @Override
        public boolean hasRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            return restaurantsAdded.stream().anyMatch(restaurant::isSameRestaurant);
        }

        @Override
        public void addRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            restaurantsAdded.add(restaurant);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
