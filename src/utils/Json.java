package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/**
 * Clasa utilitara folosita pentru scrierea si, respectiv citirea din fisiere de tip JSON a obiectelor si a structurilor
 * de date folosite in gestionarea comenzilor unui restaurant.
 */

public class Json {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static HashMap<String, List<Product>> readProducts(String path) throws IOException {
        return mapper.readValue(Path.of(path).toFile(), new TypeReference<>() {});
    }

    public static void refreshMenu(){
    }


    public static void addProductToJson(String type, Product product, Path path) throws IOException {
        HashMap<String, List<Product>> products = readProducts(path.toString());
        products.get(type).add(product);
        mapper.writeValue(path.toFile(), products);

    }


    public static void removeProductFromJson(String productNameToDelete, String path) throws IOException {
        HashMap<String, List<Product>> products = readProducts(path);
        for(String type : products.keySet()){
            products.get(type).removeIf(product -> product.getName().equals(productNameToDelete));
        }
        mapper.writeValue(Path.of(path).toFile(), products);
    }

    public static void updatePrice(String oldProductName, Product newProduct, String path) throws IOException {
        throw new RuntimeException("Missing implementation");
    }
    //export json with orders ??

}