package e_users;

import e_log.Logger;
import e_storage.IUserStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserStorage implements IUserStorage {
    private static String filePath;

    UserStorage userStorage =
            new UserStorage("C:\\Users\\MSI\\IdeaProjects\\Online_Store\\src\\e_users\\user_records.txt");

    List<User> users = userStorage.load();

    public UserStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(List<User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении списка пользователей: " + e.getMessage());
            Logger.log("Ошибка при сохранении списка пользователей: " + e.getMessage());
        }
    }

    @Override
    public List<User> load() {
        File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
                return (List<User>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка при загрузке списка пользователей: " + e.getMessage());
                Logger.log("Ошибка при загрузке списка пользователей: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<User> getAll() {
        return load();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return getAll().stream()
                .filter(user -> user.getLogin().equals(username))
                .findFirst();
    }
}

