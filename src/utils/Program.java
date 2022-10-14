package utils;

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
                    break;
                default:
                    System.out.println("Va rugam sa alegeti o optiune valida!");
            }
        }

    }
}
