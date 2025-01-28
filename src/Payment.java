import java.time.LocalDateTime;

public class Payment {
    static int paymentIDCounter = 1;
    int paymentID;
    Order order;
    PaymentMethod method;
    LocalDateTime timestamp;

    public Payment(Order order, PaymentMethod method, LocalDateTime timestamp) {
        this.paymentID = paymentIDCounter++;
        this.order = order;
        this.method = method;
        this.timestamp = timestamp;
    }

    public double getFinalPrice(){
        double totalPrice = 0.0;
        for(var item : order.items){
            totalPrice += item.getItemSubTotal();
        };

        return totalPrice;
    }
}
