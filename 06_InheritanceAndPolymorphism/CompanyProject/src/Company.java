import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Company {
    public ArrayList<Employee> employeeList = new ArrayList<>(); //Список сотрудников

    List<Employee> getTopSalaryStaff(int count) {
        employeeList.sort(new ComparatorDownToUp());

        return employeeList.subList(0, count);
        //return employeeList;
    }

    List<Employee> getLowestSalaryStaff(int count) {
        employeeList.sort(new ComparatorUpToDown());
        List<Employee> lowSalEmp = employeeList.subList(0, count);
        return lowSalEmp;
        //return employeeList;

    }

    public ArrayList workPlace() {
        return employeeList;
    }


    public void hire(Employee e) {
        employeeList.add(e);
        e.setCompany(this);
    }

    public void hireAll(Collection<Employee> c) {
        employeeList.addAll(c);
        for (Employee e : employeeList) {
            e.setCompany(this);
        }
    }

    public void fire(int i) {
        employeeList.get(i).fire();
        employeeList.remove(i);

    }

    public int getIncome() {
        int countMoney = 0;
        for (Employee c : employeeList) {
            countMoney += c.income();
        }
        return countMoney;
    }


}
