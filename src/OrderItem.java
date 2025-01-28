public class OrderItem {
    private Order order;
    private int itemID;
    private Product product;
    private int quantity;
    static int itemIDCounter = 10000;

    public OrderItem(Order order, int quantity, Product product) {
        this.itemID = itemIDCounter++;
        this.order = order;
        this.quantity = quantity;
        this.product = product;
    }

    public double getItemSubTotal() {
        return quantity * product.getPrice();
    }
}
