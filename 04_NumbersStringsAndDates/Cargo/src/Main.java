public class Main {
    public static void main(String[] args) {
        int truck = 1;
        int container = 1;
        int box = 630; //Кол-во ящиков
        int contCapas = 0;//Наполнение контейнера;
        int trucCapas = 0;//Наполнение грузовика
        if (box > 0) {
            for (int i = 1; i <= box; i++) {
                if (i == 1) {
                    System.out.println("Грузовик " + truck);
                    System.out.println("\t Контейнер " + container);
                }
                System.out.println("\t \tЯщик " + i);
                contCapas++;
                if (contCapas == 27) {
                    contCapas = 0;
                    container++;
                    trucCapas++;
                    if (trucCapas == 12) {
                        trucCapas = 0;
                        truck++;
                        System.out.println("Грузовик " + truck);
                    }
                    System.out.println("\t Контейнер " + container);
                }
            }
            System.out.println("\nНеобходимо: \n" + truck + " грузовиков\n" + container + " контейнеров");
        } else {
            System.out.println("Нет коробок");
        }
    }
}
