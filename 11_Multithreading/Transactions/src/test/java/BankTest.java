import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BankTest {
    Bank bank = new Bank();
    HashMap<String, Account> accs;
    @Before
    public void setUp() throws Exception {

        accs = new HashMap();
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

    }
    @Test
    public void testTransfer() {
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
        long actuall = 208000;
        long sum = 0;
        for (Map.Entry<String,Account> entry: accs.entrySet() ){
            sum+=(entry.getValue().getMoney());
        }
        assertEquals(sum,actuall);

    }

    @After
    public void tearDown() throws Exception {
    }


}