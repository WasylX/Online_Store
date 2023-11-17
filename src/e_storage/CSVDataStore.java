package e_storage;

import e_models.Product;
import e_users.User;
import e_users.UserRole;

import java.io.*;
import java.util.*;

public class CSVDataStore implements DataStore {

    private static final String PRODUCTS_FILE = "products.csv";
    private static final String USERS_FILE = "users.csv";

    @Override
    public void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTS_FILE))) {
            for (Product product : products) {
                String line = String.format("%d,%s,%f,%f\n",
                        product.getProductId(),
                        product.getName(),
                        product.getPrice(),
                        product.getRating());
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving products to CSV file: " + e.getMessage());
        }
    }

    @Override
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = (parts[0]);
                String name = parts[1];
                String category = parts[2];
                double price = Double.parseDouble(parts[3]);
                double rating = Double.parseDouble(parts[4]);
                products.add(new Product(id, name, category, price, rating));
            }
        } catch (IOException e) {
            System.err.println("Error loading products from CSV file: " + e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error parsing products data: " + e.getMessage());
        }
        return products;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void saveUsers(Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users.values()) {
                String line = String.format("%s,%s,%s\n",
                        user.getLogin(),
                        user.getPassword(),
                        user.getRole().toString());
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving users to CSV file: " + e.getMessage());
        }
    }

    @Override
    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String login = parts[0];
                String password = parts[1];
                UserRole role = UserRole.valueOf(parts[2]);
                users.put(login, new User(login, password, role));
            }
        } catch (IOException e) {
            System.err.println("Error loading users from CSV file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing users data: " + e.getMessage());
        }
        return users;
    }
}

