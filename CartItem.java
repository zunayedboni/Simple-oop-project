
import java.util.UUID;

public class CartItem {
    private final String productId;
    private final String name;
    private final double unitPrice;
    private int quantity;

    public CartItem(String name, double unitPrice, int quantity) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative.");
        }
        this.productId = UUID.randomUUID().toString();  // Auto-generate unique ID
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }


    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        } else {
            System.out.println("Quantity cannot be less than 1.");
        }
    }

    public double getTotalPrice() {
        return this.unitPrice * this.quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Unit Price: %.2f | Quantity: %d | Total: %.2f",
                productId, name, unitPrice, quantity, getTotalPrice());
    }
}
