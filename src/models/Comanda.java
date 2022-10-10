package models;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private List<Produs> produseComandate;

    public Comanda() {
        this.produseComandate = new ArrayList<>();
    }

    public List<Produs> getProduseComandate() {
        return produseComandate;
    }

    public void setProduseComandate(List<Produs> produseComandate) {
        this.produseComandate = produseComandate;
    }

    public void addProdus(Produs produs){
        this.produseComandate.add(produs);
    }
    public void removeProdus(Produs produs){
        this.produseComandate.remove(produs);
    }
    public static Double calculateTotal(Comanda comanda){
        return comanda.getProduseComandate().stream()
                .mapToDouble(p -> p.getCantitate() * p.getPret()).sum();
    }
    @Override
    public String toString() {
        return "Comanda{" +
                "produseComandate=" + produseComandate +
                '}';
    }
}
