package models;

public class Produs {
    private String denumire;
    private Integer cantitate;
    private Double pret;

    private TipProdus tipProdus;
//    private Double valoare;

    public Produs(String denumire, Integer cantitate, Double pret, TipProdus tipProdus) {
        if(denumire.equals("") || cantitate<=1 || pret<=0){
            throw new IllegalArgumentException("Produs invalid.");
        }
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.pret = pret;
        this.tipProdus = tipProdus;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + denumire + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }
}
