public class Manager implements Employee {
    private final int FIX_SALARY = 15000;
    int income1 = income();
    private Company company;
    @Override
    public void setCompany(Company c) {
        company = c;
    }

    @Override
    public void fire() {
        company = null;
    }


    @Override
    public int getMonthSalary() {

        return FIX_SALARY + (income1/100) * 5;
    }

    @Override
    public int income() {
        return 115000 + (int) (Math.random() * 25000);
    }
}
