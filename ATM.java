import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

//start of the class
class Account{        
    //data feilds
    double balance;
    String name;
    int accountNum;
    
    //general constructor
    public Account(double balance, String name, int accountNum){
        this.balance = balance;
        this.name = name;
        this.accountNum = accountNum;
    }
    
    //deposit method to add/deposit money into a users account balance
    public double deposit(double depositAmount){
        balance += depositAmount;
        return balance;
    }

    //withdraw method to add/deposit money into a users account balance
    public void withdraw(double withdrawAmount){
        balance -= withdrawAmount;
    }
}
    
public class ATM {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        ArrayList<Integer> accountNumbers = new ArrayList<>(); 
        ArrayList<Account> accounts = new ArrayList<>();
        
        while(true){
            //Ask the user for their full name
            System.out.println("Enter your first and last name or 'E' to exit the program.");
            String name = scanner.nextLine();
            name = name.toUpperCase();
            String exit = "E";
            
            
            if(name.equals(exit)){
                System.exit(0);
            } else {
                System.out.println("Hello " + name + ", What is your account number?");
            }
            
            String userAccountNum = scanner.nextLine(); 
            
            boolean accountExists = false;
            for(Account account : accounts){
                if(account.accountNum == Integer.parseInt(userAccountNum)){
                    accountExists = true;
                    System.out.println("Your account was successfully found. Your account number is " + accountNumbers);
                    break;
                }
            }
            
            if(!accountExists){
                int newAccountNum = random.nextInt(9999)-1000;
                System.out.println("Your account wasnt found. We will create an account for you.");
                accounts.add(new Account(0, name, newAccountNum));
                System.out.println("Your new account number is " + newAccountNum);
                
            }
            
            String withdraw = "W";
            String deposit = "D";
            String getstats = "S";
                
            System.out.println("Please enter 'D' to deposit, 'W' to withdraw, 'S' to getStats, 'T' for transactions, or 'E' to exit");
            String choice = scanner.nextLine();
            choice = choice.toUpperCase();
            
            
            if(choice.equals(deposit)){
                System.out.println("How much would you like to deposit?");
                double depositAmount = scanner.nextDouble();
                //Find the users account and update their balance
                for(Account account: accounts){
                    if(account.accountNum == Integer.parseInt(userAccountNum)){
                        account.deposit(depositAmount);
                        System.out.println("Successfully deposited " + depositAmount + ". Your new balance is" + account.balance);
                        
                    }
                }
            }
            
            if(choice.equals(withdraw)){
                System.out.println("How much would you like to withdraw?");
                double withdrawAmount = scanner.nextDouble();
                //Find the users account and update the balance
                for(Account account: accounts){
                    if(account.accountNum == Integer.parseInt(userAccountNum)){                            
                        account.withdraw(withdrawAmount);
                        System.out.println("Successfully withdrew " + withdrawAmount + ". Your new balance is " + account.balance);
                        
                    }
                }
            }
        }
    }
} // end of ATM class
    

