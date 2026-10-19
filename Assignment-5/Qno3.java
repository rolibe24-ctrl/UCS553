
import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {

    private final String studentName;
    private final T[] marks;
    private final double passingThreshold;

    public Result(String studentName, T[] marks,
                  double passingThreshold) {

        this.studentName = Objects.requireNonNull(
                studentName, "Student name cannot be null");

        this.marks = Objects.requireNonNull(
                marks, "Marks cannot be null");

        if (marks.length == 0) {
            throw new IllegalArgumentException(
                    "Marks array cannot be empty");
        }

        if (passingThreshold < 0 || passingThreshold > 100) {
            throw new IllegalArgumentException(
                    "Threshold must be between 0 and 100");
        }

        for (T mark : marks) {
            Objects.requireNonNull(mark, "Mark cannot be null");

            if (mark.doubleValue() < 0 ||
                    mark.doubleValue() > 100) {
                throw new IllegalArgumentException(
                        "Marks must be between 0 and 100");
            }
        }

        this.passingThreshold = passingThreshold;
    }

    // Calculate average marks
    public double calculateAverage() {
        double total = 0;

        for (T mark : marks) {
            total += mark.doubleValue();
        }

        return total / marks.length;
    }

    // Determine pass or fail
    public boolean hasPassed() {
        return calculateAverage() >= passingThreshold;
    }

    // Return average as an Optional
    public Optional<Double> getAverage() {
        return Optional.of(calculateAverage());
    }

    // Display student result
    public void displayResult() {
        System.out.println("Student: " + studentName);

        getAverage().ifPresent(
                average -> System.out.printf(
                        "Average: %.2f%%%n", average));

        System.out.println(
                "Result: " + (hasPassed() ? "PASS" : "FAIL"));
    }
}

class Q3 {
    public static void main(String[] args) {

        Integer[] marks1 = {80, 75, 90, 85};
        Result<Integer> student1 =
                new Result<>("Aman", marks1, 40);

        student1.displayResult();

        System.out.println();

        Double[] marks2 = {35.0, 45.0, 30.0, 40.0};
        Result<Double> student2 =
                new Result<>("Priya", marks2, 40);

        student2.displayResult();
    }
}
