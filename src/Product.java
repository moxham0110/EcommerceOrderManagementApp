import java.util.Random;

public class Product {
    int productID;
    String name;
    private double price;
    String category;
    int stockCount;
    static Random r = new Random();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

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

    public String getCategory() {
        return category;
    }
}
