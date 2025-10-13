package application.admin.command;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Command interface of the Command pattern that encapsulates an admin action.
 */
public interface AdminCommand {

    /**
     * Executes the command sending any textual output to the provided consumer.
     *
     * @param output consumer that receives the lines produced by the command
     */
    void execute(Consumer<String> output);

    /**
     * Backwards-compatible convenience method that writes directly to
     * {@link System#out} when no explicit output consumer is supplied.
     */
    default void execute() {
        execute(line -> System.out.println(Objects.requireNonNullElse(line, "")));
    }
}
