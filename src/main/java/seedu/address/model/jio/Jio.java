package seedu.address.model.jio;

import javafx.collections.ObservableList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.user.User;

import java.util.Objects;

public class Jio {
    private String name;
    private String time;
    private String date;
    private String place;
    private UniqueUserList<User> people;
    
    public Jio(String name, String time, String date, String place, User creator) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.place = place;
        this.people = new UniqueUserList();
        this.people.add(creator);
    }
    
    public String getName() { return name;}

    public String getTime() { return time;}

    public String getDate() { return date;}

    public String getPlace() { return place;}

    public ObservableList<User> getPeople() { return people.asUnmodifiableObservableList();}
    
    public void addUsers(User newUser) {
        this.people.add(newUser);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Jio)) {
            return false;
        }
        // state check
        Jio otherJio = (Jio) other;
        return otherJio.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, time, date, place);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Time: ")
                .append(getTime())
                .append(" Date: ")
                .append(getDate())
                .append(" Place: ")
                .append(getPlace())
                .append(" People: ");
        this.getPeople().forEach(x -> builder.append(x.getName()));
        return builder.toString();
    }

}
