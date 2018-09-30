package seedu.address.logic.parser;

import seedu.address.logic.commands.jio.CreateJioCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jio.Date;
import seedu.address.model.jio.Jio;
import seedu.address.model.jio.Time;
import seedu.address.model.restaurant.*;
import seedu.address.model.restaurant.Address;
import seedu.address.model.restaurant.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class CreateJioCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddJioCommand
     * and returns an AddJioCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateJioCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIME, PREFIX_DATE, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIME, PREFIX_DATE, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateJioCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Address location = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
//        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Jio jio = new Jio(name, time, date, location);

        return new CreateJioCommand(jio);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
