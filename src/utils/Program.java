package utils;

import models.Order;
import models.Restaurant;

import java.util.Scanner;

public class Program {
    public static void startProgram(){
        Restaurant restaurant = Restaurant.getInstance();

        System.out.println("Bine ati venit la restaurantul " + restaurant.getName()+"!\n");
        System.out.println("---------------------------------------------------------------------------");
        restaurant.showPrettyMenu();

        int option = 1;

        while (option != 0){
            System.out.println("Pentru a comanda apasati tasta 1.");
            System.out.println("Apasati 0 pentru a inchide aplicatia");
            option = new Scanner(System.in).nextInt();
            switch (option){
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
                    String answer = new Scanner(System.in).nextLine();
                    while(true){
                        System.out.println("Introduceti numele produsului pe care doriti sa il comandati: ");
                        String productName2 = new Scanner(System.in).nextLine();
                        System.out.println("Introduceti cantitatea dorita: ");
                        int quantity2 = new Scanner(System.in).nextInt();
                        restaurant.addProductToExistingOrder(Order.getCounter(), productName2, quantity2);
                        System.out.println("Produs adaugat, daca doriti sa mai comandati scrieti 'da', altfel scrieti 'nu'.");
                        if(new Scanner(System.in).nextLine().equals("nu")){
                            break;
                        }
                    }
                    restaurant.getOrders();
                    break;
                default:
                    System.out.println("Va rugam sa alegeti o optiune valida!");
            }
        }

    }
}
