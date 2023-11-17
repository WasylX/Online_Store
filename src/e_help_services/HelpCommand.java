package e_help_services;

import e_users.UserRole;

import java.util.Map;

public class HelpCommand implements Command {
    private final Map<String, Command> commandMap;

    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Display help for available commands.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return true;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        for (Command command : commandMap.values()) {
            System.out.println(command.getName() + " - " + command.getDescription());
        }
    }
}

