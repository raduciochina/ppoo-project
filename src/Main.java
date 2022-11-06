import models.Order;
import models.Product;
import models.Restaurant;
import utils.Json;
import utils.Program;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Main este clasa principala a aplicatiei. Aceasta clasa contine metoda main in care
 * este apelata metoda startProgram din cadrul clasei Program.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Program.startProgram();
    }
}
