package e_help_services;

import e_models.Product;
import e_shop_services.PurchaseService;
import e_users.User;
import e_users.UserRole;

import java.util.List;
import java.util.Map;

public class ViewPurchasesCommand implements Command {
    private PurchaseService purchaseService;

    public ViewPurchasesCommand(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public void execute(String[] args) {
        Map<User, List<Product>> purchases = purchaseService.getAllPurchases();
        if (purchases.isEmpty()) {
            System.out.println("No purchases have been made yet.");
        } else {
            System.out.println("--- Purchases ---");
            purchases.forEach((user, productList) -> {
                System.out.println("User: " + user.getLogin());
                productList.forEach(product -> System.out.println(" - " + product));
            });
        }
    }

    @Override
    public String getName() {
        return "viewpurchases";
    }

    @Override
    public String getDescription() {
        return "Displays a list of all purchases made by users.";
    }

    @Override
    public boolean isAccessibleBy(UserRole role) {
        return role == UserRole.ADMINISTRATOR;
    }
}

