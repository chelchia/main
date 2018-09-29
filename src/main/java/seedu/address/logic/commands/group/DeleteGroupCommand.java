package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a group the user joined.
 */
public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deleteGroup";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer group;

    /**
     * Creates a DeleteGroupCommand that will delete a
     * specified {@code Integer} group that the user joined.
     */
    public DeleteGroupCommand(Integer group) {
        requireNonNull(group);
        this.group = group;
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
                || (other instanceof DeleteGroupCommand // instanceof handles nulls
                && group.equals(((DeleteGroupCommand) other).group));
    }
}
