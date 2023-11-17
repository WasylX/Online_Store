package e_shop_services;

import e_models.Product;
import e_users.User;

import java.util.*;

public class PurchaseService {
    private final Map<User, List<Product>> purchases = new HashMap<>();

    public void recordPurchase(User user, Product product) {
        List<Product> userPurchases = purchases.computeIfAbsent(user, k -> new ArrayList<>());
        userPurchases.add(product);
    }

    public Map<User, List<Product>> getAllPurchases() {
        return Collections.unmodifiableMap(purchases);
    }

}

