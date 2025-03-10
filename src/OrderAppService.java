import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class OrderAppService {

    private static Scanner input = new Scanner(System.in);
    public static Locale appLocale = Locale.getDefault();

    public static void main(String[] args) {
        OrderAppService app = new OrderAppService();

        for (int _ : new int[2]) {
            System.out.println("-----------WELCOME TO ORDER MANAGEMENT---------");
        }

        // Application title
        System.out.println("\n\n\n" +
                "   ____          _             __  __                                                   _      _____           _                 \n" +
                "  / __ \\        | |           |  \\/  |                                                 | |    / ____|         | |                \n" +
                " | |  | |_ __ __| | ___ _ __  | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  | (___  _   _ ___| |_ ___ _ __ ___  \n" +
                " | |  | | '__/ _` |/ _ \\ '__| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|  \\___ \\| | | / __| __/ _ \\ '_ ` _ \\ \n" +
                " | |__| | | | (_| |  __/ |    | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_   ____) | |_| \\__ \\ ||  __/ | | | | |\n" +
                "  \\____/|_|  \\__,_|\\___|_|    |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |_____/ \\__, |___/\\__\\___|_| |_| |_|\n" +
                "                                                         __/ |                                        __/ |                      \n" +
                "                                                        |___/                                        |___/                       \n");

        AppData.loadData();  // Assumes AppData class is managing the loading of data
        app.displayMainMenu();  // Display the main menu after loading data
    }

    public void displayMainMenu() {
        System.out.println("-----------------------------------");
        System.out.println("Main Menu");
        System.out.println("-----------------------------------");
        System.out.println("1. Process Orders");
        System.out.println("2. Streams Example Menu");
        System.out.println("3. Order Menu");
        System.out.println("4. Change Locale");
        System.out.println("5. Take Payments");
        System.out.println("6. Exit");
        System.out.print("Please select an option: ");

        int option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1 -> processAllOrders();
            case 2 -> displayStreamsExampleMenu();
            case 3 -> displayOrderMenu();
            case 4 -> selectLocale();
            case 5 -> takePaymentsForPendingOrders();
            case 6 -> {
                System.out.println("Exiting... Goodbye!");
                return;
            }
            default -> {
                System.out.println("Invalid option. Please try again.");
                displayMainMenu();
            }
        }
    }

    // Process all orders (example for Option 1)
    public void processAllOrders() {
        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.processOrders(AppData.allOrders);

        displayMainMenu();
    }

    // Streams Example Menu with the stream-based operations
    private void displayStreamsExampleMenu() {
        StreamsExamples streams = new StreamsExamples();
        System.out.println("\nStreams Example Menu:");
        System.out.println("1. Get All Customer Names Sorted");
        System.out.println("2. Get Cheapest Product");
        System.out.println("3. Get Number of Available Products");
        System.out.println("4. Get Random Delivered Order");
        System.out.println("5. Get First Ever Order Made");
        System.out.println("6. Check if There is an Order Above 300");
        System.out.println("7. Display Customer Emails");
        System.out.println("8. Get Map of Products By Name");
        System.out.println("9. Group Orders By Status");
        System.out.println("10. Partition Orders to Cheap and Expensive");
        System.out.println("11. Display Unique Product Categories");
        System.out.println("12. Limit First 3 Products");
        System.out.println("13. Back to Main Menu");

        System.out.print("Please choose an option: ");
        int option = input.nextInt();
        input.nextLine();  // Consume the newline left by nextInt()

        switch (option) {
            case 1:
                streams.getAllCustomerNamesSorted();
                break;
            case 2:
                streams.getCheapestProduct();
                break;
            case 3:
                streams.getAvailableProducts();
                break;
            case 4:
                streams.getRandomDeliveredOrder();
                break;
            case 5:
                streams.getFirstEverOrderMade();
                break;
            case 6:
                streams.isOrderAbove300();
                break;
            case 7:
                streams.displayCustomerEmails();
                break;
            case 8:
                streams.getMapOfProductsByName();
                break;
            case 9:
                streams.groupOrdersByStatus();
                break;
            case 10:
                streams.partitionOrdersToCheapAndExpensive();
                break;
            case 11:
                streams.displayUniqueProductCategories();
                break;
            case 12:
                streams.limitFirst3Products();
                break;
            case 13:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                displayStreamsExampleMenu();
                break;
        }

        displayStreamsExampleMenu();
    }

    // Order Menu to display orders, take payment and ship orders
    private void displayOrderMenu() {
        System.out.println("\nOrder Menu:");
        System.out.println("Select an Order by ID:");

        // Display all orders by ID
        for (Order order : AppData.allOrders) {
            System.out.println("Order ID: " + order.getOrderID() + " --- Status: " + order.getStatus());
        }

        System.out.print("Enter Order ID to select an order (-1 for main menu) : ");
        int orderId = input.nextInt();
        input.nextLine();

        if(orderId == -1){
            displayMainMenu();
        }

        // Find the order with the given ID
        Optional<Order> selectedOrder = AppData.allOrders.stream()
                .filter(order -> order.getOrderID() == orderId)
                .findFirst();

        if (selectedOrder.isPresent()) {
            Order order = selectedOrder.get();
            System.out.println("Selected Order:");
            StreamsExamples.displayOrderDetails(order);

            // Display options for payment and shipping
            System.out.println("1. Process Payment");
            System.out.println("2. Ship Order");
            System.out.println("3. Back to Main Menu");

            System.out.print("Please select an option: ");
            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    processPayment(order);
                    break;
                case 2:
                    shipOrder(order);
                    break;
                case 3:
                    displayMainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    displayOrderMenu();
                    break;
            }
        } else {
            System.out.println("Order not found. Please try again.");
            displayOrderMenu();
        }
    }

    // Process payment for the selected order
    private void processPayment(Order order) {
        System.out.print("Enter payment method (Cash, VisaCard, MasterCard): ");
        String paymentMethod = input.nextLine();

        order.takePayment(paymentMethod);
        System.out.println("Payment taken for Order ID: " + order.getOrderID() + " using " + paymentMethod);
        displayOrderMenu();
    }

    // Ship the selected order
    private void shipOrder(Order order) {
        order.shipOrder();
        System.out.println("Order ID: " + order.getOrderID() + " has been shipped.");
        displayOrderMenu();
    }

    private void selectLocale() {
        System.out.println("\nSelect a Locale:");
        System.out.println("1. GBP (United Kingdom)");
        System.out.println("2. Euro (Ireland)");
        System.out.println("3. USD (United States)");
        System.out.println("4. CNY (China)");
        System.out.println("5. AUD (Australia)");
        System.out.println("6. Back to Main Menu");
        System.out.print("Please choose an option: ");

        int localeOption = input.nextInt();
        input.nextLine();

        // switch expression
        appLocale = switch (localeOption) {
            case 1 -> Locale.UK;
            case 2 -> new Locale("en", "IE");
            case 3 -> Locale.US;
            case 4 -> Locale.CHINA;
            case 5 -> new Locale("en", "AU");
            default -> {
                System.out.println("Invalid option. Please try again.");
                yield appLocale;
            }
        };

        System.out.println("Locale set to: " + appLocale.getDisplayName());
        displayMainMenu();
    }

    private void takePaymentsForPendingOrders() {
        List<String> paymentMethods = List.of("Cash", "VisaCard", "MasterCard", "BankCard");
        Random random = new Random();

        List<Order> unpaidOrders = AppData.allOrders.stream()
                .filter(order -> order.payment == null)
                .collect(Collectors.toList());

        if (unpaidOrders.isEmpty()) {
            System.out.println("All orders already have payments.");
            return;
        }

        for (Order order : unpaidOrders) {
            String randomPaymentMethod = paymentMethods.get(random.nextInt(paymentMethods.size()));
            try {
                order.takePayment(randomPaymentMethod);
                System.out.println("Payment received for Order ID " + order.getOrderID() + " using " + randomPaymentMethod);
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to process payment for Order ID " + order.getOrderID() + ": " + e.getMessage());
            }
        }

        displayMainMenu();
    }
}



