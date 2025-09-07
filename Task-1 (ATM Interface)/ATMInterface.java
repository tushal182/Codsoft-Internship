import java.util.Scanner;

// Class to represent Bank Account
class BankAccount {
    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Withdrawal amount must be positive!");
        }
    }

    // Check Balance
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

// ATM class
class ATM {
    private BankAccount account;
    private final int PIN = 1234; // fixed PIN
    private Scanner sc = new Scanner(System.in); // single Scanner for whole class

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Authenticate user with max 3 attempts
    private boolean authenticate() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = sc.nextInt();

            if (enteredPin == PIN) {
                System.out.println("Authentication successful!\n");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect PIN! Attempts left: " + attempts);
                } else {
                    System.out.println("Too many failed attempts. Your account has been blocked!");
                }
            }
        }
        return false;
    }

    // ATM Menu
    public void menu() {
        if (!authenticate()) {
            return; // exit if authentication fails
        }

        int choice;
        do {
            System.out.println("\n====== ATM Menu ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);
    }
}

// Main Class
public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account with initial balance
        BankAccount account = new BankAccount(1000.0);

        // Create ATM and connect to bank account
        ATM atm = new ATM(account);

        // Start ATM Menu
        atm.menu();
    }
}
