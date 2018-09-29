package seedu.address.logic.commands.debt;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a debt request the user received.
 */
public class DeleteDebtRequestCommand extends Command {
    public static final String COMMAND_WORD = "deleteDebtRequest";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer debt;

    /**
     * Creates a DeleteDebtRequestCommand that will delete
     * an invitation to add the specified {@code Integer} debt.
     */
    public DeleteDebtRequestCommand(Integer debt) {
        requireNonNull(debt);
        this.debt = debt;
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
                || (other instanceof DeleteDebtRequestCommand // instanceof handles nulls
                && debt.equals(((DeleteDebtRequestCommand) other).debt));
    }
}
