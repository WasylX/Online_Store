package e_store;

import e_log.Logger;
import e_models.Product;
import e_storage.DataStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductImporter {

    public static boolean isEnabled;

    String filePath = "C:\\Users\\MSI\\IdeaProjects\\Online_Store\\src\\e_store\\products.csv";

    private static final Logger logger = new Logger("user_action.log", isEnabled);

    public static void importProducts(String filePath, DataStore productStorage) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Пропускаем заголовок

            while ((line = reader.readLine()) != null) {
                try {
                    String[] productData = line.split(",");
                    if (productData.length < 5) {
                        Logger.log("Недостаточно данных в строке: " + line);
                        continue;
                    }
                    String productId = productData[0];
                    String name = productData[1];
                    String category = productData[2];
                    double price = Double.parseDouble(productData[3]);
                    double rating = Double.parseDouble(productData[4]);
                    products.add(new Product(productId, name, category, price, rating));
                } catch (NumberFormatException e) {
                    Logger.log("Ошибка преобразования числа в строке: " + line + " - " + e.getMessage());
                }
            }
            productStorage.saveProducts(products);
        } catch (IOException e) {
            Logger.log("Ошибка чтения файла: " + filePath + " - " + e.getMessage());
        }
    }
}

