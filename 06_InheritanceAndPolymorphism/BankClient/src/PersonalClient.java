public class PersonalClient extends Client {
    @Override
    protected double getWithdrawalComission(double amount) {
        return 0;
    }

    @Override
    protected double getDepositComission(double amount) {
        return 0;
    }

    @Override
    public void showTerms() {
        System.out.println("Пополнение и снятие происходит без комиссии. Остаток на балансе - " + showMoneyCount());
    }
}
