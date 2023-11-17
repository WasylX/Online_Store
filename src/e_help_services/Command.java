package e_help_services;

import e_users.UserRole;

public interface Command {
    String getName();
    String getDescription();
    boolean isAccessibleBy(UserRole role);
    void execute(String[] args);
}

