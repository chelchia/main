package seedu.address.logic.commands.restaurant;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * List the user's favourite restaurants.
 */
public class ListFavouritesCommand extends Command {
    public static final String COMMAND_WORD = "listFavouritesds";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        // TODO
        requireNonNull(model);

        return null;
    }

}

