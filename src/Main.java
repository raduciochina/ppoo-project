import models.Order;
import models.Product;
import models.ProductType;
import models.Restaurant;
import utils.Program;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Program.startProgram();
        Restaurant restaurant = Restaurant.getInstance("La Radu", "Bd. Compozitorilor 33");

        System.out.println("Bine ati venit la restaurantul " + restaurant.getName()+"!\n");
        System.out.println("Meniul " + restaurant.getMenu());

        Order c1 = new Order();
        c1.addProductToOrder(restaurant.getProductFromMenu(ProductType.FOOD, "Pizza Margarita", 2));

        System.out.println(c1);
        System.out.println(Order.calculateTotal(c1));


    }
}
