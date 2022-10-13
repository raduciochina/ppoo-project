package models;

public class Product {
    private String name;
    private Integer quantity;
    private Double price;

    private ProductType productType;
//    private Double valoare;

    public Product(String name, Integer quantity, Double price) {
        if(name.equals("") || quantity<=1 || price<=0){
            throw new IllegalArgumentException("Produs invalid.");
        }
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Product(String name, Double price) {
        if(name.equals("") || price<=0){
            throw new IllegalArgumentException("Produs invalid.");
        }
        this.name = name;
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


    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + name + '\'' +
                ", pret=" + price +
                '}';
    }
}
