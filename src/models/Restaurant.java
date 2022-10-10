package models;

import java.util.ArrayList;
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

    private final List<Produs> meniu = new ArrayList<>();
    private List<Comanda> comenziRestaurant;

    private static Restaurant instance;

    private Restaurant(String name, String address){
        this.name = name;
        this.address = address;
        this.comenziRestaurant = new ArrayList<>();
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
                ", meniu=" + meniu +
                ", comenziRestaurant=" + comenziRestaurant +
                '}';
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }
    public void initMeniu(){
        meniu.add(new Produs("Pizza Margarita", 100, 28d, TipProdus.MANCARE));
        meniu.add(new Produs("Pizza Napoli", 100, 30d, TipProdus.MANCARE));
        meniu.add(new Produs("Pizza Capriciosa", 100, 25d, TipProdus.MANCARE));
        meniu.add(new Produs("Coca-Cola", 100, 8d, TipProdus.BAUTURA));
        meniu.add(new Produs("Sprite", 100, 8d, TipProdus.BAUTURA));
        meniu.add(new Produs("Apa plata Dorna", 100, 5d, TipProdus.BAUTURA));
        meniu.add(new Produs("Platoul Boierului", 100, 69d, TipProdus.MANCARE));
        meniu.add(new Produs("Stella Artois", 100, 12d, TipProdus.BAUTURA));
        meniu.add(new Produs("Bruschette", 100, 20d, TipProdus.MANCARE));
        meniu.add(new Produs("Espresso", 100, 28d, TipProdus.BAUTURA));
        meniu.add(new Produs("Vin Rose Mateus", 100, 55d, TipProdus.BAUTURA));
        meniu.add(new Produs("Burger de Vita Angus", 100, 38d, TipProdus.MANCARE));
    }
}
