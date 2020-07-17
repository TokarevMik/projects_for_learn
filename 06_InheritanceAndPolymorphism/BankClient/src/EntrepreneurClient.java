public class EntrepreneurClient extends Client {
    private final double PERCENT_COMMISSION_1 = 0.99;
    private final double PERCENT_COMMISSION_2 = 0.995;

    @Override
    public void plusMoneyCount(double money) {
        if (money < 1000) {
            super.plusMoneyCount(money * PERCENT_COMMISSION_1);
        } else {
            super.plusMoneyCount(money * PERCENT_COMMISSION_2);
        }
    }

    @Override
    public void showTerms() {
        System.out.println("Пополнение с комиссией 1%, если сумма меньше 1000 рублей. И с комиссией 0,5%, " +
                "если сумма больше либо равна 1000 рублей. Остаток на балансе - " + showMoneyCount());
    }
}
