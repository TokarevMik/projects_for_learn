public class BankAccount {
    private double moneyCount = 200;

    public boolean send(BankAccount receiver, double amount) {
        this.minusMoneyCount(amount);
        receiver.plusMoneyCount(amount);
        return true;
    }

    public double showMoneyCount() {
        return moneyCount;
    }

    public void plusMoneyCount(double money) {
        this.moneyCount += money;
    }

    public void minusMoneyCount(double money) {
        if (moneyCount >= money) {
            this.moneyCount -= money;
        } else {
            System.out.println("Не достаточно средств");
        }
    }

}
