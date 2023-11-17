package e_storage;

import e_models.Product;
import e_users.User;

import java.util.List;
import java.util.Map;

public interface DataStore {
    void saveProducts(List<Product> products);
    List<Product> loadProducts();
    boolean isEmpty();

    void saveUsers(Map<String, User> users);
    Map<String, User> loadUsers();
}

