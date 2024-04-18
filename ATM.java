import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

//start of the class
class Account{        
    //data fields
    double balance;
    String name;
    int accountNum;
    List<Double> transactions;
    
    //general constructor
    public Account(double balance, String name, int accountNum){
        this.balance = balance;
        this.name = name;
        this.accountNum = accountNum;
        this.transactions = new ArrayList<>();
    }
    
    //deposit method to add/deposit money into a users account balance
    public double deposit(double depositAmount){
        balance += depositAmount;
        transactions.add(depositAmount);
        return balance;
    }


    //withdraw method to add/deposit money into a users account balance
    public void withdraw(double withdrawAmount){
        balance -= withdrawAmount;
        transactions.add(-withdrawAmount);
    }

    public boolean isOwner(String personsName){
        return name.equalsIgnoreCase(personsName);
    }

    public void getStats(){
        if (transactions.isEmpty()){
            System.out.println("Nothing bought yet.");
            return;
        }

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;

        int startIndex = Math.max(0, transactions.size() -5); //Starts from the last 5 transactions.

        for(int i = startIndex; i < transactions.size(); i++){
            double amount = transactions.get(i);
            sum += amount;
            min = Math.min(min, amount);
            max = Math.max(max, amount);
        }

        double average = sum / Math.min(transactions.size(), 5);

        System.out.println("Current Balance: " + balance);
        System.out.println("Last 5 Transactions:");
        System.out.println("Minimum Transaction: " + min);
        System.out.println("Maximum Transaction: " + max);
        System.out.println("Average Transaction: " + average);
    }

    public void viewRecentTransactions(){
        if (transactions.isEmpty()){
            System.out.println("No transection yet.");
            return;
        }

        int numTransactionsToShow = Math.min(transactions.size(), 5);
        System.out.println("Recent Transacitons:");

        for(int i = transactions.size() - 1; i >= transactions.size() - numTransactionsToShow; i--){
            double amount = transactions.get(i);
            String transactionType = amount > 0 ? "Deposit" : "Withdrawal";
            System.out.println("Type: " + transactionType + ", Amount: " + Math.abs(amount));
        }
    }
}
    
public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        ArrayList<Integer> accountNumbers = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();

        while (true) {
            //Ask the user for their full name
            System.out.println("Enter your first and last name or 'E' to exit the program.");
            String name = scanner.nextLine();
            name = name.toUpperCase();
            String exit = "E";

            if (name.equals(exit)) {
                System.exit(0);
            } else {
                System.out.println("Hello " + name + ", What is your account number?");
            }

            String userAccountNum = scanner.nextLine();
            boolean accountExists = false;
            Account currentAccount = null;
            for (Account account : accounts) {
                if (account.accountNum == Integer.parseInt(userAccountNum)) {
                    accountExists = true;
                    currentAccount = account;
                    System.out.println("Your account was successfully found. Your account number is " + accountNumbers);
                    break;
                }
            }

            if (!accountExists) {
                int newAccountNum = random.nextInt(9999) + 1000;
                System.out.println("Your account wasnt found. We will create an account for you.");
                currentAccount = new Account(0, name, newAccountNum);
                accounts.add(currentAccount);
                System.out.println("Your new account number is " + newAccountNum);

            }

            if (currentAccount.isOwner(name)) {
                System.out.println("Welcome, " + name + ". You are the owner.");
                //Show the menu
                String withdraw = "W";
                String deposit = "D";
                String getStats = "S";
                String viewTransactions = "T";


                System.out.println("Please enter 'D' to deposit, 'W' to withdraw, 'S' to getStats, 'T' to view transactions, or 'E' to exit");
                String choice = scanner.nextLine();
                choice = choice.toUpperCase();


                if (choice.equals("D")) {
                    System.out.println("How much would you like to deposit?");
                    double depositAmount = scanner.nextDouble();
                    //Find the users account and update their balance
                    scanner.nextLine();
                    if (depositAmount > 0) {
                        currentAccount.deposit(depositAmount);
                        System.out.println("Successfully deposited " + depositAmount + ". Your new balance is " + currentAccount.balance);
                    } else {
                        System.out.println("invalid amount");
                    }
                } else if (choice.equals("W")) {
                    System.out.println("How much would you like to withdraw?");
                    double withdrawAmount = scanner.nextDouble();
                    //Find the users account and update the balance
                    scanner.nextLine();
                    if (withdrawAmount > 0 && withdrawAmount <= currentAccount.balance) {
                        currentAccount.withdraw(withdrawAmount);
                        System.out.println("Successfully withdrew " + withdrawAmount + ". Your new balance is " + currentAccount.balance);
                    }else {
                        System.out.println("invalid amount or insufficient funds");
                    }
                } else if (choice.equals("S")){
                    currentAccount.getStats();
                } else if (choice.equals("T")){
                    currentAccount.viewRecentTransactions();
                } else if (choice.equals("E")){
                    System.out.println("Exiting the program.");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Try again.");
                }

            } else {
                System.out.println("This isnt your account...You are now being tracked by the FBI for fraud.");
            }
        }
    }
} // end of ATM class
    
