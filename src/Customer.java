import java.util.ArrayList;
import java.util.List;

public class Customer {
    static int idCount = 1;
    final int customerID;
    String name;
    String email;
    String address;

    public static int getIdCount() {
        return idCount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    List<Order> orders = new ArrayList<>();

    public Customer(String name, String email, String address) {
        this.customerID = idCount;
        idCount++;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
