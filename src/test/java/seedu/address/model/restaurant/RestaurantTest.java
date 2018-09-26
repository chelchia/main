package seedu.address.model.restaurant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalRestaurants.ALICE;
import static seedu.address.testutil.TypicalRestaurants.BOB;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.RestaurantBuilder;

public class RestaurantTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Restaurant restaurant = new RestaurantBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        restaurant.getTags().remove(0);
    }

    @Test
    public void isSameRestaurant() {
        // same object -> returns true
        assertTrue(ALICE.isSameRestaurant(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameRestaurant(null));

        // different phone and email -> returns false
        Restaurant editedAlice = new RestaurantBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameRestaurant(editedAlice));

        // different name -> returns false
        editedAlice = new RestaurantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameRestaurant(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new RestaurantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRestaurant(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new RestaurantBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRestaurant(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new RestaurantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRestaurant(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Restaurant aliceCopy = new RestaurantBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different restaurant -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Restaurant editedAlice = new RestaurantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new RestaurantBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new RestaurantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new RestaurantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new RestaurantBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
