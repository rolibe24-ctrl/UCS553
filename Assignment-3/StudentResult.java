
import java.util.Scanner;

class InvalidMarksException extends Exception {
    InvalidMarksException(String message) {
        super(message);
    }
}

public class StudentResult {

    static void validateMarks(int marks)
            throws InvalidMarksException {
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException(
                "Marks must be between 0 and 100.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of subjects: ");
            int n = Integer.parseInt(sc.nextLine());

            if (n <= 0) {
                throw new IllegalArgumentException(
                    "Number of subjects must be positive.");
            }

            int total = 0;

            for (int i = 1; i <= n; i++) {
                System.out.print("Enter marks for subject "
                                 + i + ": ");
                int marks = Integer.parseInt(sc.nextLine());

                validateMarks(marks);
                total += marks;
            }

            double percentage = (double) total / n;

            String grade;
            if (percentage >= 90)
                grade = "A";
            else if (percentage >= 75)
                grade = "B";
            else if (percentage >= 60)
                grade = "C";
            else if (percentage >= 40)
                grade = "D";
            else
                grade = "F";

            System.out.println("Total Marks: " + total);
            System.out.println("Percentage: " + percentage + "%");
            System.out.println("Grade: " + grade);

        } catch (InvalidMarksException e) {
            System.out.println("Invalid marks: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Enter integer marks.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
