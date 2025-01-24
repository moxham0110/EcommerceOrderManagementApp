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

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }


}
