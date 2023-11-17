package e_models;

public class Product {
    private String name;
    private double price;
    private double rating;
    private String productId;
    private int id;

    public Product(String productId, String name, String category, double price, double rating) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(int quantity, String name, String description, double price) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}

