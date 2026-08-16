package food.model;

public abstract class FoodOrder {

    private int orderId;
    private String customerName;
    private double amount;

    // Static restaurant name
    private static String restaurantName = "FoodExpress";

    // Static object counter
    private static int orderCount = 0;

    // Constructor
    public FoodOrder(int orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        orderCount++;
    }

    // Getter and setter for orderId
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Getter and setter for customerName
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter and setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and setter for restaurantName
    public static String getRestaurantName() {
        return restaurantName;
    }

    public static void setRestaurantName(String restaurantName) {
        FoodOrder.restaurantName = restaurantName;
    }

    // Static method to display total orders
    public static void displayTotalOrders() {
        System.out.println("Total Orders: " + orderCount);
    }

    // Abstract method
    public abstract double calculateDeliveryCharge();
}