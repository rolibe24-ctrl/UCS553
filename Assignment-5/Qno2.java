
import java.util.Optional;

class Q2 {

    public static void main(String[] args) {

        // 1. Create an Optional containing an email
        Optional<String> email =
                Optional.of("student@example.com");

        // 2. Create an empty Optional
        Optional<String> emptyEmail = Optional.empty();

        // 3. Display email using ifPresent()
        System.out.print("Email: ");
        email.ifPresent(System.out::println);

        // 4. Provide a default email using orElse()
        String defaultEmail =
                emptyEmail.orElse("default@example.com");

        System.out.println("Default email: " + defaultEmail);

        // 5. Handle missing email using orElseThrow()
        try {
            String value = emptyEmail.orElseThrow(
                    () -> new IllegalArgumentException(
                            "Email address is missing")
            );

            System.out.println(value);

        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
