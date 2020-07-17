public class CorpClient extends Client {
    private final double PERCENT_COMMISSION = 1.0;

    @Override
    public void minusMoneyCount(double money) {
        double commission = (money / 100) * PERCENT_COMMISSION;
        super.minusMoneyCount(money + commission);
    }

    @Override
    public void showTerms() {
        System.out.println("Снятие с комиссией 1%. Остаток на балансе - " + showMoneyCount());
    }
}
