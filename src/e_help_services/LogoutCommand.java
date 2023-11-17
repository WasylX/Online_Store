package e_help_services;

import e_users.UserRole;
import e_users.UserSession;

public class LogoutCommand implements Command {
        private UserSession userSession;

        public LogoutCommand(UserSession userSession) {
            this.userSession = userSession;
        }

        @Override
        public void execute(String[] args) {
            userSession.setLoggedInUser(null);
            System.out.println("You have been logged out.");
        }

    @Override
    public String getName() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "Logs out the current user.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return role == UserRole.REGISTERED_USER || role == UserRole.ADMINISTRATOR;
    }
}

