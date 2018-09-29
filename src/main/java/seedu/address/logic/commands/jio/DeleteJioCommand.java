package seedu.address.logic.commands.jio;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a jio the user started.
 */
public class DeleteJioCommand extends Command {
    public static final String COMMAND_WORD = "deleteJio";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer jio;

    /**
     * Deletes a jio to add the specified {@code Integer} review, that ranges from 1 - 5.
     */
    public DeleteJioCommand(Integer jio) {
        requireNonNull(jio);
        this.jio = jio;
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
                || (other instanceof DeleteJioCommand // instanceof handles nulls
                && jio.equals(((DeleteJioCommand) other).jio));
    }
}
