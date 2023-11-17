package e_users;

public class UserSession {
    private User currentUser;
    private User loggedInUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public UserRole getCurrentUserRole() {
        if (currentUser != null) {
            return currentUser.getRole();
        }
        return UserRole.GUEST;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}

