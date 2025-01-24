import java.util.ArrayList;
import java.util.List;

public class Customer {
    static int idCount = 1;
    final int customerID;
    String name;
    String email;
    String address;
    List<Order> orders = new ArrayList<>();

    public Customer(String name, String email, String address) {
        this.customerID = idCount;
        idCount++;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
