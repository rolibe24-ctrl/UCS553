
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

class Q4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 1. Accept event details
        System.out.print("Enter event name: ");
        String eventName = sc.nextLine();

        System.out.print("Enter event date (YYYY-MM-DD): ");
        LocalDate eventDate =
                LocalDate.parse(sc.nextLine());

        System.out.print("Enter start time (HH:MM): ");
        LocalTime startTime =
                LocalTime.parse(sc.nextLine());

        System.out.print("Enter venue (leave blank if unknown): ");
        String venueInput = sc.nextLine();

        // 2. Handle optional venue
        Optional<String> venue = venueInput.trim().isEmpty()
                ? Optional.empty()
                : Optional.of(venueInput.trim());

        LocalDate today = LocalDate.now();

        // 3. Display event details
        System.out.println("\n--- Event Details ---");
        System.out.println("Event: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Start Time: " + startTime);

        System.out.println("Venue: " +
                venue.orElse("Venue not specified"));

        // 4. Check event date
        if (eventDate.isAfter(today)) {
            System.out.println("Status: Future event");
        } else if (eventDate.isEqual(today)) {
            System.out.println("Status: Event is today");
        } else {
            System.out.println("Status: Past event");
        }

        // 5. Calculate days remaining
        long daysRemaining =
                ChronoUnit.DAYS.between(today, eventDate);

        System.out.println(
                "Days remaining: " + daysRemaining);

        // 6. Calculate end time after two hours
        LocalTime endTime = startTime.plusHours(2);

        System.out.println("End Time: " + endTime);

        sc.close();
    }
}
