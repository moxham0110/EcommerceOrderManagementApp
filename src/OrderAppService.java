import java.util.*;
import java.util.stream.Collectors;

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

    //findAny() and Consumer Predicate filter()
    private void getRandomDeliveredOrder(){
        Optional<Order> randomDeliveredOrder = AppData.allOrders.stream()
                .filter(o -> o.status.equals(OrderStatus.DELIVERED))
                .findAny();

        //todo: Improve messaging here - give order items and costs
        randomDeliveredOrder.ifPresent(order -> System.out.println("Random order:\nID: " + order.orderID));
    }

    //findFirst() sorted()
    private void getFirstEverOrderMade(){
        Optional<Order> firstOrder = AppData.allOrders.stream()
                .sorted(Comparator.comparing(Order::getOrderTime))
                .findFirst();

        //todo: Localisation - print different region date types
        firstOrder.ifPresent(order -> System.out.println("First order made on:" + order.orderTime));
    }

    //anyMatch()
    private void isOrderAbove300(){
        boolean hasBigOrder = AppData.allOrders.stream()
                .anyMatch(o -> o.getTotalCost() > 300);

        System.out.println("Is there an order above 300? " + hasBigOrder);
    }

    //map() and forEach()
    private void displayCustomerEmails(){
        AppData.allCustomers.stream()
                .map(Customer::getEmail)
                .forEach(System.out::println);
    }

    //Collectors.toMap()
    private void getMapOfOrders(){
        Map<Integer, Order> orderMap = AppData.allOrders.stream()
                .collect(Collectors.toMap(Order::getOrderID, o -> o));

        System.out.println(orderMap);
    }

    //Collectors.groupingBy()
    private void groupOrdersByStatus(){
        Map<OrderStatus, List<Order>> ordersByStatus = AppData.allOrders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));

        System.out.println(ordersByStatus);
    }

    //Collectors.partitioningBy()
    private void partitionOrdersToCheapAndExpensive(){
        Map<Boolean, List<Order>> partitionedOrders = AppData.allOrders.stream()
                .collect(Collectors.partitioningBy(o -> o.getTotalCost() > 300));

        System.out.println("Expensive orders: " + partitionedOrders.get(true));
        System.out.println("Cheap orders: " + partitionedOrders.get(false));
    }

    //distinct()
    private void displayUniqueProductCategories(){
        List<String> categories = AppData.allProducts.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();

        System.out.println(categories);
    }

    //limit()
    private void limitFirst3Products(){
        List<Product> top3Products = AppData.allProducts.stream()
                .limit(3)
                .toList();

        System.out.println(top3Products);
    }




}
