package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Blocks out time on the logged in user's timetable based on nusmods link.
 */
public class AddNusModsCommand extends Command {
    public static final String COMMAND_WORD = "AddNusMods";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    // TODO
    private final Integer link;


    public AddNusModsCommand(Integer link) {
        requireNonNull(link);
        this.link = link;
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
                || (other instanceof AddNusModsCommand // instanceof handles nulls
                && link.equals(((AddNusModsCommand) other).link));
    }
}
