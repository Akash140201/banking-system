import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Backup {
    private List<Account> accounts = new ArrayList<>();
    private ExecutorService backupService = Executors.newSingleThreadExecutor();

    public synchronized void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added: " + account.accountHolderName);
    }

    public synchronized void removeAccount(String accountNumber) {
        accounts.removeIf(acc -> acc.accountNumber.equals(accountNumber));
        System.out.println("Account removed: " + accountNumber);
    }

    public synchronized void viewAccountsSorted(Comparator<Account> comparator) {
        accounts.stream().sorted(comparator).forEach(acc -> System.out.println(acc.accountHolderName + " - " + acc.accountNumber));
    }

    public synchronized Account searchAccount(String identifier) {
        return accounts.stream()
                .filter(acc -> acc.accountNumber.equals(identifier) || acc.accountHolderName.equalsIgnoreCase(identifier))
                .findFirst()
                .orElse(null);
    }

    public void startBackup() {
        backupService.submit(() -> {
            while (true) {
                backupAccounts();
                Thread.sleep(4000); // Backup every 60 seconds
            }
        });
    }

    private synchronized void backupAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts_backup.dat"))) {
            oos.writeObject(accounts);
            System.out.println("Backup completed.");
        } catch (IOException e) {
            System.err.println("Error during backup: " + e.getMessage());
        }
    }

}