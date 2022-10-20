package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clasa Product modeleaza un produs din meniul restaurantului.
 * Aceasta clasa are ca atribute denumirea, pretul si cantitatea produsului comandat.
 * Atributul cantitate este folosit atat in meniu pentru a se stii in prealabil de catre
 * restaurant cat de multe produse de un anumit tip se pot vinde intr-o anumita zi cat si
 * in comanda pentru a se stii de catre restaurant cat de multe produse de un anumit tip
 * a comandat un client astfel incat sa se poata realiza actualizarea stocului.
 */
public class Product {
    private String name;
    private Integer quantity;
    private Double price;

    private Product(){

    }

    public Product(String name, Integer quantity, Double price) {
        if(name.equals("") || quantity<1 || price<=0){
            throw new IllegalArgumentException("Produs invalid.");
        }
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toStringNice() {
        return
                 name +
                " ----------------------------------- "  + price +
                " RON";
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
