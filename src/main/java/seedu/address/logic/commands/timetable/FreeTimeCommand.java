package seedu.address.logic.commands.Integer;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Frees up the time on the user's timetable.
 */
public class FreeTimeCommand extends Command {
    public static final String COMMAND_WORD = "freeTime";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    private final Integer toFree;

    /**
     * Creates a WriteReview to add the specified {@code Integer} review, that ranges from 1 - 5.
     */
    public FreeTimeCommand(Integer toFree) {
        requireNonNull(toFree);
        this.toFree = toFree;
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
                || (other instanceof FreeTimeCommand // instanceof handles nulls
                && toFree.equals(((FreeTimeCommand) other).toFree));
    }
}
