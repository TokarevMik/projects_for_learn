import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String fio = scn.nextLine();
        String surname = null;
        String name = null;
        String patron = null;
        int spaceIndex = -1;
        int countInd = 0;//счетчик пробелов
       while(true){
           spaceIndex = fio.indexOf(" ",spaceIndex+1);
           if(spaceIndex == -1){
               break;
           }
            countInd++;
       }
       if(countInd==1){                                 //введено 2 слова
           surname = fio.substring(0,fio.indexOf(" "));
           name = fio.substring(surname.length());
           patron = "без отчества";
       } else if(countInd==2){                          //введено 3 слова
           surname = fio.substring(0,fio.indexOf(" "));
           int indexSur = fio.indexOf(" ",surname.length()+1);
           name = fio.substring(surname.length(),indexSur);
           patron = fio.substring(name.length()+surname.length());
       } else if (countInd>2){                         //введено более 3 слов
           surname = fio.substring(0,fio.indexOf(" "));
           int indexSur = fio.indexOf(" ",surname.length()+1);
           name = fio.substring(surname.length(),indexSur);
           int indexPatron = fio.indexOf(" ",name.length()+surname.length()+1);
           patron = fio.substring(name.length()+surname.length(),indexPatron);
       }
        System.out.println("Фамилия: " + surname);
        System.out.println("Имя: " + name.trim());
        System.out.println("Отчество: " + patron.trim() );
        }
}
