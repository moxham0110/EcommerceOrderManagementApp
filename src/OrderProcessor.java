import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class OrderProcessor {

    ExecutorService executorService;

    public void processOrders(List<Order> orders) {
        // Filter out the PENDING orders
        List<Order> pendingOrders = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .collect(Collectors.toList());

        if (pendingOrders.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }

        // ExecutorService
        executorService = Executors.newFixedThreadPool(8);
        List<Callable<Void>> tasks = new ArrayList<>();

        // Create a list of Callables to submit to ExecutorService
        for (Order order : pendingOrders) {
            tasks.add(() -> {
                try {
                    order.processOrder();
                    System.out.println("Processed order ID: " + order.getOrderID());
                } catch (IllegalStateException e) {
                    System.out.println("Failed to process order ID " + order.getOrderID() + ": " + e.getMessage());
                }
                return null; // Return null for Void callable
            });
        }

        try {
            // Execute all tasks in parallel
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Shutdown
            executorService.shutdown();
        }
    }

}
