package e_shop_services;

import e_log.Logger;
import e_log.LoggingService;
import e_models.Product;
import e_payment_services.AsyncPaymentProcessor;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class BasketService {
    private List<Product> basketItems;
    private final LoggingService loggingService;

    private final AsyncPaymentProcessor paymentProcessor;
    private Logger Logger;

    public BasketService(AsyncPaymentProcessor paymentProcessor, LoggingService loggingService) {
        this.basketItems = new ArrayList<>();
        this.paymentProcessor = paymentProcessor;
        this.loggingService = loggingService;
    }
    public void addProductToBasket(Product product) {
        basketItems.add(product);
        Logger.log("Product added to basket: " + product.getName());
    }

    public void removeProductFromBasket(Product product) {
        if (!basketItems.remove(product)) {
            Logger.log("Attempted to remove a product that wasn't in the basket: " + product.getName());
            throw new IllegalArgumentException("Product not in basket");
        }
       Logger.log("Product removed from basket: " + product.getName());
    }

    public List<Product> getBasketItems() {
        return new ArrayList<>(basketItems);
    }

    public void clearBasket() {
        basketItems.clear();
        Logger.log("Basket cleared");
    }

    public void checkout() {
        BigDecimal totalAmount = BigDecimal.valueOf(basketItems.stream().mapToDouble(Product::getPrice).sum());

        CompletableFuture<Boolean> paymentResult = AsyncPaymentProcessor.processPayment(totalAmount);

        paymentResult.thenAccept(success -> {
            if (success) {
                basketItems.clear();
                Logger.log("Payment successful, basket cleared.");
                System.out.println("Payment successful. Thank you for your purchase!");
            } else {
                Logger.log("Payment failed.");
                System.out.println("Payment failed. Please try again.");
            }
        });
    }

    private boolean processPayment(List<Product> items) {
        double paymentAmount = items.stream().mapToDouble(Product::getPrice).sum();
        Logger.log("Processing payment for amount: " + paymentAmount);
        return Math.random() > 0.9;
    }
}

