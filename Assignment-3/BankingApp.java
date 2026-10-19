
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) {
        super(msg);
    }
}

class AccountNotFoundException extends Exception {
    AccountNotFoundException(String msg) {
        super(msg);
    }
}

class BankAccount {
    private final String accountNumber;
    private double balance;

    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException(
                "Deposit must be positive.");
        balance += amount;
    }

    void withdraw(double amount)
            throws InvalidAmountException,
                   InsufficientBalanceException {
        if (amount <= 0)
            throw new InvalidAmountException(
                "Withdrawal must be positive.");

        if (amount > balance)
            throw new InsufficientBalanceException(
                "Insufficient account balance.");

        balance -= amount;
    }

    double getBalance() {
        return balance;
    }

    boolean matches(String number) {
        return accountNumber.equals(number);
    }
}

public class BankingApp {

    static BankAccount findAccount(
            BankAccount account, String number)
            throws AccountNotFoundException {
        if (!account.matches(number)) {
            throw new AccountNotFoundException(
                "Account number not found.");
        }
        return account;
    }

    // Exceptions propagate to the caller.
    static void performWithdrawal(
            BankAccount account, double amount)
            throws InvalidAmountException,
                   InsufficientBalanceException {
        account.withdraw(amount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount("101", 5000);

        try {
            System.out.print("Enter account number: ");
            String number = sc.nextLine();

            BankAccount selected = findAccount(account, number);

            System.out.print("Enter deposit amount: ");
            double deposit = Double.parseDouble(sc.nextLine());
            selected.deposit(deposit);
            System.out.println("Deposit successful.");

            System.out.print("Enter withdrawal amount: ");
            double withdrawal = Double.parseDouble(sc.nextLine());
            performWithdrawal(selected, withdrawal);
            System.out.println("Withdrawal successful.");

            System.out.println("Final balance: "
                               + selected.getBalance());

        } catch (AccountNotFoundException e) {
            System.out.println("Account error: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Amount error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Balance error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid numeric amount.");
        } finally {
            sc.close();
        }
    }
}
