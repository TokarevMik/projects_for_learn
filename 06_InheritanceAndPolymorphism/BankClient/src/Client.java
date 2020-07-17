public abstract class Client {
    private double moneyCount = 100;

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

    public abstract void showTerms();

}
