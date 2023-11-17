package e_help_services;

import e_shop_services.ProductService;
import e_users.UserRole;

public class UpdateProductCommand implements Command {
    private ProductService productService;

    public UpdateProductCommand(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public String getName() {
        return "updateproduct";
    }

    @Override
    public String getDescription() {
        return "Updates details for an existing product.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return role == UserRole.ADMINISTRATOR;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: updateproduct <product_id> <name> <price> <rating>");
            return;
        }
        try {
            String productId = (args[0]);
            String name = args[1];
            double price = Double.parseDouble(args[2]);
            double rating = Double.parseDouble(args[3]);

            boolean isUpdated = productService.updateProduct(productId, name, price, rating);
            if (isUpdated) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product update failed. Please check product ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid input." +
                            " Please enter a valid integer for product ID," +
                            " and valid numbers for price and rating."
            );
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

