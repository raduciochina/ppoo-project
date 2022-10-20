package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Order detine un counter pentru a numara comenzile si un id unic pentru fiecare comanda.
 * De asemenea aceasta clasa contine o lista de produse care reprezinta produsele comandate de catre client.
 *
 */
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
    public void addProductToOrder(Product product){
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

    public double calculateOrderPrice(){
        double totalPrice = 0;
        for(Product product : orderedProducts){
            totalPrice = totalPrice + product.getPrice() * product.getQuantity();
        }
        return totalPrice;
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

    public double calculateTVA() {
        return (calculateOrderPrice()*0.05);
    }

    public double calculateTips() {
        return (calculateOrderPrice()*0.1);
    }
}
