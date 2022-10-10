package utils;

import models.Restaurant;

import java.util.Scanner;

public class Program {
    public static void startProgram(){
        Restaurant restaurant = Restaurant.getInstance("La Radu", "Bd. Compozitorilor 33");

        System.out.println("Bine ati venit la restaurantul " + restaurant.getName()+"!\n");
        Scanner input = new Scanner(System.in);

    }
}
