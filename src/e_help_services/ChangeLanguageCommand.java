package e_help_services;

import e_users.UserRole;

public class ChangeLanguageCommand implements Command {
    @Override
    public String getName() {
        return "changelanguage";
    }

    @Override
    public String getDescription() {
        return "Change the user's language preference.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return true;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: changelanguage <language>");
            return;
        }
    }
}

