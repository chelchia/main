package seedu.address.logic.commands.restaurant;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a review to the restaurant.
 */
public class WriteReviewCommand extends Command {
    public static final String COMMAND_WORD = "writeReview";

    // TODO
    public static final String MESSAGE_USAGE = null;

    // TODO
    public static final String MESSAGE_SUCCESS = null;

    // TODO
    public static final String MESSAGE_DUPLICATE_REVIEW = null;

    private final Integer review;

    /**
     * Creates a WriteReview to add the specified {@code Integer} review, that ranges from 1 - 5.
     */
    public WriteReviewCommand(Integer review) {
        requireNonNull(review);
        this.review = review;
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
                || (other instanceof WriteReviewCommand // instanceof handles nulls
                && review.equals(((WriteReviewCommand) other).review));
    }
}
