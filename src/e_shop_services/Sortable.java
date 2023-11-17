package e_shop_services;

import java.util.Comparator;

public interface Sortable<T> {
    void sort(Comparator<T> comparator);
    void reverseSort(Comparator<T> comparator);
}

