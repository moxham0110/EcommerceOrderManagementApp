public class OrderItem {
    private Order order;
    private int itemID;
    private Product product;
    private int quantity;
    static int itemIDCounter = 10000;

    public Product getProduct() {
        return product;
    }

    public OrderItem(Order order, int quantity, Product product) {
        this.itemID = itemIDCounter++;
        this.order = order;
        this.quantity = quantity;
        this.product = product;
    }

    public double getItemSubTotal() {
        return quantity * product.getPrice();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
