package e_payment_services;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class AsyncPaymentProcessor {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static CompletableFuture<Boolean> processPayment(BigDecimal amount) {
        return CompletableFuture.supplyAsync(() -> {
            // Эмуляция задержки сетевого запроса
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }, executor);
    }
}

