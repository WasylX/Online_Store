package e_models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Basket implements Manageable<Product> {
    private List<Product> purchasedProducts;
    private LocalDate purchaseDate;

    public Basket() {
        this.purchasedProducts = new ArrayList<>();
        this.purchaseDate = LocalDate.now();
    }

    @Override
    public void add(Product product) {
        this.purchasedProducts.add(product);
    }

    @Override
    public void remove(Product product) {
        this.purchasedProducts.remove(product);
    }

    @Override
    public Product get(int index) {
        return purchasedProducts.get(index);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(purchasedProducts);
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void clear() {
        purchasedProducts.clear();
    }

    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Product product : purchasedProducts) {
            totalAmount = totalAmount.add(BigDecimal.valueOf(product.getPrice()));
        }
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "purchasedProducts=" + purchasedProducts +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}

