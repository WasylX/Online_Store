package e_storage;

import e_models.Product;
import e_users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializedDataStore implements DataStore {
    private static final String PRODUCTS_FILE = "products.ser";
    private static final String USERS_FILE = "users.ser";

    @Override
    public void saveProducts(List<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE))) {
            oos.writeObject(products);
        } catch (IOException e) {
            // Обработка исключений
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRODUCTS_FILE))) {
            return (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Обработка исключений
            return new ArrayList<>();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            // Обработка исключений
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Обработка исключений
            return new HashMap<>();
        }
    }
}

