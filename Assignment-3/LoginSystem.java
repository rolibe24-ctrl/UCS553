
import java.util.Scanner;

class InvalidUsernameException extends Exception {
    InvalidUsernameException(String msg) {
        super(msg);
    }
}

class InvalidPasswordException extends Exception {
    InvalidPasswordException(String msg) {
        super(msg);
    }
}

class AccountLockedException extends Exception {
    AccountLockedException(String msg) {
        super(msg);
    }
}

public class LoginSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String correctUsername = "admin";
        final String correctPassword = "java123";
        final int maxAttempts = 3;

        int failedAttempts = 0;
        boolean loggedIn = false;
        boolean locked = false;

        try {
            while (failedAttempts < maxAttempts) {
                try {
                    System.out.print("Username: ");
                    String username = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    if (!username.equals(correctUsername)) {
                        throw new InvalidUsernameException(
                            "Incorrect username.");
                    }

                    if (!password.equals(correctPassword)) {
                        throw new InvalidPasswordException(
                            "Incorrect password.");
                    }

                    loggedIn = true;
                    System.out.println("Login successful!");
                    break;

                } catch (InvalidUsernameException e) {
                    failedAttempts++;
                    System.out.println(e.getMessage());

                } catch (InvalidPasswordException e) {
                    failedAttempts++;
                    System.out.println(e.getMessage());
                }

                if (failedAttempts == maxAttempts) {
                    locked = true;
                    throw new AccountLockedException(
                        "Maximum failed attempts reached.");
                }

                System.out.println("Attempts remaining: "
                    + (maxAttempts - failedAttempts));
            }

        } catch (AccountLockedException e) {
            System.out.println("Account locked: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Login process ended.");
        }

        if (loggedIn) {
            System.out.println("Welcome, admin!");
        }
    }
}
