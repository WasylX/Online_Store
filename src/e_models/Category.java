package e_models;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Category implements Manageable<Product> {
    private String name;
    private Set<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new LinkedHashSet<>();
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
    }

    @Override
    public Product get(int index) {
        return new ArrayList<>(products).get(index);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}


