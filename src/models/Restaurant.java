package models;

import utils.Json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Restaurant este o clasa Singleton deoarece din cerintele de implementare reiese faptul ca avem un singur restaurant
 * pentru care trebuie sa gestionam comenzile, asadar prin implementarea design-pattern-ului Singleton ne asiguram de
 * faptul ca aceasta entitate va fi una unica, iar de fiecare data cand se va incerca crearea unei alte instante va fi
 * returnata aceeasi instanta.
 * Restaurantul are ca atribute o denumire si o adresa fizica unde poate fi regasit.
 * De asemenea, restaurantul are un meniu reprezentat de un HashMap care are ca cheie tipul produsului si ca valoare o lista
 * de produse de acelasi tip. Totodata, la nivelul restaurantului se retine o lista de comenzi.
 */
public class Restaurant {
    private String name;
    private String address;
    private final HashMap<String, List<Product>> menu = new HashMap<>();
    private List<Order> restaurantOrders;
    private static Restaurant instance;


    private Restaurant() throws IOException {
        this.name = "La Radu";
        this.address = "Bd. Compozitorilor 33";
        this.restaurantOrders = new ArrayList<>();
        initMeniu();
    }

    /**
     * Implementare de Lazy Singleton
     */
    public static Restaurant getInstance() throws IOException {
        if(instance == null){
            instance = new Restaurant();
        }
        return instance;
    }
    public void getOrders(){
        System.out.println(this.restaurantOrders);
    }
    public List<Order> getOrderList(){
        return this.restaurantOrders;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", meniu=" + menu +
                ", comenziRestaurant=" + restaurantOrders +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, List<Product>> getMenu() {
        return menu;
    }

    /**
     * Metodele showPretyMenu si showNormalMenu sunt folosit pentru afisarea meniului la consola in diferite formate,
     * un format pentru client si un format pentru administratorul aplicatiei.
     */

    public void showPrettyMenu(){
        System.out.println("Meniul restaurantului " + this.name + " este:");
        for(String type : menu.keySet()){
            System.out.println(type + ":");
            for(Product product : menu.get(type)){
                System.out.println(product.toStringNice());
            }
        }
    }
    public void showNormalMenu(){
        System.out.println("Meniul restaurantului " + this.name + " este:");
        for(String type : menu.keySet()){
            System.out.println(type + ":");
            for(Product product : menu.get(type)){
                System.out.println(product.toString());
            }
        }
    }

    /**
     * Metoda folosita pentru extragerea unui produs dupa nume din cadrul HashMap-ului
     * @param productName
     * @return
     */
    public Product getProductFromMenu(String productName){
        for(String type : menu.keySet()){
            for(Product product : menu.get(type)){
                if(product.getName().equals(productName)){
                    return product;
                }
            }
        }
        return null;

    }

    /**
     * Metoda initMeniu populeaza HashMap-ul din cadrul clasei cu produsele citite din fisierul .json cu ajutorul unei clase utilitare
     * care are metode statice de citire/scriere in json.
     * @throws IOException
     */
    public void initMeniu() throws IOException {
        this.menu.putAll(Json.readProducts("src\\meniu.json"));
    }

    /**
     * Metoda ce intoarce o comanda care practic este formata dintr-o o lista de produse dupa id-ul acesteia
     * @param id
     */
    public void getOrderById(int id){
        for(Order order : this.restaurantOrders){
            if(order.getId() == id){
                System.out.println(order);
            }
        }
    }

    /**
     * Metoda addProductToOrder este utilizata in momentul in care clientul realizeaza o comanda, se creeaza o instanta a clasei Order,
     * se extrage produsul comandat din meniu pe baza denumirii acestuia, se verifica stocul de la nivelul restaurantului si ulterior este adaugat in
     * comanda si se actualizeaza stocul acestuia.
     * @param productName
     * @param quantity
     */
    public void addProductToOrder(String productName, int quantity) {
        Order order = new Order();

        Product product = getProductFromMenu(productName);
        if(product == null){
            throw new IllegalArgumentException("Produsul nu exista in meniu.");
        }
        if(product.getQuantity() < quantity){
            throw new IllegalArgumentException("Cantitatea ceruta nu este disponibila.");
        }
        product.setQuantity(product.getQuantity() - quantity);
        Product newProduct = new Product(product.getName(), quantity, product.getPrice());
        order.addProductToOrder(newProduct);
        this.restaurantOrders.add(order);
    }

    /**
     * Metoda addProductToExistingOrder este folosita ca o extensie a metodei addProductToOrder.
     * Aceasta e folosita mereu cand un client doreste sa adauge mai mult de 1 produs dintr-un fel la comanda sa.
     * @param id
     * @param product
     * @param quantity
     */
    public void addProductToExistingOrder(int id, String product, int quantity){
        for(Order order : this.restaurantOrders){
            if(order.getId() == id){
                Product menuProduct = getProductFromMenu(product);
                menuProduct.setQuantity(menuProduct.getQuantity() - quantity);
                Product productToAdd = new Product(menuProduct.getName(), quantity, menuProduct.getPrice());
                order.addProductToOrder(productToAdd);
            }
        }
    }

    /**
     * Metoda calculateTotalPrice calculeaza costul total al tututor comenzilor efectuate.
     * @return
     */
    public Double calculateTotalPrice(){
        Double totalPrice = 0d;
        for(Order order : this.restaurantOrders){
            totalPrice += this.calculateOrderPrice(order.getId());
        }
        return totalPrice;

    }

    /**
     * Metoda calculateOrderPrice primeste id-ul comenzii si returneaza costul total al comenzii respective
     * @param id
     * @return
     */
    public Double calculateOrderPrice(int id){
        for(Order order : this.restaurantOrders){
            if(order.getId() == id){
                return Order.calculateTotal(order);
            }
        }
        return null;
    }

    /**
     * Metoda addProductToMenu este folosita pentru adaugarea unui produs in meniu.
     * @param newProductType
     * @param newProductName
     * @param newProductPrice
     * @param newProductQuantity
     * @throws IOException
     */
    public void addProductToMenu(String newProductType, String newProductName, Double newProductPrice, int newProductQuantity) throws IOException {
        Product product = new Product(newProductName, newProductQuantity, newProductPrice);
        Json.addProductToJson(newProductType, product, Path.of("src\\meniu.json"));
    }

    /**
     * Metoda removeProductFromMenu este folosita pentru a sterge un produs din meniu.
     * @param productNameToDelete
     * @throws IOException
     */

    public void deleteProductFromMenu(String productNameToDelete) throws IOException {
        Product product = getProductFromMenu(productNameToDelete);
        if(product == null){
            throw new IllegalArgumentException("Produsul nu exista in meniu.");
        }
        Json.removeProductFromJson(productNameToDelete, "src\\meniu.json");

    }

    /**
     * Metoda updatePrice este folosita pentru modificarea pretului unui produs din meniu.
     * @param oldProductName
     * @param newPrice
     * @throws IOException
     */
    public void updatePrice(String oldProductName, double newPrice) throws IOException {
        Product product = getProductFromMenu(oldProductName);
        if(product == null){
            throw new IllegalArgumentException("Produsul nu exista in meniu");
        }
        Product newProduct = new Product(oldProductName, product.getQuantity(), newPrice);
        Json.updatePrice(oldProductName, newProduct, "src\\meniu.json");

    }

    /**
     * Metoda updateQuantity este folosita pentru modificarea cantitatii unui produs din meniu.
     * @param oldProductName
     * @param newQuantity
     * @throws IOException
     */
    public void updateQuantity(String oldProductName, int newQuantity) throws IOException {
        Product product = getProductFromMenu(oldProductName);
        if(product == null){
            throw new IllegalArgumentException("Produsul nu exista in meniu");
        }
        Product newProduct = new Product(oldProductName, newQuantity, product.getPrice());
        Json.updateQuantity(oldProductName, newProduct, "src\\meniu.json");
    }

    /**
     * Metoda refreshProductStock este utilizata pentru modificarea cantitatii disponibile la nivelul restaurantului dintr-un anumit produs cand este efectuata o comanda.
     * @param productName
     * @param quantity
     * @throws IOException
     */
    public void refreshProductStock(String productName, int quantity) throws IOException {
        Product product = getProductFromMenu(productName);
        if(product == null){
            throw new IllegalArgumentException("Produsul nu exista in meniu");
        }
        Json.refreshMenuStock(productName, quantity, "src\\meniu.json");
    }

    /**
     * Metoda generateZ este folosita pentru generarea unui raport fiscal Z intr-un fisier text.
     * @param orders
     * @throws IOException
     */
    public void generateZ(List<Order> orders) throws IOException {
        String fileName = "Raport-Z-" + LocalDate.now().toString() + ".txt";
        File file = new File(fileName);
        if(file.createNewFile()){
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Order order : orders){
                bufferedWriter.write(order.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }

    }
}
