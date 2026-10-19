
import java.util.Objects;
import java.util.Optional;

class GenericUtility {

    // 1. Check whether two objects are equal
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    // 2. Return value or default
    public static <T> T getDefault(
            Optional<T> value, T defaultValue) {

        return Objects.requireNonNull(value)
                      .orElse(defaultValue);
    }

    // 3. Find the larger of two comparable objects
    public static <T extends Comparable<T>> T max(T a, T b) {

        Objects.requireNonNull(a, "First value cannot be null");
        Objects.requireNonNull(b, "Second value cannot be null");

        return a.compareTo(b) >= 0 ? a : b;
    }

    // 4. Check whether an object is null
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

class Q5 {

    public static void main(String[] args) {

        // Test isEqual()
        System.out.println("Strings equal: " +
                GenericUtility.isEqual("Java", "Java"));

        System.out.println("Integers equal: " +
                GenericUtility.isEqual(10, 20));

        // Test getDefault()
        Optional<String> email =
                Optional.of("student@example.com");

        Optional<String> empty =
                Optional.empty();

        System.out.println("Email: " +
                GenericUtility.getDefault(
                        email, "default@example.com"));

        System.out.println("Default value: " +
                GenericUtility.getDefault(
                        empty, "default@example.com"));

        // Test max()
        System.out.println("Maximum integer: " +
                GenericUtility.max(15, 25));

        System.out.println("Maximum string: " +
                GenericUtility.max("Apple", "Banana"));

        System.out.println("Maximum double: " +
                GenericUtility.max(12.5, 9.8));

        // Test isNull()
        String name = null;
        String language = "Java";

        System.out.println("Name is null: " +
                GenericUtility.isNull(name));

        System.out.println("Language is null: " +
                GenericUtility.isNull(language));
    }
}
