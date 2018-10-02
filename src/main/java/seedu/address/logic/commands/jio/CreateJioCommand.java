package seedu.address.logic.commands.jio;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.jio.Jio;

/**
 * Creates a public jio.
 */
public class CreateJioCommand extends Command {
    public static final String COMMAND_WORD = "createJio";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new jio and adds a jio to the book of jios. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TIME + "TIME "
            + PREFIX_DATE + "DATE "
            + PREFIX_ADDRESS + "PLACE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "MALA "
            + PREFIX_TIME + "1300 "
            + PREFIX_DATE + "14-11-18 "
            + PREFIX_ADDRESS + "Fine Food ";

    public static final String MESSAGE_SUCCESS = "New jio added: %1$s";
    public static final String MESSAGE_DUPLICATE_JIO = "This jio already exists in the book";

    private final Jio toAdd;


    /**
     * Creates an createJioCommand to add the specified {@code Jio}
     */
    public CreateJioCommand(Jio jio) {
        requireNonNull(jio);
        toAdd = jio;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        
        if (model.ha(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JIO); //Jio has already been created
        }

        model.addRestaurant(toAdd);
        model.commitAddressBook();
        
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateJioCommand // instanceof handles nulls
                && toAdd.equals(((CreateJioCommand) other).toAdd));
    }}
