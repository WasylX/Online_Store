package e_store;

import e_storage.InMemoryDataStore;

import java.util.Scanner;

public class ProductManager {

    private InMemoryDataStore<Product> productStorage;
    private Scanner scanner;

    public ProductManager(InMemoryDataStore<Product> productStorage) {
        this.productStorage = productStorage;
        this.scanner = new Scanner(System.in);
    }

    private void addProduct() {
        System.out.println("Adding a new product.");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter product quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Product newProduct = new Product(quantity, name, description, price);
        ProductStorage.add(newProduct);

        System.out.println("Product added successfully: " + newProduct);
    }


    public static class Product extends e_models.Product {
        private String name;
        private String description;
        private double price;
        private int quantity;

        public Product(String id, String name, String category, double price, double rating) {
            super(id, name, category, price, rating);
        }

        public Product(int quantity, String name, String description, double price) {
            super(quantity, name, description, price);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}

