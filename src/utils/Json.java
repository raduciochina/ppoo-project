package utils;

import com.google.gson.Gson;
import models.Order;
import models.Product;
import models.Restaurant;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Clasa utilitara folosita pentru scrierea si, respectiv citirea din fisiere de tip JSON a obiectelor si a structurilor
 * de date folosite in gestionarea comenzilor unui restaurant.
 */

public class Json {
    public static List<Product> readProducts(Path path) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = Files.newBufferedReader(path)) {
            return List.of(gson.fromJson(reader, Product[].class));
        }
    }


}