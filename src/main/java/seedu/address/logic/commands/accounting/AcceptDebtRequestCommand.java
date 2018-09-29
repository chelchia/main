package seedu.address.logic.commands.accounting;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Accepts a debt request the user received.
 */
public class AcceptDebtRequestCommand extends Command {
    public static final String COMMAND_WORD = "acceptDebtRequest";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer debt;


    public AcceptDebtRequestCommand(Integer debt) {
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
                || (other instanceof AcceptDebtRequestCommand // instanceof handles nulls
                && debt.equals(((AcceptDebtRequestCommand) other).debt));
    }
}
