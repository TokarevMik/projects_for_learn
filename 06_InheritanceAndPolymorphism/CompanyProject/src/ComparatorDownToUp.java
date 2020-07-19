import java.util.Comparator;

public class ComparatorDownToUp implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getMonthSalary() > o2.getMonthSalary()) {
            return -1;
        } else if (o1.getMonthSalary() < o2.getMonthSalary()) {
            return 1;
        } else if(o1.getMonthSalary() == o2.getMonthSalary()) {
            return 0;
        } else return 0;
      // return (Double.compare(o1.getMonthSalary(),o2.getMonthSalary())*(-1));
    }
}
