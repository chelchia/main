package seedu.address.model.jio;

import seedu.address.model.restaurant.Name;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Time {
    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Time must be from 0000-2359 in 24h format.";

    /*
     * Time must follow 24h format.
     */
    public static final String TIME_VALIDATION_REGEX = "([01]?[0-9]|2[0-3])[0-5][0-9]";

    public final String time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValid(time), MESSAGE_TIME_CONSTRAINTS);
        this.time = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValid(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}
