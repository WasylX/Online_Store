package e_locale;

import java.text.NumberFormat;
import java.text.DateFormat;
import java.util.*;

public class LocalizationManager {

    private static Locale currentLocale;
    private static ResourceBundle messages;

    public static void setupLocale(String languageTag) {
        currentLocale = Locale.forLanguageTag(languageTag);
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public static String getMessage(String key) {
        return messages.getString(key);
    }

    public static void printWelcomeMessage() {
        System.out.println(getMessage("welcome.message"));
    }

    public static void printFormattedDate(Date date) {
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, currentLocale);
        System.out.println(dateFormatter.format(date));
    }

    public static void printFormattedCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        System.out.println(currencyFormatter.format(amount));
    }

    public static void changeLocale(String languageTag) {
        setupLocale(languageTag);
    }
}

