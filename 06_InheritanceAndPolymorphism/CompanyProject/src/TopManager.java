import java.util.ArrayList;
import java.util.Collection;

public class TopManager implements Employee {
    private final double FIX_SALARY = 15000.0;
    private Company placeOfWork;

    private double getPercent() {
        if (placeOfWork.getIncome() > 10000000) {
            return FIX_SALARY * 1.5;
        } else {
            return 0.0;
        }
    }

    public void hireToWork(Company c) {
        placeOfWork = c;
    }

    public void fire() {
        placeOfWork = null;
    }

    @Override

    public double getMonthSalary() {
        if (placeOfWork != null) {
            if ((placeOfWork.workPlace()).contains(this)) {
                return FIX_SALARY + getPercent();
            } else {
                return 0.0;
            } //в данной компании нет такого сотрудника
        } else {
            return 0.0; //Сотрудник уволен.
        }
    }

    @Override
    public double income() {
        return 0;
    }
}
