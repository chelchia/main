package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Accepts a group invite.
 **/

public class AcceptGroupCommand extends Command {
    public static final String COMMAND_WORD = "acceptGroup";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer group;

    /**
     * Creates a AcceptGroupCommand to add the specified {@code Integer} group into the logged in user's group..
     */
    public AcceptGroupCommand(Integer group) {
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
                || (other instanceof AcceptGroupCommand // instanceof handles nulls
                && group.equals(((AcceptGroupCommand) other).group));
    }
}
