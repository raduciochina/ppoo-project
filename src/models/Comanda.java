package models;

import java.util.List;

public class Comanda {
    private List<Produs> produseComandate;

    public Comanda(List<Produs> produseComandate) {
        this.produseComandate = produseComandate;
    }

    public List<Produs> getProduseComandate() {
        return produseComandate;
    }

    public void setProduseComandate(List<Produs> produseComandate) {
        this.produseComandate = produseComandate;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "produseComandate=" + produseComandate +
                '}';
    }
}
