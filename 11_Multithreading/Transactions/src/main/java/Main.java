import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        HashMap<String, Account> accs = new HashMap();
        Account oneAcc = new Account("oneAcc", 15000);
        accs.put("oneAcc", oneAcc);
        Account twoAcc = new Account("twoAcc", 25000);
        accs.put("twoAcc", twoAcc);
        Account threeAcc = new Account("threeAcc", 45000);
        accs.put("threeAcc", threeAcc);
        Account fourAcc = new Account("fourAcc", 48000);
        accs.put("fourAcc", fourAcc);
        Account fiveAcc = new Account("fiveAcc", 40000);
        accs.put("fiveAcc", fiveAcc);
        Account sixAcc = new Account("sixAcc", 35000);
        accs.put("sixAcc", sixAcc);


        bank.setAccounts(accs);
        long sum1 = 0;
        for (Map.Entry<String,Account> entry: accs.entrySet() ){
            sum1 += entry.getValue().getMoney();
        }
        System.out.println("Сумма счетов до транзакций " + sum1);

        new Thread(() -> {
            try {
                bank.transfer("oneAcc","twoAcc",10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                bank.transfer("twoAcc","oneAcc",50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                bank.transfer("oneAcc","threeAcc",10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                bank.transfer("threeAcc","fiveAcc",1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                bank.transfer("threeAcc","sixAcc",8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        AtomicLong sum2 = new AtomicLong();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Map.Entry<String, Account> entry : accs.entrySet()) {
                sum2.addAndGet(entry.getValue().getMoney());
            }
            System.out.println("Сумма счетов после транзакций *** " + sum2);
        }).start();
        System.out.println("Сумма счетов после транзакций " + sum1);

    }

}
