public abstract class Client {
    private double moneyCount = 100;
    protected abstract double getWithdrawalComission(double amount);
    protected abstract double getDepositComission(double amount);

    public double showMoneyCount() {
        return moneyCount;
    }

    public void plusMoneyCount(double money) {

        this.moneyCount += (money - getDepositComission(money));
    }

    public void minusMoneyCount(double money) {
        if (moneyCount >= money) {
            this.moneyCount -= (money + getWithdrawalComission(money));
        } else {
            System.out.println("Не достаточно средств");
        }
    }

    public abstract void showTerms();

}
