package application.admin.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Invoker that stores and executes admin commands by name.
 */
public class AdminCommandInvoker {
    private final Map<String, AdminCommand> commands = new LinkedHashMap<>();

    public void register(String name, AdminCommand command) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Command name must not be blank");
        }
        if (command == null) {
            throw new IllegalArgumentException("Command instance must not be null");
        }
        if (commands.containsKey(name)) {
            throw new IllegalArgumentException("Command with name '" + name + "' is already registered");
        }
        commands.put(name, command);
    }

    public void execute(String name) {
        execute(name, line -> System.out.println(line));
    }

    public void execute(String name, Consumer<String> output) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Command name must not be blank");
        }
        AdminCommand command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown admin command: " + name);
        }
        if (output == null) {
            throw new IllegalArgumentException("Output consumer must not be null");
        }
        command.execute(output);
    }

    public Map<String, AdminCommand> getRegisteredCommands() {
        return Map.copyOf(commands);
    }
}
