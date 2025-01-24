import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppService {

    static List<Customer> allCustomers = new ArrayList<>();
    static List<Order> allOrders = new ArrayList<>();
    static List<OrderItem> allOrderItems = new ArrayList<>();
    static List<Product> allProducts = new ArrayList<>();

    public static void main(String[] args) {
        readCustomerData();
        readOrderData();
        readProductsData();
        readOrderItemsData();

        System.out.println(allCustomers.stream().toList());
        System.out.println(allOrders.stream().toList());
        System.out.println(allOrderItems.stream().toList());
        System.out.println(allProducts.stream().toList());
    }

    private static void readCustomerData(){
        Path filePath = Paths.get("src/customers.csv");
        try {
            // Read all lines from the file into a list
            List<String> lines = Files.readAllLines(filePath);

            // Split lines by ; delimiter
            for (String line : lines) {
                var values = line.split(";");
                // Create customers
                Customer cust = new Customer(values[0], values[1], values[2]);
                allCustomers.add(cust);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readOrderData(){
        Path filePath = Paths.get("src/orders.csv");
        try {
            // Read all lines from the file into a list
            List<String> lines = Files.readAllLines(filePath);

            // Split lines by ; delimiter
            for (String line : lines) {
                if (line.equals("customerID;status;orderTime"))
                        continue;

                var values = line.split(";");
                // Create orders
                // Retrieve customer with specific ID
                Customer orderCust = allCustomers.stream()
                            .filter(cust -> cust.customerID == Integer.parseInt(values[0]))
                            .findFirst().orElseThrow(IllegalStateException::new);

                LocalDateTime orderTimestamp = LocalDateTime.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                Order order = new Order(orderCust, values[1], orderTimestamp);
                allOrders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readOrderItemsData(){
        Path filePath = Paths.get("src/orderitems.csv");
        try {
            // Read all lines from the file into a list
            List<String> lines = Files.readAllLines(filePath);

            // Split lines by ; delimiter
            for (String line : lines) {
                // Skip header line
                if (line.equals("orderID;quantity;productID"))
                    continue;

                var values = line.split(";");
                // Create order items
                // Retrieve order and product with specific IDs

                Order itemOrder = allOrders.stream()
                            .filter(order -> order.orderID == Integer.parseInt(values[0]))
                            .findFirst()
                            .orElseThrow(IllegalStateException::new);

                int productID = Integer.parseInt(values[1]);

                Product product = allProducts.stream()
                        .filter(p -> p.productID == Integer.parseInt(values[2]))
                        .findFirst()
                        .orElseThrow(IllegalStateException::new);

                // Create order item, add to Order items list
                OrderItem item = new OrderItem(itemOrder, productID, product);
                allOrderItems.add(item);
                itemOrder.addItem(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readProductsData(){
        Path filePath = Paths.get("src/products.csv");
        try {
            // Read all lines from the file into a list
            List<String> lines = Files.readAllLines(filePath);

            // Split lines by ; delimiter
            for (String line : lines) {
                // Skip header line
                if (line.equals("productID;name;price;category"))
                    continue;

                var values = line.split(";");
                // Create product
                Product product = new Product(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), values[3]);

                allProducts.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}