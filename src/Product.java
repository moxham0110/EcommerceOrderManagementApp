import java.util.Random;

public class Product {
    int productID;
    String name;
    private double price;
    String category;
    int stockCount;
    static Random r = new Random();

    public Product(int productID, String name, double price, String category) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockCount = r.nextInt(5,20);
    }

    public double getPrice() {
        return price;
    }
}
