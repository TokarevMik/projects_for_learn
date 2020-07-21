import java.util.Comparator;

public class ComparatorDownToUp implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o2.getMonthSalary(), o1.getMonthSalary());
    }
}
