package e_shop_services;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleService {
    private Locale locale;
    private ResourceBundle resourceBundle;

    public LocaleService(String language, String country) {
        this.locale = new Locale(language, country);
        loadResourceBundle();
    }

    private void loadResourceBundle() {
        resourceBundle = ResourceBundle.getBundle("Messages", locale);
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public void changeLocale(String language, String country) {
        this.locale = new Locale(language, country);
        loadResourceBundle();
    }
}

