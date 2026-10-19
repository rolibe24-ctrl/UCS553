package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.service.OrderUtility;
import food.utility.OrderCounter;

public class Main {

    public static void main(String[] args) {

        // Set restaurant name
        FoodOrder.setRestaurantName("FoodExpress");

        System.out.println("====================================");
        System.out.println("       " + FoodOrder.getRestaurantName());
        System.out.println("       ONLINE FOOD DELIVERY");
        System.out.println("====================================");

        // Create six orders
        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "Raman", 500);
        orders[1] = new PremiumOrder(102, "Aman", 800);
        orders[2] = new RegularOrder(103, "Rahul", 650);
        orders[3] = new PremiumOrder(104, "Priya", 1000);
        orders[4] = new RegularOrder(105, "Neha", 750);
        orders[5] = new PremiumOrder(106, "Karan", 1200);

        // Display total orders
        FoodOrder.displayTotalOrders();

        // Generate bills
        for (FoodOrder order : orders) {

            // Validate customer name
            if (!OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Invalid customer name!");
                continue;
            }

            // Validate amount
            if (!OrderUtility.validateAmount(order.getAmount())) {
                System.out.println("Invalid order amount!");
                continue;
            }

            // Display order summary
            OrderUtility.generateOrderSummary(order);
        }

        OrderCounter.printLine();
        System.out.println("All orders processed successfully.");
    }
}