package e_users;

import e_help_services.*;
import e_shop_services.ProductService;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class UserContext {
    private User user;
    private List<Command> commands;
    private String[] arg;
    private Map<String, Command> commandMap;

    public UserContext(User user) {
        this.user = user;
        this.commands = new ArrayList<>();
        setupCommands();
    }

    private void setupCommands() {
        if (user.getRole() == UserRole.ADMINISTRATOR) {
            commands.add(new AddProductCommand(new ProductService()));
        } else {
            commands.add(new ViewProductsCommand(new ProductService()));
        }
        commands.add(new HelpCommand(commandMap));
        commands.add(new ChangeLanguageCommand());
    }

    public void executeCommand(String commandInput) {
        for (Command command : commands) {
            if (commandInput.equals(command.getName())) {
                command.execute(new String[]{arg[0]});
                return;
            }
        }
        System.out.println("Команда не найдена. Введите 'help' для списка команд.");
    }
}

