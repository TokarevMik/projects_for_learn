import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;
    Pattern telPat = Pattern.compile("^(\\+79)(\\d{9})$");
    Pattern mailPat = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    Pattern namePat = Pattern.compile("^[А-Я][а-я]+ [А-Я][а-я]+[\\-]?([А-Я][а-я]+)?$");

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];
        Matcher mailM = mailPat.matcher(components[2]);
        Matcher telM = telPat.matcher(components[3]);
        Matcher nameM = namePat.matcher(name);
        if (!mailM.find()) {
            throw new IllegalArgumentException("Wrong email format. Correct format: \n vasily.petrov@gmail.com");
        }
        if (!telM.find()) {
            throw new IllegalArgumentException("Wrong phone number format. Correct format: \n +79215637722");
        }
        if (!nameM.find()) {
            throw new IllegalArgumentException("Wrong name format. Correct format: \n Василий Петров");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        Matcher nameM = namePat.matcher(name);
        if (!nameM.find()) {
            throw new IllegalArgumentException("Wrong name format. Correct format: \n Василий Петров");
        }
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}