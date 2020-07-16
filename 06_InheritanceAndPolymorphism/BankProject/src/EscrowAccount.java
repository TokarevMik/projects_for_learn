import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EscrowAccount extends BankAccount {
    boolean isFirstMin = true;
    Calendar allowedOfMinus;
    Calendar toDay;

    @Override
    public double showMoneyCount() {
        return super.showMoneyCount();
    }

    @Override
    public void plusMoneyCount(double money) {
        super.plusMoneyCount(money);
    }

    @Override
    boolean send(BankAccount receiver, double amount) {
        if (canMinus()) {
            super.send(receiver, amount);
            return true;
        } else {
            System.out.println("Перевод не возможен.");
            return false;
        }
    }

    public boolean canMinus() {
        if (isFirstMin) {
            return true;
        } else {
            toDay = Calendar.getInstance();
            if (toDay.after(allowedOfMinus)) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void minusMoneyCount(double money) {
        if (canMinus()) {
            allowedOfMinus = Calendar.getInstance();
            allowedOfMinus.add(Calendar.MONTH, 1);
            isFirstMin = false;
            super.minusMoneyCount(money);

        } else {
            System.out.println("Перевод не возможен.");
        }
    }
}




