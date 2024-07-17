//import required packages
import java.util.ArrayList;
import java.util.Scanner;

// Define a Transaction class to track each transaction
class Transaction {
    private String type; // "Withdrawal" or "Deposit"
    private double amount;
    // Constructor of class Transaction
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " of ₹" + amount;
    }
}

// Define the ATM class
class ATM {
    private String accountNumber;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;
    int PIN=1234;
    //Constructor of class ATM
    public ATM(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
    //Method to check if the authorized person is using the account
    public void checkpin(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a PIN: ");
        int enteredpin=sc.nextInt();

        if(enteredpin==PIN){
            displayMenu();
        }
        else{
            System.out.println("Enter a Valid PIN ");
            checkpin();
        }
    }
    // Method to display choices that a account holder can do in a ATM
    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Change PIN");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
    }
    //Method to check the Current Balance in the Account
    public void checkBalance() {
        System.out.println("\nYour current balance is ₹" + balance);
    }
    //Method to withdraw some amount and update the balance in the account
    public void withdrawCash(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("\nSuccessfully withdrew ₹" + amount);
        } else {
            System.out.println("\nInsufficient funds or invalid amount.");
        }
    }
    //Method to deposit some money and update the balance in the account
    public void depositCash(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("\nSuccessfully deposited ₹" + amount);
        } else {
            System.out.println("\nInvalid amount.");
        }
    }
    //Method to give the access to the holder to update a new pin to the account
    public void changePin(String newPin) {
        pin = newPin;
        System.out.println("\nPIN successfully changed.");
    }
    //Method to give the holder the information about the previous transactions held in the account so far
    public void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

// Main class to run the ATM simulation
public class ATMMachine {
    public static void main(String[] args) {
        // Example usage
        ATM atm = new ATM("123456789", "1234", 0.00);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ATM !! ");
        //Calling the checkpin() method to provide service to the Customer
        atm.checkpin();
        //Executing the while block if it is true
            while (true) {

                System.out.print("\nEnter your choice (1-6): ");
                String option = scanner.nextLine();
                //Implement the switch case based on the input of option
                switch (option) {
                    case "1":
                        atm.checkBalance();
                        break;
                    case "2":
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        atm.withdrawCash(withdrawAmount);
                        break;
                    case "3":
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        atm.depositCash(depositAmount);
                        break;
                    case "4":
                        System.out.print("Enter new PIN (4 digits): ");
                        String newPin = scanner.nextLine();
                        atm.changePin(newPin);
                        break;
                    case "5":
                        atm.displayTransactionHistory();
                        break;
                    case "6":
                        System.out.println("\nThank you for using the ATM. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("\nInvalid choice. Please enter a number from 1 to 6.");
                        break;
                }
            }



    }
}
