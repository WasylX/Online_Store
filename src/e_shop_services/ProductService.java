package e_shop_services;

import e_models.Product;

import java.util.*;

public class ProductService implements Sortable<Product> {
    private Set<Product> products;

    public ProductService() {
        this.products = new LinkedHashSet<>();
    }

    public void addProduct(Product product) {
        if (!products.add(product)) {
            throw new IllegalArgumentException("Product already exists in the catalog.");
        }
    }

    public void removeProduct(String product) {
        if (!products.remove(product)) {
            throw new IllegalArgumentException("Product does not exist in the catalog.");
        }
    }

    public boolean updateProduct(String productId, String name, double price, double rating) {
        for (Product product : products) {
            if (product.getProductId() !=null) {
                product.setName(name);
                product.setPrice(price);
                product.setRating(rating);
                return true;
            }
        }
        return false;
    }

    public Product getProductById(String productId) {
        return products.stream()
                .filter(product -> Objects.equals(product.getProductId(), productId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product with ID " + productId + " not found."));
    }

    public Set<Product> getAllProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Override
    public void sort(Comparator<Product> comparator) {
        List<Product> sortedList = new ArrayList<>(products);
        sortedList.sort(comparator);
        products = new LinkedHashSet<>(sortedList);
    }

    @Override
    public void reverseSort(Comparator<Product> comparator) {
        sort(comparator.reversed());
    }

    public void sortByName() {
        Comparator<Product> byName = Comparator.comparing(Product::getName);
        sort(byName);
    }

    public void sortByPrice() {
        Comparator<Product> byPrice = Comparator.comparingDouble(Product::getPrice);
        sort(byPrice);
    }

    public void sortByRating() {
        Comparator<Product> byRating = Comparator.comparingDouble(Product::getRating);
        sort(byRating);
    }
}

