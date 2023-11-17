package e_help_services;

import e_models.Product;
import e_shop_services.ProductService;
import e_users.UserRole;

public class AddProductCommand implements Command {
    private ProductService productService;
    private int productId;
    private String category;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: addproduct <name> <price> <rating>");
            return;
        }
        try {
            String productId = args[0];
            String name = args[1];
            double price = Double.parseDouble(args[2]);
            double rating = Double.parseDouble(args[3]);
            Product product = new Product(productId, name, category, price, rating);
            productService.addProduct(product);
            System.out.println("Product added successfully: " + product);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price or rating. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Product could not be added: " + e.getMessage());
        }
    }
    @Override
    public String getName() {
        return "addproduct";
    }

    @Override
    public String getDescription() {
        return "Adds a new product to the catalog.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return role == UserRole.ADMINISTRATOR;
    }

}

