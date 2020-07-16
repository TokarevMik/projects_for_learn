public class CardBankAccount extends BankAccount {
    private double moneyCount = 120;

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
        return super.send(receiver, amount);
    }

    @Override
    public void minusMoneyCount(double money) {
        double commission = (money / 100) * 1.0;
        super.minusMoneyCount(money + commission);


    }
}
