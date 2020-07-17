public class CorpClient extends Client {
    private final double PERCENT_COMMISSION = 1.0;

    @Override
    protected double getWithdrawalComission(double amount) {
        return (amount / 100) * PERCENT_COMMISSION;
    }

    @Override
    protected double getDepositComission(double amount) {
        return 0;
    }

    @Override
    public void minusMoneyCount(double money) {
        super.minusMoneyCount(money);
    }

    @Override
    public void showTerms() {
        System.out.println("Снятие с комиссией 1%. Остаток на балансе - " + showMoneyCount());
    }
}
