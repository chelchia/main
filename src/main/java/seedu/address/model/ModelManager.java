package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.UserDataChangedEvent;
import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.DebtId;
import seedu.address.model.accounting.DebtStatus;
import seedu.address.model.group.Friendship;
import seedu.address.model.group.Group;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.model.timetable.Date;
import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final FilteredList<Restaurant> filteredRestaurants;
    private UserData userData;
    private boolean isLoggedIn = false;
    private User currentUser = null;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        filteredRestaurants = new FilteredList<>(versionedAddressBook.getRestaurantList());
    }

    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs,
                        UserData userData) {
        super();
        requireAllNonNull(addressBook, userPrefs, userData);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        filteredRestaurants = new FilteredList<>(versionedAddressBook.getRestaurantList());
        this.userData = userData;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== Model Manager Miscellaneous Methods =+==========================================================

    public boolean isCurrentlyLoggedIn() {
        return this.isLoggedIn;
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        versionedAddressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(versionedAddressBook));
    }

    /** Raises an event to indicate the model has changed */
    private void indicateUserDataChanged() {
        raise(new UserDataChangedEvent(userData));
    }

    //=========== Model Manager Restaurants Methods =+============================================================

    @Override
    public boolean hasRestaurant(Restaurant restaurant) {
        requireNonNull(restaurant);
        return versionedAddressBook.hasRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(Restaurant target) {
        versionedAddressBook.removeRestaurant(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        versionedAddressBook.addRestaurant(restaurant);
        updateFilteredRestaurantList(PREDICATE_SHOW_ALL_RESTAURANTS);
        indicateAddressBookChanged();
    }

    @Override
    public void updateRestaurant(Restaurant target, Restaurant editedRestaurant) {
        requireAllNonNull(target, editedRestaurant);

        versionedAddressBook.updateRestaurant(target, editedRestaurant);
        indicateAddressBookChanged();
    }

    //=========== Filtered Restaurant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Restaurant} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Restaurant> getFilteredRestaurantList() {
        return FXCollections.unmodifiableObservableList(filteredRestaurants);
    }

    @Override
    public void updateFilteredRestaurantList(Predicate<Restaurant> predicate) {
        requireNonNull(predicate);
        filteredRestaurants.setPredicate(predicate);
    }

    //=========== Model Manager User Methods ====================================================================

    @Override
    public boolean hasUser(Username username) {
        requireNonNull(username);
        return userData.hasUser(username);
    }

    @Override
    public boolean verifyLogin(Username username, Password password) {
        requireAllNonNull(username);
        requireAllNonNull(password);
        return userData.verifyLogin(username, password);
    }

    @Override
    public void addUser(User user) {
        userData.addUser(user);
        indicateUserDataChanged();
    }

    @Override
    public void loginUser(User user) {
        requireAllNonNull(user);
        this.currentUser = user;
        this.isLoggedIn = true;
    }

    @Override
    public void loginUser(Username username) {
        requireAllNonNull(username);
        User userToLogin = userData.getUsernameUserHashMap().get(username);
        requireAllNonNull(userToLogin);
        this.currentUser = userToLogin;
        this.isLoggedIn = true;
    }

    @Override
    public void logoutUser() {
        this.currentUser = null;
        this.isLoggedIn = false;
    }

    @Override
   public boolean hasUsernameSentRequest(Username friendUsername) {
        User friend = userData.getUser(friendUsername);
        Username myUsername = currentUser.getUsername();
        List<Friendship> friendRequestLists = friend.getFriendRequests();
        for (Friendship f: friendRequestLists) {
            if (f.getFriendUsername().equals(myUsername)) {
                return true;
            }
        }
        return false;
    }

    /**
    * Returns whether there is a debtId
    */
    public boolean hasDebtId(DebtId debtId) {
        boolean result = false;
        for (int i = 0; i < currentUser.getDebts().size(); i++) {
            if (currentUser.getDebts().get(i).getDebtId().equals(debtId)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean matchDebtToAmount(DebtId debtId, Amount amount) {
        int count = 0;
        boolean check = false;
        for (int i = 0; i < currentUser.getDebts().size(); i++) {
            if (currentUser.getDebts().get(i).getDebtId().equals(debtId)) {
                count = i;
                check = true;
                break;
            }
        }
        if (check) {
            if (currentUser.getDebts().get(count).getAmount().equals(amount)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean matchDebtToUser(DebtId debtId, Username user) {
        int count = 0;
        boolean check = false;
        for (int i = 0; i < currentUser.getDebts().size(); i++) {
            if (currentUser.getDebts().get(i).getDebtId().equals(debtId)) {
                count = i;
                check = true;
                break;
            }
        }
        if (check) {
            if (currentUser.getDebts().get(count).getDebtor().getUsername().equals(user)
                    || currentUser.getDebts().get(count).getCreditor().getUsername().equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasUsernameFriendRequest(Username friendusername) {
        List<Friendship> friendRequestsLists = currentUser.getFriendRequests();
        for (Friendship f: friendRequestsLists) {
            if (f.getFriendUsername().equals(friendusername)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean matchDebtToStatus(DebtId debtId, DebtStatus status) {
        int count = 0;
        boolean check = false;
        for (int i = 0; i < currentUser.getDebts().size(); i++) {
            if (currentUser.getDebts().get(i).getDebtId().equals(debtId)) {
                count = i;
                check = true;
                break;
            }
        }
        if (check) {
            if (currentUser.getDebts().get(count).getDebtStatus() == status) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addDebt(Username debtorUsername, Amount amount) {
        User debtor = userData.getUser(debtorUsername);
        currentUser.addDebt(debtor, amount);
        indicateUserDataChanged();

    }

    @Override
    public void clearDebt(Username debtorUsername, Amount amount, DebtId debtId) {
        User debtor = userData.getUser(debtorUsername);
        currentUser.clearDebt(debtor, amount, debtId);
        indicateUserDataChanged();
    }

    @Override
    public void acceptedDebtRequest(Username creditorUsername, Amount amount, DebtId debtId) {
        User creditor = userData.getUser(creditorUsername);
        currentUser.acceptedDebtRequest(creditor, amount, debtId);
        indicateUserDataChanged();
    }

    @Override
    public boolean hasUsernameFriend(Username friendUsername) {
        List<Friendship> friendsLists = currentUser.getFriends();
        for (Friendship f: friendsLists) {
            if (f.getFriendUsername().equals(friendUsername)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFriend(Username friendUsername) {
        User friendUser = userData.getUser(friendUsername);
        currentUser.addFriend(friendUser);
        indicateUserDataChanged();
    }

    @Override
    public void acceptFriend(Username friendUsername) {
        User friendUser = userData.getUser(friendUsername);
        currentUser.acceptFriendRequest(friendUser);
        indicateUserDataChanged();
    }

    @Override
    public boolean isSameAsCurrentUser(Username username) {
        User toCheck = userData.getUser(username);
        return toCheck.equals(currentUser);
    }

    @Override
    public void deleteFriend(Username friendUsername) {
        User friendUser = userData.getUser(friendUsername);
        currentUser.deleteFriend(friendUser);
        indicateUserDataChanged();
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        String groupName = group.getGroupName();
        return userData.hasGroup(groupName);
    }

    @Override
    public void addGroup(Group group) {
        userData.addGroup(group);
        group.addCreator(currentUser);
        indicateUserDataChanged();
    }

    @Override
    public boolean hasGroupRequest(Name groupName) {
        List<Group> listGroups = currentUser.getGroupRequests();
        for(Group g: listGroups) {
            if(groupName.toString().equals(g.getGroupName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInGroup(Group group) {
        currentUser.getGroups().forEach(grouper -> System.out.println(grouper.getGroupName()));
        System.out.println("finished groups");
        List<Group> listGroups = currentUser.getGroups();
        for(Group g: listGroups) {
            if(group.equals(g)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void acceptGroupRequest(Name groupName) {
        Group group = userData.getGroupHashmap().get(groupName.toString());
        currentUser.acceptGroupRequest(group);
        indicateUserDataChanged();
    }

    @Override
    public void addPendingUsersGroup(Group group) {
        List<Username> listUsernames = group.getPendingUsernames();
        String groupName = group.getGroupName();
        Group toAddto = userData.getGroupHashmap().get(groupName);
        List<User> listUsers = new ArrayList<>();
        listUsernames.forEach(username -> listUsers.add(userData.getUser(username)));
        toAddto.addMembers(listUsers);
        indicateUserDataChanged();
    }

    @Override
    public boolean isAllValidUsers(Group group) {
        List<Username> listUsernames = group.getPendingUsernames();
        for(Username u: listUsernames) {
            if(!userData.hasUser(u)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasUsersInGroup(Group group) {
        List<Username> listUsernames = group.getPendingUsernames();
        String groupName = group.getGroupName();
        Group originalGroup = userData.getGroupHashmap().get(groupName);
        List<User> acceptedUsers = originalGroup.getAcceptedUsers();

        // checking to see if any Users are already in the list of acceptedUsers
        return acceptedUsers.stream().anyMatch(
                accUser -> listUsernames.stream().anyMatch(
                        user -> accUser.getUsername().equals(user)));
    }

    @Override
    public boolean hasRequestForUsers(Group group) {
        List<Username> listUsernames = group.getPendingUsernames();
        String groupName = group.getGroupName();
        Group originalGroup = userData.getGroupHashmap().get(groupName);
        List<User> pendingUsers = originalGroup.getPendingUsers();

        // checking to see if any Users are already in the list of pendingUsers
        return pendingUsers.stream().anyMatch(
                accUser -> listUsernames.stream().anyMatch(
                        user -> accUser.getUsername().equals(user)));
    }

    @Override
    public void deleteDebtRequest(Username creditorUsername, Amount amount, DebtId debtId) {
        User creditor = userData.getUser(creditorUsername);
        currentUser.deleteDebtRequest(creditor, amount, debtId);
        indicateUserDataChanged();
    }

    @Override
    public String listDebtHistory() {
        return currentUser.listDebtHistory();
    }

    @Override
    public String listDebtor() {
        return currentUser.listDebtor();
    }

    @Override
    public String listCreditor() {
        return currentUser.listCreditor();
    }

    @Override
    public String listDebtRequestReceived() {
        return currentUser.listDebtRequestReceived();
    }

    @Override
    public String listDebtRequestSent() {
        return currentUser.listDebtRequestSent();
    }

    @Override
    public void deleteFriendRequest(Username friendUsername) {
        User friendUser = userData.getUser(friendUsername);
        currentUser.deleteFriendRequest(friendUser);
        indicateUserDataChanged();
    }

    // =================== Timetable methods ===============================

    @Override
    public void blockDateForCurrentUser(Date date) {
        requireNonNull(date);
        currentUser.blockDateOnSchedule(date);
        indicateUserDataChanged();
    }

    @Override
    public void freeDateForCurrentUser(Date date) {
        requireNonNull(date);
        currentUser.freeDateOnSchedule(date);
        indicateUserDataChanged();
    }

    @Override
    public boolean hasDateForCurrentUser(Date date) {
        requireNonNull(date);
        return currentUser.hasDateOnSchedule(date);
    }

    //=========== Jio methods ===============================================================================

    @Override
    public boolean hasJio(Jio jio) {
        requireNonNull(jio);
        return userData.hasJio(jio);
    }

    @Override
    public boolean hasJioName(Name jioName) {
        requireNonNull(jioName);
        return userData.hasJioName(jioName);
    }

    @Override
    public void removeJioOfName(Name jioName) {
        requireNonNull(jioName);
        userData.removeJioOfName(jioName);
        indicateUserDataChanged();
    }

    @Override
    public void createJio(Jio jio) {
        requireNonNull(jio);
        jio.addUser(currentUser);
        userData.addJio(jio);
        updateFilteredRestaurantList(PREDICATE_SHOW_ALL_RESTAURANTS);
        indicateUserDataChanged();
    }

    @Override
    public boolean isCurrentUserInJioOfName(Name jioName) {
        requireNonNull(jioName);
        return userData.isCurrentUserInJioOfName(jioName, currentUser);
    }

    @Override
    public void addCurrentUserToJioOfName(Name jioName) {
        requireNonNull(jioName);
        userData.addUserToJioOfName(jioName, currentUser);
        indicateUserDataChanged();
    }

    //=========== Undo/Redo/Commit ===============================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && filteredRestaurants.equals(other.filteredRestaurants);
    }

}
