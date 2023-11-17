package e_help_services;

import e_shop_services.ProductService;
import e_users.UserRole;

import java.util.NoSuchElementException;

public class RemoveProductCommand implements Command {

    private ProductService productService;

    public RemoveProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: removeproduct <product_id>");
            return;
        }
        try {
            String productId = (args[0]);
            productService.removeProduct(productId);
            System.out.println("Product removed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid product ID. Please enter a valid integer.");
        } catch (NoSuchElementException e) {
            System.out.println("Product could not be found: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "removeproduct";
    }

    @Override
    public String getDescription() {
        return "Removes a product from the catalog.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return role == UserRole.ADMINISTRATOR;
    }

}

