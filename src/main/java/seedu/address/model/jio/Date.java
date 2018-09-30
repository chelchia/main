package seedu.address.model.jio;

import seedu.address.model.restaurant.Name;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Date {
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date must be in DDMMYY format.";

    /*
     * Must follow DDMMYY format.
     */
    public static final String DATE_VALIDATION_REGEX = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-([0-9][0-9])";

    public final String date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValid(date), MESSAGE_DATE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValid(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
