import java.util.ArrayList;
import java.util.Collection;

public class TopManager implements Employee {
    private final int FIX_SALARY = 14000;
    private Company company;
    private final int FRONTIER_FOR_BONUS = 10_000_000;

    @Override
    public int income() {
        return 0;
    }

    private int getPercent() {       //Процент премии
        if (company.getIncome() > FRONTIER_FOR_BONUS) {
            return ((FIX_SALARY / 100) * 150);
        } else {
            return 0;
        }
    }

    public void setCompany(Company c) {
        company = c;
    }

    public void fire() {
        company = null;
    }

    @Override

    public int getMonthSalary() {
        if (company != null) {
            if ((company.workPlace()).contains(this)) {
                return FIX_SALARY + getPercent();
            } else {
                return 0;
            } //в данной компании нет такого сотрудника
        } else {
            return 0; //Сотрудник уволен.
        }
    }
}
