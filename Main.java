import java.io.IOException;
import java.util.Comparator;


    public class Main{
        public static void main(String[] args) throws IOException {
            Backup bank = new Backup();
            bank.startBackup();

            SavingsAccount savingsAccount = new SavingsAccount("12650", "akash", 2980, "akash@ybl", 3.9);
            bank.addAccount(savingsAccount);


            CurrentAccount currentAccount = new CurrentAccount("89803", "aditya", 34563, "aditya@ybl");
            bank.addAccount(currentAccount);



            savingsAccount.deposit(100);
            savingsAccount.applyInterest();
            savingsAccount.viewStatement();
            savingsAccount.saveStatementToFile();

            currentAccount.withdraw(500);
            currentAccount.viewStatement();

            bank.viewAccountsSorted(Comparator.comparing(acc -> acc.accountHolderName));
        }
    }

