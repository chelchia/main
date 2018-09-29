package seedu.address.logic.commands.friend;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a friend request the user received.
 */
public class DeleteFriendRequestCommand extends Command {
    public static final String COMMAND_WORD = "deleteFriendRequest";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer friend;

    /**
     * Creates a DeleteFriendRequestCommand that will delete
     * an invitation to add the specified {@code Integer} friend.
     */
    public DeleteFriendRequestCommand(Integer friend) {
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
                || (other instanceof DeleteFriendRequestCommand // instanceof handles nulls
                && friend.equals(((DeleteFriendRequestCommand) other).friend));
    }
}
