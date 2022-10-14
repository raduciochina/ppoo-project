package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter;
    private int id;
    private List<Product> orderedProducts;

    public Order() {
        this.id = ++counter;
        this.orderedProducts = new ArrayList<>();
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
    public void addProductToOrder(Product product, int quantity){
        this.orderedProducts.add(product);
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void removeProduct(Product product){
        this.orderedProducts.remove(product);
    }
    public static Double calculateTotal(Order order){
        return order.getOrderedProducts().stream()
                .mapToDouble(p -> p.getQuantity() * p.getPrice()).sum();
    }
    @Override
    public String toString() {
        return "Order{" +
                "id= " + id + " " +
                "orderedProducts=" + orderedProducts +
                '}';
    }
}
