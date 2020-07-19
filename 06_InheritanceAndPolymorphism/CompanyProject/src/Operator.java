public class Operator implements Employee {
    private final int FIX_SALARY = 14000;
    private Company company;
    @Override
    public int getMonthSalary() {
        return FIX_SALARY;
    }

    @Override
    public int income() {
        return 0;
    }

    @Override
    public void setCompany(Company c) {
        company = c;
    }

    @Override
    public void fire() {
        company = null;
    }
}
