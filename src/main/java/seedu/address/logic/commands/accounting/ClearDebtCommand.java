package seedu.address.logic.commands.accounting;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a debt from the logged in user's list of debts.
 */
public class ClearDebtCommand extends Command {
    public static final String COMMAND_WORD = "clearDebt";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer toClear;


    public ClearDebtCommand(Integer toClear) {
        requireNonNull(toClear);
        this.toClear = toClear;
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
                || (other instanceof ClearDebtCommand // instanceof handles nulls
                && toClear.equals(((ClearDebtCommand) other).toClear));
    }
}
