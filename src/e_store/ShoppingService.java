package e_store;

import e_models.Product;
import e_users.User;

import java.util.ResourceBundle;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class ShoppingService {
    private User user;
    private ResourceBundle messages;

    public ShoppingService(User user, Locale locale) {
        this.user = user;
        this.messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    public void checkout() {
        Locale locale = messages.getLocale();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(locale);

        double total = 0.0;
        for (Product product : user.getBasket().getPurchasedProducts()) {
            total += product.getPrice();
            System.out.println(messages.getString("product.name") + product.getName()
                    + ": " + currencyFormat.format(product.getPrice()));
        }
        System.out.println(messages.getString("total.sum") + currencyFormat.format(total));
        System.out.println(messages.getString("purchase.date")
                + dateFormat.format(user.getBasket().getPurchaseDate()));
        user.getBasket().clear();
    }
}

