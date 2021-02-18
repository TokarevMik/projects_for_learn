public class Account {

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    private long money;
    private String accNumber;
    private boolean isFrauded = false;

    public boolean getIsFraued() {
        synchronized (this) {
            return isFrauded;
        }
    }

    public void setIsFrauded() {
        synchronized (this) {
            isFrauded = true;
        }
    }

    public long getMoney() {
        synchronized (this) {
            return money;
        }
    }

    public void setMoney(long money) {
        synchronized (this) {
            this.money = money;
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

}
