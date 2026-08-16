package library.main;

import library.model.LibraryResource;
import library.model.Book;
import library.model.DigitalResource;
import library.model.Printable;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {

        // Set library name
        LibraryResource.setLibraryName("Smart University Library");

        System.out.println("======================================");
        System.out.println("       " + LibraryResource.getLibraryName());
        System.out.println("    RESOURCE MANAGEMENT SYSTEM");
        System.out.println("======================================");

        // Create resources
        LibraryResource[] resources = new LibraryResource[6];

        resources[0] = new Book(101, "Java Programming", "James Gosling");
        resources[1] = new DigitalResource(102, "Python Course", "Guido van Rossum");
        resources[2] = new Book(103, "Data Structures", "Mark Allen");
        resources[3] = new DigitalResource(104, "Machine Learning", "Tom Mitchell");
        resources[4] = new Book(105, "Database Systems", "Raghu Ramakrishnan");
        resources[5] = new DigitalResource(106, "Cloud Computing", "Thomas Erl");

        // Display total resources
        LibraryResource.displayTotalResources();

        // Overdue days for each resource
        int[] overdueDays = {5, 10, 3, 7, 0, 4};

        double totalFine = 0;

        System.out.println("\nRESOURCE DETAILS");

        // Process all resources
        for (int i = 0; i < resources.length; i++) {

            // Validate resource ID
            if (!InputValidator.validateResourceId(
                    resources[i].getResourceId())) {

                System.out.println("Invalid Resource ID!");
                continue;
            }

            // Validate overdue days
            if (!InputValidator.validateFineDays(overdueDays[i])) {

                System.out.println("Invalid overdue days!");
                continue;
            }

            // Print details
            Printable printable = (Printable) resources[i];
            printable.printDetails();

            System.out.println("Overdue Days : " + overdueDays[i]);

            // Calculate fine
            double fine = resources[i].calculateFine(overdueDays[i]);

            System.out.println("Fine         : Rs. " + fine);

            totalFine += fine;
        }

        System.out.println("-----------------------------------");
        System.out.println("TOTAL FINE   : Rs. " + totalFine);
        System.out.println("===================================");
    }
}