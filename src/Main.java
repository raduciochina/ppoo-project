import models.Comanda;
import models.Produs;
import models.Restaurant;
import models.TipProdus;
import utils.Program;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Program.startProgram();

        Comanda c1 = new Comanda();
        c1.addProdus(new Produs("aaaa", 2,5d, TipProdus.MANCARE));
        c1.addProdus(new Produs("ssss", 3,5d, TipProdus.MANCARE));
        c1.addProdus(new Produs("dddd", 4,5d, TipProdus.MANCARE));
        System.out.println(c1);
        System.out.println(Comanda.calculateTotal(c1));


    }
}
