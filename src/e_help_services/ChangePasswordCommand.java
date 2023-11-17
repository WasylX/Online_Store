package e_help_services;

import e_shop_services.AuthenticationService;
import e_users.User;
import e_users.UserRole;
import e_users.UserSession;

public class ChangePasswordCommand implements Command {
    private AuthenticationService authService;
    private UserSession userSession;
    private String login;

    public ChangePasswordCommand(AuthenticationService authService, UserSession userSession) {
        this.authService = authService;
        this.userSession = userSession;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: changepassword <oldPassword> <newPassword>");
            return;
        }
        String oldPassword = args[0];
        String newPassword = args[1];
        try {
            User user = userSession.getLoggedInUser();
            if (user != null && authService.changePassword(login, oldPassword, newPassword)) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Password change failed: Incorrect old password.");
            }
        } catch (Exception e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "changepassword";
    }

    @Override
    public String getDescription() {
        return "Changes the password for the current user.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return false;
    }
}

