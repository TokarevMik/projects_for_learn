import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }//удалить

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
//        return random.nextBoolean();
        if (accounts.get(fromAccountNum).getIsFraued() || accounts.get(toAccountNum).getIsFraued()) {
            return true;
        } else if (amount > 50000) {
            return true;
        } else {
            return false;
        }
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        if (isFraud(fromAccountNum, toAccountNum, amount)) {  //блокировка акк-та если проверка не пройдена
            accounts.get(fromAccountNum).setIsFrauded();
            accounts.get(toAccountNum).setIsFrauded();

        } else {
            Account fromAccount = accounts.get(fromAccountNum);
            Account toAccount = accounts.get(toAccountNum);
            synchronized (fromAccount.compareTo(toAccount) > 0 ? fromAccount : toAccount) {
                synchronized (fromAccount.compareTo(toAccount) > 0 ? toAccount : fromAccount) {
                    if (fromAccount.getMoney() < amount) {
                        return;
                    } else {
                        fromAccount.takeMoney(amount);
                        toAccount.putMoney(amount);
                    }
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        synchronized (accounts.get(accountNum)) {
            return accounts.get(accountNum).getMoney();
        }

    }
}


/**
 * TODO: реализовать метод. Метод переводит деньги между счетами.
 * Если сумма транзакции > 50000, то после совершения транзакции,
 * она отправляется на проверку Службе Безопасности – вызывается
 * метод isFraud. Если возвращается true, то делается блокировка
 * счетов (как – на ваше усмотрение)
 */