import java.time.LocalDateTime;

public class Payment {
    int paymentID;
    Order order;
    double amount;
    PaymentMethod method;
    LocalDateTime timestamp;
}
