package seedu.address.logic.commands.friend;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists a user's mutual friends.
 */
public class FindMutualFriendsCommand extends Command {
    public static final String COMMAND_WORD = "findMutualFriends";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer user;

    /**
     * Finds the specified {@code Integer} user's mutual friends.
     */
    public FindMutualFriendsCommand(Integer user) {
        requireNonNull(user);
        this.user = user;
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
                && user.equals(((FindMutualFriendsCommand) other).user));
    }
}
