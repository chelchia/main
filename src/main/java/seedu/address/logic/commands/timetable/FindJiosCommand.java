package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds a jios suitable for the logged in user's timetable.
 */
public class FindJiosCommand extends Command {
    public static final String COMMAND_WORD = "findJios";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer friends;

    /**
     * Creates a FindJiosCommand to find a common eating timeslot among the {@code Integer} friends.
     */
    public FindJiosCommand(Integer friends) {
        requireNonNull(friends);
        this.friends = friends;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        // TODO
        requireNonNull(model);

        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindJiosCommand // instanceof handles nulls
                && friends.equals(((FindJiosCommand) other).friends));
    }
}
