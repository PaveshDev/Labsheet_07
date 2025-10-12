package application.admin.command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Invoker that stores and executes admin commands by name.
 */
public class AdminCommandInvoker {
    private final Map<String, AdminCommand> commands = new LinkedHashMap<>();

    public void register(String name, AdminCommand command) {
        commands.put(name, command);
    }

    public void execute(String name) {
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
