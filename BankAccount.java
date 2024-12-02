import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    abstract class Account{
        protected String accountNumber;
        protected String accountHolderName;
        protected double balance;
        protected List<String> transactions = new ArrayList<>();
        protected String upiId;

        public Account(String accountNumber, String accountHolderName,
                       double balance, String upiId) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = balance;
            this.upiId = upiId;
        }

        public void viewStatement() {
            System.out.println("Account Statement for " + accountHolderName + ":");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }

        public void deposit(double amount) {
            balance += amount;
            transactions.add("Deposited: " + amount + " at " + LocalDateTime.now());
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                transactions.add("Withdraw: " + amount + " at " + LocalDateTime.now());
                return true;
            } else {
                System.out.println("Balance is Insufficient .");
                return false;
            }
        }


        public boolean transfer(Account recipient, double amount) {
            if (withdraw(amount)) {
                recipient.deposit(amount);
                transactions.add("Transferred: " + amount + " to " + recipient.accountHolderName + " at " + LocalDateTime.now());
                return true;
            }
            return false;
        }



        public void saveStatementToFile() throws IOException {
            String fileName = accountNumber + "_statement.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("Account Statement for " + accountHolderName + ":\n");
                for (String transaction : transactions) {
                    writer.write(transaction + "\n");
                }
            }
            System.out.println("Statement saved  " + fileName);
        }
    }

