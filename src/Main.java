import e_locale.LocalizationManager;
import e_log.Logger;
import e_models.Product;
import e_storage.*;
import e_store.ProductImporter;
import e_Interface.ShopInterface;
import e_users.User;
import e_users.UserContext;
import e_users.UserManager;
import e_users.UserRole;

import java.util.*;

import static e_store.ProductImporter.isEnabled;

public class Main {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    static DataStore productStorage;
    static DataStore userStorage = new SerializedDataStore();
    private static UserManager userManager = new UserManager();
    private static boolean isRunning = true;
    private static String username;
    private static String password;
    UserContext userContext = new UserContext(currentUser);

    public static void main(String[] args) {
        setupLocale(args);
        setupLogging(args);
        setupStorage(args);

        loadInitialData();

        checkAndRegisterAdmin();

        runApplicationLoop();

        startShopInterface();
    }

    private static void setupLocale(String[] args) {
        String languageTag = Arrays.stream(args)
                .filter(arg -> arg.startsWith("--locale="))
                .findFirst()
                .map(arg -> arg.substring("--locale=".length()))
                .orElse("en");

        LocalizationManager.setupLocale(languageTag);
    }

    private static void setupLogging(String[] args) {
        boolean isLoggingEnabled = Arrays.stream(args).anyMatch("--enable-logging"::equals);
        if (isLoggingEnabled) {
            Logger.log("Logger is enabled");
        }
    }

    private static void setupStorage(String[] args) {
        String storageType = args.length > 0 ? args[0] : "memory";
        switch (storageType) {
            case "csv":
                productStorage = new CSVDataStore();
                userStorage = new CSVDataStore();
                break;
            case "serialized":
                productStorage = new SerializedDataStore();
                userStorage = new SerializedDataStore();
                break;
            case "memory":
                productStorage = new InMemoryDataStore<Product>();
                userStorage = new InMemoryDataStore<Product>();
                break;
            default:
                System.out.println("Unknown storage type: " + storageType + ". Using default in-memory storage.");
                productStorage = new InMemoryDataStore<Product>();
                userStorage = new InMemoryDataStore<Product>();
                break;
        }
    }

    private static void loadInitialData() {
        productStorage.loadProducts();
        userStorage.loadUsers();

        ProductImporter importer = new ProductImporter();
        try {
            ProductImporter.importProducts(
                    "C:\\Users\\MSI\\IdeaProjects\\Online_Store\\src\\e_store\\products.csv", productStorage
            );
        } catch (NumberFormatException e) {
            System.out.println("Error in file data format: " + e.getMessage());
        }
    }

    private static void checkAndRegisterAdmin() {
        if (userStorage.isEmpty()) {
            System.out.println("No administrators found. Create the first admin account.");
            registerAdmin();
        }
    }

    private static void runApplicationLoop() {
        try (Scanner scanner = new Scanner(System.in);
             Logger logger = new Logger("user_action.log", isEnabled)) {

            while (isRunning) {
                printMenu();
                String option = scanner.nextLine();
                processUserOption(option, logger);
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nPlease choose an action: ");
        System.out.println("1. Register");
        System.out.println("2. Add Admin");
        System.out.println("3. Change Password");
        System.out.println("4. Login");
        System.out.println("5. Show Help");
        System.out.println("6. Exit");
        System.out.print("Enter option: ");
    }

   private static void processUserOption(String option, Logger logger) {
        switch (option) {
            case "1":
              /* registerUser();
                break;
            case "2":
                addAdmin();
                break;
            case "3":
                changePassword();
                break;
            case "4":
                loginUser();
                break;
            case "5":
                showHelp();
                break;
            case "6":
                exitApplication();
                break;
            default:*/
                System.out.println("Invalid option, please try again.");
                break;
        }
    }

    private static void startShopInterface() {
        ShopInterface shopInterface = new ShopInterface(scanner, userManager);
        shopInterface.start();
    }


    private static void registerAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        User admin = new User(username, password, UserRole.ADMINISTRATOR);
        users.put(username, admin);
    }
    private static boolean isAdmin(User user) {
        return user != null && user.getRole() == UserRole.ADMINISTRATOR;
    }
}

