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
 * de date folosite in gestionarea comenzilor unui restaurant. Aceasta clasa contine doar metode statice pentru a putea
 * fi utilizata fara a fi nevoie de instantierea unei instante avand rolul unei clase utilitare.
 * Pentru a putea scrie si citi din fisiere de tip JSON, aceasta clasa foloseste biblioteca Jackson.
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
        HashMap<String, List<Product>> products = readProducts(path);
        for(String type : products.keySet()){
            for(Product product : products.get(type)){
                if(product.getName().equals(oldProductName)){
                    product.setPrice(newProduct.getPrice());
                }
            }
        }
        mapper.writeValue(Path.of(path).toFile(), products);
    }

    public static void updateQuantity(String oldProductName, Product newProduct, String path) throws IOException {
        HashMap<String, List<Product>> products = readProducts(path);
        for(String type : products.keySet()){
            for(Product product : products.get(type)){
                if(product.getName().equals(oldProductName)){
                    product.setQuantity(newProduct.getQuantity());
                }
            }
        }
        mapper.writeValue(Path.of(path).toFile(), products);
    }

    public static void refreshMenuStock(String productName, int quantity, String path) throws IOException {
        HashMap<String, List<Product>> products = readProducts(path);
        for(String type : products.keySet()){
            for(Product product : products.get(type)){
                if(product.getName().equals(productName)){
                    product.setQuantity(product.getQuantity() - quantity);
                }
            }
        }
        mapper.writeValue(Path.of(path).toFile(), products);
    }

    //export json with orders ??

}