package e_help_services;

import e_models.Product;
import e_shop_services.ProductService;
import e_users.UserRole;

import java.util.List;

public class ViewProductsCommand implements Command {
    private ProductService productService;

    public ViewProductsCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(String[] args) {
        List<Product> products = (List<Product>) productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("The product catalog is currently empty.");
        } else {
            System.out.println("--- Product Catalog ---");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
    @Override
    public String getName() {
        return "viewproducts";
    }

    @Override
    public String getDescription() {
        return "Displays a list of all products.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return true;
    }

}

