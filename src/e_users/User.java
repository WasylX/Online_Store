package e_users;

import e_models.Basket;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
    private transient Basket basket;
    private UserRole role;
    private boolean isFirstLogin;
    private boolean passwordChangeRequired;
    private String passwordResetCode;

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.basket = new Basket();
        this.isFirstLogin = (role == UserRole.ADMINISTRATOR);
        this.passwordChangeRequired = (role == UserRole.ADMINISTRATOR);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Basket getBasket() {
        return basket;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

    public boolean isPasswordChangeRequired() {
        return passwordChangeRequired;
    }

    public void setPasswordChangeRequired(boolean passwordChangeRequired) {
        this.passwordChangeRequired = passwordChangeRequired;
    }

    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        this.isFirstLogin = false;
        this.passwordChangeRequired = false;
    }

    public boolean isAdmin() {
        return this.role == UserRole.ADMINISTRATOR;
    }

}

