package e_help_services;

import e_users.UserRole;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public void executeCommand(String commandName, String[] args, UserRole role) {
        Command command = commands.get(commandName.toLowerCase());
        if (command != null && command.isAccessibleBy(role)) {
            command.execute(args);
        } else {
            System.out.println("Command not found or not accessible by your role.");
        }
    }

    public void listCommands(UserRole role) {
        System.out.println("Available commands:");
        commands.values().stream()
                .filter(cmd -> cmd.isAccessibleBy(role))
                .forEach(cmd -> System.out.println(cmd.getName() + " - " + cmd.getDescription()));
    }
}

