import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartManager {

    private static final List<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize items (Egg, Milk, Noodles)
        CartItem egg = new CartItem("Egg", 10, 0);
        CartItem milk = new CartItem("Milk", 20, 0);
        CartItem noodles = new CartItem("Noodles", 30, 0);

        // Add them to a catalog list
        List<CartItem> catalog = List.of(egg, milk, noodles);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the E-Commerce Cart Manager ===");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Egg");
            System.out.println("2. Add Milk");
            System.out.println("3. Add Noodles");
            System.out.println("4. View Cart");
            System.out.println("5. Increment Quantity");
            System.out.println("6. Decrement Quantity");
            System.out.println("7. Show Total Payable Amount");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1, 2, 3 -> addItemToCart(catalog.get(choice - 1));
                case 4 -> printCart();
                case 5 -> modifyQuantity(scanner, true);
                case 6 -> modifyQuantity(scanner, false);
                case 7 -> printTotalAmount();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
        scanner.close();
        System.out.println("Thank you for shopping!");
    }

    private static void addItemToCart(CartItem item) {
        for (CartItem c : cart) {
            if (c.getProductId().equals(item.getProductId())) {
                c.incrementQuantity();
                System.out.println(item.getName() + " quantity increased.");
                return;
            }
        }
        CartItem newItem = new CartItem(item.getName(), item.getUnitPrice(), 1);
        cart.add(newItem);
        System.out.println(item.getName() + " added to cart.");
    }

    private static void modifyQuantity(Scanner scanner, boolean increment) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        printCart();
        System.out.print("Enter product ID to modify: ");
        scanner.nextLine(); // clear buffer
        String id = scanner.nextLine();

        for (CartItem c : cart) {
            if (c.getProductId().equals(id)) {
                if (increment) {
                    c.incrementQuantity();
                    System.out.println(c.getName() + " quantity increased.");
                } else {
                    c.decrementQuantity();
                }
                return;
            }
        }
        System.out.println("Item not found in cart!");
    }

    private static void printCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            System.out.println("\n=== Cart Items ===");
            for (CartItem item : cart) {
                System.out.println(item);
            }
        }
    }

    private static void printTotalAmount() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }
        System.out.printf("Total Payable Amount: %.2f%n", total);
    }
}
