import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    static int orderIDCounter = 1000;
    int orderID;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    OrderStatus status;
    LocalDateTime orderTime;
    Payment payment;
    Discount discount;

    public Order(Customer customer, String status, LocalDateTime orderTime) {
        this.orderID = orderIDCounter++;
        this.customer = customer;
        this.status = OrderStatus.valueOf(status);
        this.orderTime = orderTime;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public void processOrder() {
        this.status = OrderStatus.PROCESSED;
    }

    public void shipOrder() {
        this.status = OrderStatus.SHIPPED;
    }

    public void confirmDeliveredOrder() {
        this.status = OrderStatus.DELIVERED;
    }

    public double getTotalCost() {
        double totalCost = 0;
        for(var i : this.items){
            totalCost += i.getItemSubTotal();
        }
        return applyDiscount(totalCost);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public double applyDiscount(double amount) {
        if (this.discount != null)
            return amount - this.discount.value;
        return amount;
    }
}
