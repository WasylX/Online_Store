package e_store;

import e_log.Logger;
import e_models.Product;
import e_storage.DataStore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductStorage {

    private static List<Product> products;
    private static String filePath;
    private DataStore productStorage;

    public ProductStorage(String filePath) {
        ProductStorage.filePath = filePath;
        products = new ArrayList<>();
        ProductImporter.importProducts(filePath, productStorage);
    }

    public static void add(Product product) {
        products.add(product);
        saveProducts();
    }

    private static void saveProducts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(products);
        } catch (IOException e) {
            Logger.log("Ошибка при сохранении продуктов: " + e.getMessage());
        }
    }

    public boolean removeProduct(String productName) {
        return products.removeIf(product -> product.getName().equalsIgnoreCase(productName));
    }

    public Optional<Product> getProduct(String productName) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void updateProduct(Product updatedProduct) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(updatedProduct.getName()));
        products.add(updatedProduct);
    }

    public void load() {
    }


    public void save() {

    }
}

