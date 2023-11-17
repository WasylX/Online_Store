package e_shop_services;

import e_users.User;
import e_users.UserManager;
import e_users.UserRole;

public class AuthenticationService {

    private UserManager userManager;

    public AuthenticationService(UserManager userManager) {
        this.userManager = userManager;
    }

    public User register(String login, String password) {
        if (userManager.getUser(login) != null) {
            return null;
        }
        User newUser = new User(login, password, UserRole.REGISTERED_USER);
        userManager.addUser(newUser);
        return newUser;
    }

    public boolean authenticate(String login, String password) {
        User user = userManager.getUser(login);
        if (user != null && user.getPassword().equals(password)) {
            if (user.isFirstLogin() && user.isAdmin()) {
                // Требовать смены пароля
                return false;
            }
            return true;
        }
        return false;
    }

    public void addAdmin(User creator, String login, String password) {
        if (creator != null && creator.isAdmin()) {
            User newAdmin = new User(login, password, UserRole.ADMINISTRATOR);
            userManager.addUser(newAdmin);
        } else {
            System.out.println("Only administrator can add another administrator");
        }
    }

    public boolean changePassword(String login, String oldPassword, String newPassword) {
        User user = userManager.getUser(login);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.changePassword(newPassword);
            return true;
        }
        return false;
    }
}

