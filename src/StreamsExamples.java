import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsExamples {

    // map() and sorted()
    public void getAllCustomerNamesSorted(){
        List<String> customerNames = AppData.allCustomers.stream()
                .map(Customer::getName)
                .sorted()
                .toList();

        System.out.println("List of Customer Names:\n");
        System.out.println(customerNames);
    }

    // min() and Comparator.comparing()
    public void getCheapestProduct(){
        Product cheapestProduct = AppData.allProducts.stream()
                .min(Comparator.comparing(Product::getPrice)).orElseThrow();

        System.out.println("Product: " + cheapestProduct.name + " -- Price: " + cheapestProduct.getPrice());
    }

    // filter() and count()
    public void getAvailableProducts(){
        long availableProducts = AppData.allProducts.stream()
                .filter(p -> p.stockCount > 0)
                .count();

        System.out.println("Number of Available Products: " + availableProducts);
    }

    // findAny() and Predicate filter()
    public void getRandomDeliveredOrder() {
        Optional<Order> randomDeliveredOrder = AppData.allOrders.stream()
                .filter(o -> o.getStatus().equals(OrderStatus.DELIVERED))
                .findAny();

        System.out.println("----- Random Delivered Order -----");
        randomDeliveredOrder.ifPresent(order -> { displayOrderDetails(order);});
    }

    // findFirst() sorted()
    public void getFirstEverOrderMade(){
        Optional<Order> firstOrder = AppData.allOrders.stream()
                .sorted(Comparator.comparing(Order::getOrderTime))
                .findFirst();

        System.out.println("----- First Order Ever Made -----");
        firstOrder.ifPresent(order -> displayOrderDetails(order));
    }

    // anyMatch()
    public void isOrderAbove300(){
        boolean hasBigOrder = AppData.allOrders.stream()
                .anyMatch(o -> o.getTotalCost() > 300);

        System.out.println("Is there an order above 300? " + hasBigOrder);
    }

    // map() and forEach()
    public void displayCustomerEmails(){
        AppData.allCustomers.stream()
                .map(Customer::getEmail)
                .forEach(System.out::println);
    }

    // Collectors.toMap()
    public void getMapOfProductsByName() {
        Map<String, Product> productMap = AppData.allProducts.stream()
                .collect(Collectors.toMap(Product::getName, product -> product));

        System.out.println("Product Map:");
        productMap.forEach((name, product) -> {
            System.out.println(name + "->" + product.getPrice());
        });
    }

    // Collectors.groupingBy()
    public void groupOrdersByStatus(){
        Map<OrderStatus, List<Order>> ordersByStatus = AppData.allOrders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));

        // Print the grouped orders with their orderIDs
        ordersByStatus.forEach((status, orders) -> {
            System.out.print(status + ": ");
            orders.forEach(order -> System.out.print(order.getOrderID() + " "));
            System.out.println();
        });
    }

    // Collectors.partitioningBy()
    public void partitionOrdersToCheapAndExpensive(){
        Map<Boolean, List<Order>> partitionedOrders = AppData.allOrders.stream()
                .collect(Collectors.partitioningBy(o -> o.getTotalCost() > 300));

        // Print the partitioned orders with their orderIDs
        System.out.print("Expensive orders (Over 300): ");
        partitionedOrders.get(true).forEach(order -> System.out.print(order.getOrderID() + " "));
        System.out.println();

        System.out.print("Cheap orders (300 and below): ");
        partitionedOrders.get(false).forEach(order -> System.out.print(order.getOrderID() + " "));
        System.out.println();
    }

    // distinct()
    public void displayUniqueProductCategories(){
        List<String> categories = AppData.allProducts.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();

        System.out.println(categories);
    }

    // limit()
    public void limitFirst3Products(){
        List<Product> top3Products = AppData.allProducts.stream()
                .limit(3)
                .toList();

        top3Products.forEach(product -> System.out.println(product.getName()));

    }

    // displayOrderDetails() method
    public static void displayOrderDetails(Order order){
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Customer: " + order.customer.getName());

        // Localized Date formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss", OrderAppService.appLocale);
        String formattedDate = order.getOrderTime().format(formatter);
        System.out.println("Order Date: " + formattedDate);

        // Displaying items and costs with localisation format
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(OrderAppService.appLocale);
        double totalCost = 0;
        System.out.println("Items in the order:");
        for (OrderItem item : order.items) {
            Product product = item.getProduct();
            double itemCost = product.getPrice() * item.getQuantity();
            totalCost += itemCost;

            System.out.println("- " + item.getQuantity() + " x " + product.getName() + " @ "
                    + currencyFormatter.format(product.getPrice()) + " each = " + currencyFormatter.format(itemCost));
        }

        System.out.println("Total cost: " + currencyFormatter.format(totalCost));
    }
}
