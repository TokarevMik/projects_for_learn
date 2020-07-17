public class EntrepreneurClient extends Client {
    private final double PERCENT_COMMISSION_1 = 1;
    private final double PERCENT_COMMISSION_2 = 0.5;

    @Override
    protected double getWithdrawalComission(double amount) {
        return 0;
    }

    @Override
    protected double getDepositComission(double amount) {
        if (amount < 1000) {
           return (amount/100) * PERCENT_COMMISSION_1;
        } else {
           return (amount/100) * PERCENT_COMMISSION_2;
        }
    }
    @Override
    public void showTerms() {
        System.out.println("Пополнение с комиссией 1%, если сумма меньше 1000 рублей. И с комиссией 0,5%, " +
                "если сумма больше либо равна 1000 рублей. Остаток на балансе - " + showMoneyCount());
    }
}
