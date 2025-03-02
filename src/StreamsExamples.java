import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsExamples {

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

    //findAny() and Predicate filter()
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
