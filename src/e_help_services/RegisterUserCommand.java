package e_help_services;

import e_shop_services.AuthenticationService;
import e_users.User;
import e_users.UserRole;

public class RegisterUserCommand implements Command {
    private AuthenticationService authService;
    public RegisterUserCommand(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: register <login> <password>");
            return;
        }
        String login = args[0];
        String password = args[1];
        try {
            User newUser = authService.register(login, password);
            System.out.println("User registered successfully: " + newUser.getLogin());
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }


    @Override
    public String getName() {
        return "register";
    }

    @Override
    public String getDescription() {
        return "Registers a new user in the system.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return role == UserRole.GUEST;
    }
}

