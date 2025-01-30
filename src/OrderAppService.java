import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OrderAppService {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        OrderAppService app = new OrderAppService();
        //Application title
        System.out.println("\n\n\n" +
                "   ____          _             __  __                                                   _      _____           _                 \n" +
                "  / __ \\        | |           |  \\/  |                                                 | |    / ____|         | |                \n" +
                " | |  | |_ __ __| | ___ _ __  | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  | (___  _   _ ___| |_ ___ _ __ ___  \n" +
                " | |  | | '__/ _` |/ _ \\ '__| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|  \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ \n" +
                " | |__| | | | (_| |  __/ |    | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_   ____) | |_| \\__ \\ ||  __/ | | | | |\n" +
                "  \\____/|_|  \\__,_|\\___|_|    |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |_____/ \\__, |___/\\__\\___|_| |_| |_|\n" +
                "                                                         __/ |                                        __/ |                      \n" +
                "                                                        |___/                                        |___/                       \n");

        AppData.loadData();
        app.displayMainMenu();

    }

    public void displayMainMenu(){
        System.out.println("-----------------------------------");
        System.out.println("Main Menu");
        System.out.println("-----------------------------------");

        getCheapestProduct();
        getAvailableProducts();
    }

    //map() and sorted()
    private void getAllCustomerNamesSorted(){
        List<String> customerNames = AppData.allCustomers.stream()
                .map(Customer::getName)
                .sorted()
                .toList();

        System.out.println("List of Customer Names:\n");
        System.out.println(customerNames);
    }

    //min() and Comparator.comparing()
    private void getCheapestProduct(){
        Product cheapestProduct = AppData.allProducts.stream()
                .min(Comparator.comparing(Product::getPrice)).orElseThrow();

        System.out.println("Product: " + cheapestProduct.name + " -- Price: " + cheapestProduct.getPrice());
    }


    //filter() and count()
    private void getAvailableProducts(){
        long availableProducts = AppData.allProducts.stream()
                .filter(p -> p.stockCount > 0)
                .count();

        System.out.println("Available Products: " + availableProducts);
    }

    //findAny() and Consumer Predicate
    private void getRandomDeliveredOrder(){
        Optional<Order> randomDeliveredOrder = AppData.allOrders.stream()
                .filter(o -> o.status.equals(OrderStatus.DELIVERED))
                .findAny();

        //todo: Improve messaging here - give order items and costs
        randomDeliveredOrder.ifPresent(order -> System.out.println("Random order:\nID: " + order.orderID));
    }

    //findFirst()
    private void getFirstEverOrderMade(){
        Optional<Order> firstOrder = AppData.allOrders.stream()
                .sorted(Comparator.comparing(Order::getOrderTime))
                .findFirst();

        //todo: Localisation - print different region date types
        firstOrder.ifPresent(order -> System.out.println("First order made on:" + order.orderTime));
    }
}
