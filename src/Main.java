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

        Order c1 = new Order();
//        c1.addProduct(new Product("aaaa", 2,5d, ProductType.MANCARE));
//        c1.addProduct(new Product("ssss", 3,5d, ProductType.MANCARE));
//        c1.addProduct(new Product("dddd", 4,5d, ProductType.MANCARE));
        System.out.println(restaurant.getMenu());
        System.out.println(c1);
        System.out.println(Order.calculateTotal(c1));


    }
}
