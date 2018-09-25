package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class addJioParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddJioCommand
     * and returns an AddJioCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIME, PREFIX_DATE, PREFIX_PLACE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIME, PREFIX_DATE, PREFIX_PLACE);
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        String time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        String date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        String place = ParserUtil.parsePlace(argMultimap.getValue(PREFIX_PLACE).get());
//        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Jio jio = new Person(name, time, date, place);

        return new AddJioCommand(jio);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
