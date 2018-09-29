package seedu.address.logic.commands.friend;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a friend in the user's friend list.
 */
public class DeleteFriendCommand extends Command {
    public static final String COMMAND_WORD = "deleteFriend";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer friend;

    /**
     * Deletes the specified {@code Integer} friend.
     */
    public DeleteFriendCommand(Integer friend) {
        requireNonNull(friend);
        this.friend = friend;
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
                || (other instanceof DeleteFriendCommand // instanceof handles nulls
                && friend.equals(((DeleteFriendCommand) other).friend));
    }
}
