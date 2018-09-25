package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalRestaurants.getTypicalRestaurants;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysRestaurant;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import guitests.guihandles.RestaurantCardHandle;
import guitests.guihandles.RestaurantListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.storage.XmlSerializableAddressBook;

public class RestaurantListPanelTest extends GuiUnitTest {
    private static final ObservableList<Restaurant> TYPICAL_PERSONS =
            FXCollections.observableList(getTypicalRestaurants());

    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND_PERSON);

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "sandbox");

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private RestaurantListPanelHandle restaurantListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_PERSONS);

        for (int i = 0; i < TYPICAL_PERSONS.size(); i++) {
            restaurantListPanelHandle.navigateToCard(TYPICAL_PERSONS.get(i));
            Restaurant expectedRestaurant = TYPICAL_PERSONS.get(i);
            RestaurantCardHandle actualCard = restaurantListPanelHandle.getRestaurantCardHandle(i);

            assertCardDisplaysRestaurant(expectedRestaurant, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void handleJumpToListRequestEvent() {
        initUi(TYPICAL_PERSONS);
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        RestaurantCardHandle expectedRestaurant = restaurantListPanelHandle.getRestaurantCardHandle(INDEX_SECOND_PERSON.getZeroBased());
        RestaurantCardHandle selectedRestaurant = restaurantListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedRestaurant, selectedRestaurant);
    }

    /**
     * Verifies that creating and deleting large number of restaurants in {@code RestaurantListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() throws Exception {
        ObservableList<Restaurant> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of restaurant cards exceeded time limit");
    }

    /**
     * Returns a list of restaurants containing {@code restaurantCount} restaurants that is used to populate the
     * {@code RestaurantListPanel}.
     */
    private ObservableList<Restaurant> createBackingList(int restaurantCount) throws Exception {
        Path xmlFile = createXmlFileWithRestaurants(restaurantCount);
        XmlSerializableAddressBook xmlAddressBook =
                XmlUtil.getDataFromFile(xmlFile, XmlSerializableAddressBook.class);
        return FXCollections.observableArrayList(xmlAddressBook.toModelType().getRestaurantList());
    }

    /**
     * Returns a .xml file containing {@code restaurantCount} restaurants. This file will be deleted when the JVM terminates.
     */
    private Path createXmlFileWithRestaurants(int restaurantCount) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        builder.append("<addressbook>\n");
        for (int i = 0; i < restaurantCount; i++) {
            builder.append("<restaurants>\n");
            builder.append("<name>").append(i).append("a</name>\n");
            builder.append("<phone>000</phone>\n");
            builder.append("<email>a@aa</email>\n");
            builder.append("<address>a</address>\n");
            builder.append("</restaurants>\n");
        }
        builder.append("</addressbook>\n");

        Path manyRestaurantsFile = Paths.get(TEST_DATA_FOLDER + "manyRestaurants.xml");
        FileUtil.createFile(manyRestaurantsFile);
        FileUtil.writeToFile(manyRestaurantsFile, builder.toString());
        manyRestaurantsFile.toFile().deleteOnExit();
        return manyRestaurantsFile;
    }

    /**
     * Initializes {@code restaurantListPanelHandle} with a {@code RestaurantListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code RestaurantListPanel}.
     */
    private void initUi(ObservableList<Restaurant> backingList) {
        RestaurantListPanel restaurantListPanel = new RestaurantListPanel(backingList);
        uiPartRule.setUiPart(restaurantListPanel);

        restaurantListPanelHandle = new RestaurantListPanelHandle(getChildNode(restaurantListPanel.getRoot(),
                RestaurantListPanelHandle.PERSON_LIST_VIEW_ID));
    }
}
