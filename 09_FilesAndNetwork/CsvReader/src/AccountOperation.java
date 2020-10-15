public class AccountOperation {
        private double refill;
        private double writeOff;
        private String accOperation;

    public AccountOperation(double refill, double writeOff, String accOperation) {
        this.refill = refill;
        this.writeOff = writeOff;
        this.accOperation = accOperation;
    }

    public double getRefill() {
        return refill;//пополнение
    }

    public double getWriteOff() {
        return writeOff;//списание
    }

    public String getAccOperation() {
        return accOperation;//описание операции
    }
}
