package e_users;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users;
    private User loggedInUser;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getLogin(), user);
    }

    public User getUser(String login) {
        return users.get(login);
    }

    public UserRole getUserRole(String login) {
        User user = getUser(login);
        return user != null ? user.getRole() : null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }
}

