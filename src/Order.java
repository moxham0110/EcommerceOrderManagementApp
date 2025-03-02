import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Order {
    static int orderIDCounter = 1000;
    int orderID;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    OrderStatus status;
    LocalDateTime orderTime;
    Payment payment;
    Discount discount;

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public OrderStatus getStatus() {
        return status;
    }

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

    //Consumer lambda
    Consumer<Customer> sendEmail = customer ->
            System.out.println("Sending email to " + customer.getEmail());

    public void processOrder(Payment payment) {
        //todo: reduce stockCount for product stockCount - quantity in orderitem
        //todo: If stockCount is below zero then throw exception and cancel order.


        setPayment(payment);
        this.status = OrderStatus.PROCESSED;
        sendEmail.accept(this.customer);

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

    // Function lambda
    private final Function<Double, Double> discountFunction = amount ->
            (this.discount != null) ? amount - this.discount.value() : amount;

    public double applyDiscount(double amount) {
        return discountFunction.apply(amount);
    }
}
