package e_storage;

import e_models.Product;
import e_users.User;

import java.util.*;

public class InMemoryDataStore<P extends Product> implements DataStore {
    private List<Product> productData = new ArrayList<>();
    private Map<String, User> userData = new HashMap<>();

    @Override
    public void saveProducts(List<Product> products) {
        productData = new ArrayList<>(products);
    }

    @Override
    public List<Product> loadProducts() {
        return productData;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void saveUsers(Map<String, User> users) {
        userData = new HashMap<>(users);
    }

    @Override
    public Map<String, User> loadUsers() {
        return userData;
    }

}

