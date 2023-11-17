package e_Interface;

import e_models.Product;
import e_store.ProductStorage;
import e_users.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopInterface {

    private final Scanner scanner;
    private final UserManager userManger;

    public ShopInterface(Scanner scanner, UserManager userManager) {
        this.scanner = scanner;
        this.userManger = userManager;
    }

    public void start() {
        System.out.println("Welcome to the online shop!");

        boolean shopRunning = true;
        while (shopRunning) {
            System.out.println("\nPlease choose an action: ");
            System.out.println("1. View product catalogs");
            System.out.println("2. View products in a catalog");
            System.out.println("3. Sort products");
            System.out.println("4. Add product to cart");
            System.out.println("5. Checkout");
            System.out.println("6. Logout");
            System.out.print("Enter option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    viewProductCatalogs();
                    break;
                case "2":
                    viewProductsInCatalog();
                    break;
                case "3":
                    sortProducts();
                    break;
                case "4":
                    addProductToCart();
                    break;
                case "5":
                    checkout();
                    break;
                case "6":
                    shopRunning = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }

    public void viewProductCatalogs() {
        ProductStorage productStorage =
                new ProductStorage("C:\\Users\\MSI\\IdeaProjects\\Online_Store\\src\\e_store\\products.csv");
        List<Product> products = productStorage.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Каталог продуктов пуст.");
        } else {
            System.out.println("Каталог продуктов:");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductId() + ", Название: " + product.getName()
                        + ", Цена: " + product.getPrice() + ", Рейтинг: " + product.getRating());
            }
        }
    }

    private void viewProductsInCatalog() {
        System.out.print("Enter catalog ID: ");
        String catalogId = scanner.nextLine();
        List<Product> products = getProductsInCatalog(catalogId);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void sortProducts() {
        System.out.println("Choose sort type:");
        System.out.println("1. By Name");
        System.out.println("2. By Price");
        System.out.println("3. By Rating");
        System.out.print("Enter option: ");
        String sortOption = scanner.nextLine();
        List<Product> sortedProducts = getSortedProducts(sortOption);
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    private void addProductToCart() {
        System.out.print("Enter product ID to add to cart: ");
        String productId = scanner.nextLine();
        addToCart(productId);
        System.out.println("Product added to cart.");
    }

    private void checkout() {
        System.out.println("Processing your checkout...");
        System.out.println("Checkout complete. Total amount: ");
    }


    private List<Product> getProductsInCatalog(String catalogId) {
        return new ArrayList<>();
    }

    private List<Product> getSortedProducts(String sortOption) {
        return new ArrayList<>();
    }

    private void addToCart(String productId) {
    }
}

