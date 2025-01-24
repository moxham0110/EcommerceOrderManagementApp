import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    static int orderIDCounter = 1000;
    int orderID;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    String status;
    LocalDateTime orderTime;

    public Order(Customer customer, String status, LocalDateTime orderTime) {
        this.orderID = orderIDCounter++;
        this.customer = customer;
        this.status = status;
        this.orderTime = orderTime;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }
}
