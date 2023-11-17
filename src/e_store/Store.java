package e_store;

import e_models.Basket;
import e_models.Product;
import e_payment_services.AsyncPaymentProcessor;
import e_users.UserRole;
import e_users.User;
import e_users.UserManager;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Store {
    private Map<String, Product> products = new HashMap<>();
    private Map<User, List<Product>> purchases = new HashMap<>();
    private  AsyncPaymentProcessor processPayment;

    private UserManager userManager;
    private String login;
    private Basket basket;

    public Store(UserManager userManager) {
        this.userManager = userManager;
    }

    public void listProducts() {
        products.values().forEach(System.out::println);
    }

    public void addProduct(Product product) {
        if (userManager.getUserRole(login) == UserRole.ADMINISTRATOR) {
            products.put(product.getProductId(), product);
            System.out.println("Product added.");
        } else {
            System.out.println("You do not have permission to add products.");
        }
    }

    public void removeProduct(String productId) {
        if (userManager.getLoggedInUser().getRole() == UserRole.ADMINISTRATOR) {
            products.remove(productId);
            System.out.println("Product removed.");
        } else {
            System.out.println("You do not have permission to remove products.");
        }
    }

    public void updateProduct(Product product) {
        if (userManager.getLoggedInUser().getRole() == UserRole.ADMINISTRATOR) {
            products.put(product.getProductId(), product);
            System.out.println("Product updated.");
        } else {
            System.out.println("You do not have permission to update products.");
        }
    }

    public void purchaseProduct(String productId) {
        User user = userManager.getLoggedInUser();
        if (user != null && user.getRole() == UserRole.REGISTERED_USER) {
            Product product = products.get(productId);
            if (product != null) {
                purchases.computeIfAbsent(user, k -> new ArrayList<>()).add(product);
                System.out.println("Product purchased.");
            } else {
                System.out.println("Product does not exist.");
            }
        } else {
            System.out.println("You need to be logged in to make a purchase.");
        }
    }

    public void listUserPurchases() {
        User user = userManager.getLoggedInUser();
        if (user != null) {
            List<Product> userPurchases = purchases.get(user);
            if (userPurchases != null) {
                userPurchases.forEach(System.out::println);
            } else {
                System.out.println("No purchases found for the user.");
            }
        } else {
            System.out.println("You need to be logged in to view purchases.");
        }
    }

    public void checkout() {
        BigDecimal totalAmount = basket.getTotalAmount();

        CompletableFuture<Boolean> paymentResult = AsyncPaymentProcessor.processPayment(totalAmount);

        paymentResult.thenAccept(paymentSuccessful -> {
            if (paymentSuccessful) {
                System.out.println("Payment successful! Thank you for your purchase.");
                basket.clear();
            } else {
                System.out.println("Payment failed! Please try again or use a different payment method.");
            }
        });
    }
}

