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
import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.DebtId;
import seedu.address.model.accounting.DebtStatus;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.model.timetable.Date;
import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
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
        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_RESTAURANT);
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

        @Override
        public boolean hasDebtId(DebtId debtId) {
            return false;
        }

        @Override
        public boolean matchDebtToAmount(DebtId debtId, Amount amount) {
            return false;
        }

        @Override
        public boolean matchDebtToUser(DebtId debtId, Username user) {
            return false;
        }

        @Override
        public boolean matchDebtToStatus(DebtId debtId, DebtStatus status) {
            return false;
        }
        @Override
        public void addDebt(Username debtorUsername, Amount amount) {
        }
        @Override
        public void clearDebt(Username debtorUsername, Amount amount, DebtId debtId) {
        }
        @Override
        public void acceptedDebtRequest(Username creditorUsername, Amount amount, DebtId debtId) {
        }
        @Override
        public void deleteDebtRequest(Username creditorUsername, Amount amount, DebtId debtId) {
        }

        @Override
        public String listDebtHistory() {
            return null;
        }

        @Override
        public String listDebtor() {
            return null;
        }

        @Override
        public String listCreditor() {
            return null;
        }

        @Override
        public String listDebtRequestReceived() {
            return null;
        }

        @Override
        public String listDebtRequestSent() {
            return null;
        }


        // To be Done Later, fake tests for now

        @Override
        public ObservableList<Jio> getJioList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasJio(Jio jio) {
            return true;
        }

        @Override
        public boolean hasJioName(seedu.address.model.user.Name jioName) {
            return true;
        }

        @Override
        public void removeJioOfName(seedu.address.model.user.Name jioName) {}

        @Override
        public void createJio(Jio jio) {}

        @Override
        public boolean isCurrentUserInJioOfName(seedu.address.model.user.Name jioName) {
            return true;
        }

        @Override
        public void addCurrentUserToJioOfName(seedu.address.model.user.Name jioName) {}

        @Override
        public void addUser(User user) {}

        @Override
        public boolean hasUser(Username username) {
            return true;
        }

        @Override
        public boolean verifyLogin(Username username, Password password) {
            return true;
        }

        @Override
        public boolean isCurrentlyLoggedIn() {
            return true;
        }

        @Override
        public void loginUser(User user){}

        @Override
        public void loginUser(Username username){}

        @Override
        public void logoutUser() {}

        @Override
        public boolean hasUsernameSentRequest(Username friendUsername) {
            return true;
        }

        @Override
        public boolean hasUsernameFriendRequest(Username friendusername) {
            return true;
        }

        @Override
        public boolean hasUsernameFriend(Username friendUsername) {
            return true;
        }

        @Override
        public void addFriend(Username friendUsername) {}

        @Override
        public void acceptFriend(Username friendUsername) {}

        @Override
        public boolean isSameAsCurrentUser(Username username) {
            return true;
        }

        @Override
        public void deleteFriend(Username friendUsername) {}

        @Override
        public void deleteFriendRequest(Username friendUsername) {}

        // ========= timetable commands =========

        @Override
        public void blockDateForCurrentUser(Date date) {}

        @Override
        public void freeDateForCurrentUser(Date date) {}

        @Override
        public boolean hasDateForCurrentUser(Date date) {
            return true;
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
