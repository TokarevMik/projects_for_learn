import java.util.Calendar;

public class EscrowAccount extends BankAccount {
    private boolean isFirstMin = true;
    private Calendar allowedOfMinus;
    private Calendar toDay;
    private final int WAITING_TIME = 1;
    @Override
    public double showMoneyCount() {
        return super.showMoneyCount();
    }

    @Override
    public void plusMoneyCount(double money) {
        super.plusMoneyCount(money);
    }

    @Override
    public boolean send(BankAccount receiver, double amount) {
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
            allowedOfMinus.add(Calendar.MONTH, WAITING_TIME);
            isFirstMin = false;
            super.minusMoneyCount(money);

        } else {
            System.out.println("Перевод не возможен.");
        }
    }
}
