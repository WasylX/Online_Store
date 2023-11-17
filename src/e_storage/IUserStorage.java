package e_storage;

import e_users.User;

import java.util.List;
import java.util.Optional;

public interface IUserStorage {
    void save(List<User> users);
    List<User> load();
    List<User> getAll();
    Optional<User> findByUsername(String username);
}

