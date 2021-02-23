public class Account implements Comparable<Account> {

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    private long money;
    private String accNumber;
    private boolean isFrauded = false;

    public boolean getIsFraued() {
        return isFrauded;
    }

    public void setIsFrauded() {
        isFrauded = true;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void putMoney(long debit) {
        money += debit;
    }

    public void takeMoney(long credit) {
        money -= credit;
    }

    @Override
    public int compareTo(Account a) {
        return this.getAccNumber().compareTo(a.getAccNumber());
    }
}
