package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> orderedProducts;

    public Order() {
        this.orderedProducts = new ArrayList<>();
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public void addProduct(Product product){
        this.orderedProducts.add(product);
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
                "orderedProducts=" + orderedProducts +
                '}';
    }
}
