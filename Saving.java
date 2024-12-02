import java.time.LocalDateTime;

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, String upiId, double interestRate) {
        super(accountNumber, accountHolderName, balance, upiId);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        deposit(interest);
        transactions.add("Interest added: " + interest + " at " +  LocalDateTime.now());
    }
}
