package models;

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

    private static Restaurant instance;

    private Restaurant(String name, String address){
        this.name = name;
        this.address = address;
    }

    public static Restaurant getInstance(String name, String address){
        if(instance == null){
            instance = new Restaurant(name,address);
        }
        return instance;
    }

}
