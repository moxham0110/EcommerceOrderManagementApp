import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AppService {

    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        readCustomerData();
        readOrderData();
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
                customers.add(cust);
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
                Customer orderCust = customers.stream()
                            .filter(cust -> cust.customerID == Integer.parseInt(values[0]))
                            .findFirst().orElseThrow(IllegalStateException::new);

                LocalDateTime orderTimestamp = LocalDateTime.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                Order order = new Order(orderCust, values[1], orderTimestamp);
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}