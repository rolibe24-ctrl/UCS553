
import java.util.*;

class ShoppingException extends Exception {
    ShoppingException(String msg) {
        super(msg);
    }
}

class ProductNotFoundException extends ShoppingException {
    ProductNotFoundException(String msg) {
        super(msg);
    }
}

class InvalidQuantityException extends ShoppingException {
    InvalidQuantityException(String msg) {
        super(msg);
    }
}

class EmptyCartException extends ShoppingException {
    EmptyCartException(String msg) {
        super(msg);
    }
}

class PaymentException extends ShoppingException {
    PaymentException(String msg) {
        super(msg);
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class ShoppingCartApp {
    static Map<String, Product> products = new HashMap<>();
    static Map<String, Integer> cart = new LinkedHashMap<>();

    static Product searchProduct(String name)
            throws ProductNotFoundException {
        Product p = products.get(name.toLowerCase());

        if (p == null)
            throw new ProductNotFoundException(
                "Product not found: " + name);

        return p;
    }

    static void addToCart(String name, int quantity)
            throws ShoppingException {
        Product p = searchProduct(name);

        if (quantity <= 0)
            throw new InvalidQuantityException(
                "Quantity must be positive.");

        String key = p.name.toLowerCase();
        cart.put(key, cart.getOrDefault(key, 0) + quantity);
        System.out.println("Product added to cart.");
    }

    static void removeFromCart(String name)
            throws ProductNotFoundException {
        String key = name.toLowerCase();

        if (!cart.containsKey(key))
            throw new ProductNotFoundException(
                "Product is not in the cart.");

        cart.remove(key);
        System.out.println("Product removed.");
    }

    static double checkout(double payment)
            throws ShoppingException {
        if (cart.isEmpty())
            throw new EmptyCartException("Cart is empty.");

        double total = 0;

        for (String key : cart.keySet()) {
            Product p = searchProduct(key);
            total += p.price * cart.get(key);
        }

        if (payment < total)
            throw new PaymentException(
                "Insufficient payment. Total: " + total);

        cart.clear();
        return payment - total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        products.put("laptop", new Product("Laptop", 50000));
        products.put("mouse", new Product("Mouse", 500));
        products.put("keyboard", new Product("Keyboard", 1200));

        try {
            System.out.println("Available: Laptop, Mouse, Keyboard");
            System.out.print("Enter product name: ");
            String name = sc.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            addToCart(name, quantity);

            double total = 0;
            for (String key : cart.keySet()) {
                Product p = searchProduct(key);
                total += p.price * cart.get(key);
            }

            System.out.println("Cart total: " + total);
            System.out.print("Enter payment amount: ");
            double payment = Double.parseDouble(sc.nextLine());

            double change = checkout(payment);
            System.out.println("Payment successful.");
            System.out.println("Change: " + change);

        } catch (ProductNotFoundException e) {
            System.out.println("Product error: " + e.getMessage());
        } catch (InvalidQuantityException e) {
            System.out.println("Quantity error: " + e.getMessage());
        } catch (EmptyCartException e) {
            System.out.println("Cart error: " + e.getMessage());
        } catch (PaymentException e) {
            System.out.println("Payment error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Enter valid numeric input.");
        } catch (ShoppingException e) {
            System.out.println("Shopping error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
