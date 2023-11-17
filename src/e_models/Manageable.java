package e_models;

import java.util.List;

public interface Manageable<T> {
    void add(T item);
    void remove(T item);
    T get(int index);
    List<T> getAll();
}

