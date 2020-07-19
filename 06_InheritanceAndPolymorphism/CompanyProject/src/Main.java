import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {
        Company myCompany = new Company();
        for (int i = 0; i < 180; i++) {
            myCompany.hire(new Operator());
        }
        for (int i = 0; i < 80; i++) {
            myCompany.hire(new Manager());
        }
        for (int i = 0; i < 10; i++) {
            myCompany.hire(new TopManager());
        }
        /*System.out.println("Все зарплаты");
        for (Employee e: myCompany.employeeList) {
            System.out.println(e.getMonthSalary());
        }*/
        System.out.println("-----------------------");

        System.out.println("Самый низкие зарплаты");
        for (Employee e : myCompany.getLowestSalaryStaff(30)) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("*******************");
        System.out.println("Самые высокие зарплаты");
        for (Employee e : myCompany.getTopSalaryStaff(15)) {
            System.out.println(e.getMonthSalary());
        }
        while (myCompany.employeeList.size() > 135) {  //Увольняем половину сотрудников
            int sizeOfList = myCompany.employeeList.size();
            int i = (int) (Math.random() * sizeOfList);
            myCompany.fire(i);
        }
        System.out.println("Размер списка после увольнения " + myCompany.employeeList.size());
        System.out.println("-----------------------");

        System.out.println("Самый низкие зарплаты после увольнения");
        for (Employee e : myCompany.getLowestSalaryStaff(30)) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("*******************");
        System.out.println("Самые высокие зарплаты после увольнения");
        for (Employee e : myCompany.getTopSalaryStaff(15)) {
            System.out.println(e.getMonthSalary());
        }
        /*System.out.println("*******************");
        for (Employee e: myCompany.employeeList) {
            System.out.println(e.getMonthSalary());
        }*/
    }
}
