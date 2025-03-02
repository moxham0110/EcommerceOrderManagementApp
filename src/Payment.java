import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

public record Payment(String paymentID, Order order, PaymentMethod method, LocalDateTime timestamp) {
    private static final Supplier<String> generatePaymentID = () -> "PAYMENT-" + UUID.randomUUID();

    public Payment(Order order, PaymentMethod method, LocalDateTime timestamp) {
        this(generatePaymentID.get(), order, method, timestamp);
    }
}
