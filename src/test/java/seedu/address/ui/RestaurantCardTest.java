package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysRestaurant;

import org.junit.Test;

import guitests.guihandles.RestaurantCardHandle;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.testutil.RestaurantBuilder;

public class RestaurantCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Restaurant restaurantWithNoTags = new RestaurantBuilder().withTags(new String[0]).build();
        RestaurantCard restaurantCard = new RestaurantCard(restaurantWithNoTags, 1);
        uiPartRule.setUiPart(restaurantCard);
        assertCardDisplay(restaurantCard, restaurantWithNoTags, 1);

        // with tags
        Restaurant restaurantWithTags = new RestaurantBuilder().build();
        restaurantCard = new RestaurantCard(restaurantWithTags, 2);
        uiPartRule.setUiPart(restaurantCard);
        assertCardDisplay(restaurantCard, restaurantWithTags, 2);
    }

    @Test
    public void equals() {
        Restaurant restaurant = new RestaurantBuilder().build();
        RestaurantCard restaurantCard = new RestaurantCard(restaurant, 0);

        // same restaurant, same index -> returns true
        RestaurantCard copy = new RestaurantCard(restaurant, 0);
        assertTrue(restaurantCard.equals(copy));

        // same object -> returns true
        assertTrue(restaurantCard.equals(restaurantCard));

        // null -> returns false
        assertFalse(restaurantCard.equals(null));

        // different types -> returns false
        assertFalse(restaurantCard.equals(0));

        // different restaurant, same index -> returns false
        Restaurant differentRestaurant = new RestaurantBuilder().withName("differentName").build();
        assertFalse(restaurantCard.equals(new RestaurantCard(differentRestaurant, 0)));

        // same restaurant, different index -> returns false
        assertFalse(restaurantCard.equals(new RestaurantCard(restaurant, 1)));
    }

    /**
     * Asserts that {@code restaurantCard} displays the details of {@code expectedRestaurant} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(RestaurantCard restaurantCard, Restaurant expectedRestaurant, int expectedId) {
        guiRobot.pauseForHuman();

        RestaurantCardHandle restaurantCardHandle = new RestaurantCardHandle(restaurantCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", restaurantCardHandle.getId());

        // verify restaurant details are displayed correctly
        assertCardDisplaysRestaurant(expectedRestaurant, restaurantCardHandle);
    }
}
