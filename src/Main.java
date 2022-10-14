import models.Order;
import models.Restaurant;
import utils.Program;

public class Main {
    public static void main(String[] args) {
        //Program.startProgram();
        Restaurant restaurant = Restaurant.getInstance();

        System.out.println("Bine ati venit la restaurantul " + restaurant.getName()+"!\n");
        restaurant.showPrettyMenu();

        restaurant.addProductToOrder("Pizza Napoli", 2);
        restaurant.addProductToOrder("Pizza Margarita", 9);

        restaurant.getOrders();
//        Program.startProgram();

    }
}
