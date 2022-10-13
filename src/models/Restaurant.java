package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Restaurant este o clasa Singleton deoarece din cerintele de implementare reiese faptul ca avem un singur restaurant
 * pentru care trebuie sa gestionam comenzile, asadar prin implementarea design-pattern-ului Singleton ne asiguram de
 * faptul ca aceasta entitate va fi una unica, iar de fiecare data cand se va incerca crearea unei alte instante va fi
 * returnata aceeasi instanta.
 * Restaurantul are ca atribute o denumire si o adresa fizica unde poate fi regasit.
 */
public class Restaurant {
    private String name;
    private String address;
    private static final HashMap<ProductType, List<Product>> menu = new HashMap<>();
    private List<Order> restaurantOrders;
    private static Restaurant instance;

    private Restaurant(String name, String address){
        this.name = name;
        this.address = address;
        this.restaurantOrders = new ArrayList<>();
        //initialize menu
        menu.put(ProductType.FOOD, new ArrayList<>());
        menu.put(ProductType.DRINK, new ArrayList<>());
        initMeniu();
    }

    public static Restaurant getInstance(String name, String address){
        if(instance == null){
            instance = new Restaurant(name,address);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public String showMeniu(){
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", meniu=" + menu +
                ", comenziRestaurant=" + restaurantOrders +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<ProductType, List<Product>> getMenu() {
        return menu;
    }
    public Product getProductFromMenu(ProductType productType, String productName){
        for(Product product : menu.get(productType)){
            if(product.getName().equals(productName)){
                return product;
            }
        }
        return null;
    }
    private void initMeniu(){

        List<Product> food = new ArrayList<>();
        List<Product> beverage = new ArrayList<>();
        food.add(new Product("Pizza Margarita", 100, 28d));
        food.add(new Product("Pizza Napoli", 100, 25d));
        food.add(new Product("Pizza Capriciosa", 100, 29d));
        food.add(new Product("Platoul Boierului", 100, 70d));
        food.add(new Product("Bruschette", 100, 21d));
        food.add(new Product("Burger de Vita Angus", 100, 37d));

        beverage.add(new Product("Coca-Cola", 100, 8d));
        beverage.add(new Product("Sprite", 100, 8d));
        beverage.add(new Product("Apa plata Dorna", 100, 5d));
        beverage.add(new Product("Stella Artois", 100, 12d));
        beverage.add(new Product("Espresso", 100, 28d));
        beverage.add(new Product("Vin Rose Mateus", 100, 55d));

        menu.put(ProductType.FOOD, food);
        menu.put(ProductType.DRINK, beverage);
    }
}
