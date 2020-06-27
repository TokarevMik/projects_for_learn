import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String fio = scn.nextLine();
        String surname = null;
        String name = null;
        String patron = null;
        //int spaceIndex = -1;
        String [] fio2 = fio.split("\\s");
        surname = fio2[0];
        name  = fio2[1];
        if(fio2.length>2){
            patron = fio2[2];
        } else {
            patron = "без отчества";
        }
        System.out.println("Фамилия: " + surname);
        System.out.println("Имя: " + name.trim());
        System.out.println("Отчество: " + patron.trim() );
        }
}
