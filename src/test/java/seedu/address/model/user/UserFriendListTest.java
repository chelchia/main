package seedu.address.model.user;

import org.junit.Assert;
import org.junit.Test;

import seedu.address.model.restaurant.Address;
import seedu.address.model.restaurant.Email;
import seedu.address.model.restaurant.Name;
import seedu.address.model.restaurant.Phone;

public class UserFriendListTest {
    @Test
    public void listFriendRequestsTest() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "Alice\n");
    }

    @Test
    public void multipleFriendRequests() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        User carol = new User(new Name("Carol"), new Phone("8942"), new Email("carol@g.com"), new Address("6 Baker"));
        bob.addFriend(alice);
        carol.addFriend(alice);
        Assert.assertEquals(alice.listFriendRequests() , "Bob\nCarol\n");
        Assert.assertEquals(bob.listFriendRequests() , "");
        Assert.assertEquals(carol.listFriendRequests() , "");
        Assert.assertEquals(alice.listFriends(), "");
        Assert.assertEquals(bob.listFriends(), "");
    }

    @Test
    public void listFriends() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        bob.acceptFriendRequest(new Name("Alice"));
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "");
        Assert.assertEquals(alice.listFriends(), "Bob\n");
        Assert.assertEquals(bob.listFriends(), "Alice\n");
    }

    @Test
    public void listMultipleFriends() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        User carol = new User(new Name("Carol"), new Phone("8942"), new Email("carol@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        alice.addFriend(carol);
        bob.acceptFriendRequest(new Name("Alice"));
        carol.acceptFriendRequest(new Name("Alice"));
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "");
        Assert.assertEquals(alice.listFriends(), "Bob\nCarol\n");
        Assert.assertEquals(bob.listFriends(), "Alice\n");
        Assert.assertEquals(carol.listFriends(), "Alice\n");
    }

    @Test
    public void addingOneselfAsFriend() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        alice.addFriend(alice);
        Assert.assertEquals(alice.listFriendRequests() , "");
    }

    @Test
    public void deleteFriendRequest() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        bob.deleteFriendRequest(new Name("Alice"));
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "");
    }

    @Test
    public void multipleFriendRequestWithDelete() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        bob.deleteFriendRequest(new Name("Alice"));
        alice.addFriend(bob);
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "Alice\n");
    }

    @Test
    public void multipleFriendRequestToSameUser() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        alice.addFriend(bob);
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "Alice\n");
    }

    @Test
    public void friendRequestAfterFriends() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        bob.acceptFriendRequest(new Name("Alice"));
        alice.addFriend(bob);
        Assert.assertEquals(alice.listFriendRequests() , "");
        Assert.assertEquals(bob.listFriendRequests() , "");
        Assert.assertEquals(alice.listFriends() , "Bob\n");
        Assert.assertEquals(bob.listFriends() , "Alice\n");
    }

    @Test
    public void friendDelete() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        bob.acceptFriendRequest(new Name("Alice"));
        alice.deleteFriend(new Name("Bob"));
        Assert.assertEquals(alice.listFriends() , "");
        Assert.assertEquals(bob.listFriends() , "");
    }

    @Test
    public void friendDifferentUserMultipleDelete() {
        User alice = new User(new Name("Alice"), new Phone("8942"), new Email("alice@g.com"), new Address("6 Baker"));
        User bob = new User(new Name("Bob"), new Phone("8942"), new Email("bob@g.com"), new Address("6 Baker"));
        User carol = new User(new Name("Carol"), new Phone("8942"), new Email("carol@g.com"), new Address("6 Baker"));
        alice.addFriend(bob);
        alice.addFriend(carol);
        bob.acceptFriendRequest(new Name("Alice"));
        carol.acceptFriendRequest(new Name("Alice"));
        alice.deleteFriend(new Name("Bob"));
        carol.deleteFriend(new Name("Alice"));
        Assert.assertEquals(alice.listFriends() , "");
        Assert.assertEquals(bob.listFriends() , "");
        Assert.assertEquals(carol.listFriends() , "");
    }

}
