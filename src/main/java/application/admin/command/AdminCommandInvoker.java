package application.admin.command;

import java.util.LinkedHashMap;
import java.util.Map;

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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Command name must not be blank");
        }
        AdminCommand command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown admin command: " + name);
        }
        command.execute();
    }

    public Map<String, AdminCommand> getRegisteredCommands() {
        return Map.copyOf(commands);
    }
}
