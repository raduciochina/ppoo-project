package utils;

import models.Order;
import models.Restaurant;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Clasa folosita pentru a porni programul in linia de comanda, prin metoda startProgram() se afiseaza meniul si se
 * asteapta input-ul utilizatorului pentru a se executa actiunile dorite.
 */

public class Program {
    public static void startProgram() throws IOException {
        Restaurant restaurant = Restaurant.getInstance();

        System.out.println("Bine ati venit la restaurantul " + restaurant.getName() + "!\n");
        System.out.println("---------------------------------------------------------------------------");
        restaurant.showPrettyMenu();

        int option = 1;

        while (option != 0) {
            System.out.println("Pentru a comanda apasati tasta 1.");
            System.out.println("Pentru interfata administrator apasati tasta 2.");
            System.out.println("Apasati 0 pentru a inchide aplicatia");
            option = new Scanner(System.in).nextInt();
            switch (option) {
                case 0:
                    System.out.println("Va multumim ca ati ales restaurantul nostru!");
                    break;
                case 1:
                    System.out.println("Introduceti numele produsului pe care doriti sa il comandati: ");
                    String productName = new Scanner(System.in).nextLine();
                    System.out.println("Introduceti cantitatea dorita: ");
                    int quantity = new Scanner(System.in).nextInt();
                    restaurant.addProductToOrder(productName, quantity);
                    System.out.println("Produs adaugat, daca doriti sa mai comandati scrieti 'da', altfel scrieti 'nu'.");


                    while (new Scanner(System.in).nextLine().equals("da")) {
                        System.out.println("Introduceti numele produsului pe care doriti sa il comandati: ");
                        String productName2 = new Scanner(System.in).nextLine();
                        System.out.println("Introduceti cantitatea dorita: ");
                        int quantity2 = new Scanner(System.in).nextInt();
                        restaurant.addProductToExistingOrder(Order.getCounter(), productName2, quantity2);
                        System.out.println("Produs adaugat, daca doriti sa mai comandati scrieti 'da', altfel scrieti 'nu'.");
                        if (new Scanner(System.in).nextLine().equals("nu")) {
                            System.out.println("Comanda dumneavoastra este: ");
                            restaurant.getOrderById(Order.getCounter());
                            System.out.println("Totalul comenzii este: " + restaurant.calculateOrderPrice(Order.getCounter()) + " lei.");
                            break;
                        }
                    }
                    break;
                case 2:
                    String password = "admin";
                    System.out.println("Introduceti parola de administrator: ");
                    String inputPassword = new Scanner(System.in).nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("Bine ati venit, administrator!");
                        System.out.println("Pentru a adauga un produs in meniu apasati tasta 1.");
                        System.out.println("Pentru a sterge un produs din meniu apasati tasta 2.");
                        System.out.println("Pentru a modifica un produs din meniu apasati tasta 3.");
                        System.out.println("Pentru a afisa toate comenzile apasati tasta 4.");
                        System.out.println("Pentru a afisa toate produsele din meniu apasati tasta 5.");
                        System.out.println("Pentru a afisa toate produsele dintr-o comanda apasati tasta 6.");
                        System.out.println("Pentru a afisa pretul unei comenzi apasati tasta 7.");
                        System.out.println("Pentru a afisa pretul total al comenzilor apasati tasta 8.");
                        System.out.println("Pentru a iesi apasati tasta 0.");
                        int option2 = new Scanner(System.in).nextInt();
                        switch (option2) {
                            case 0:
                                System.out.println("La revedere admin!");
                                break;
                            case 1:
                                System.out.println("Introduceti tipul produsului (FOOD or DRINK):");
                                String newProductType = new Scanner(System.in).nextLine();
                                System.out.println("Introduceti numele produsului pe care doriti sa il adaugati: ");
                                String newProductName = new Scanner(System.in).nextLine();
                                System.out.println("Introduceti pretul produsului: ");
                                double newProductPrice = new Scanner(System.in).nextDouble();
                                System.out.println("Introduceti cantitatea produsului: ");
                                int newProductQuantity = new Scanner(System.in).nextInt();
                                restaurant.addProductToMenu(newProductType, newProductName, newProductPrice, newProductQuantity);
                                System.out.println("Produsul a fost adaugat cu succes!");
                                break;
                            case 2:
                                System.out.println("Introduceti numele produsului pe care doriti sa il stergeti: ");
                                String productNameToDelete = new Scanner(System.in).nextLine();
                                restaurant.deleteProductFromMenu(productNameToDelete);
                                System.out.println("Produsul a fost sters cu succes!");
                                restaurant.initMeniu();
                                break;
                            case 3:
                                System.out.println("Introduceti numele produsului pe care doriti sa il modificati: ");
                                String oldProductName = new Scanner(System.in).nextLine();
                                System.out.println("Introduceti noul pret al produsului: ");
                                double newPrice = new Scanner(System.in).nextDouble();
                                restaurant.updatePrice(oldProductName, newPrice);
                                System.out.println("Produs modificat cu succes!");
                                break;
                            case 4:
                                System.out.println("Toate comenzile: ");
                                List<Order> orders = restaurant.getOrderList();
                                for (Order o : orders) {
                                    System.out.println("Order nr. " + o.getId());
                                    System.out.println("Total: " + o.calculateOrderPrice() + " lei");
                                    System.out.println("TVA: " + o.calculateTVA() + " lei");
                                }
                                break;
                            case 5:
                                restaurant.showPrettyMenu();
                                break;
                            case 6:
                                System.out.println("Introduceti numarul comenzii pe care doriti sa o vizualizati: ");
                                int orderNumber = new Scanner(System.in).nextInt();
                                restaurant.getOrderById(orderNumber);
                                break;
                            case 7:
                                System.out.println("Introduceti id-ul comenzii pentru care doriti sa aflati pretul: ");
                                int id = new Scanner(System.in).nextInt();
                                System.out.println("Pretul comenzii este: " + restaurant.calculateOrderPrice(id) + " lei.");
                                break;
                            case 8:
                                System.out.println(restaurant.calculateTotalPrice() + " lei");
                                break;
                            default:
                                System.out.println("Va rugam sa alegeti o optiune valida!");
                        }
                    } else {
                        System.out.println("Parola gresita!");
                    }
            }
        }
    }
}