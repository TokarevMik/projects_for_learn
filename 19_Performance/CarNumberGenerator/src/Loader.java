import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ForkJoinPool;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter(new FileWriter("res/numbers.txt", true));
//        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            ForkJoinPool fjp = new ForkJoinPool();//2
            StringBuilder builder;//2
            RecursiveWriting task = new RecursiveWriting(regionCode, 1, 1000);//2
            builder = fjp.invoke(task); //2
           /* StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
//                            builder.append(padNumber(number, 3));
                            padNumber(number, 3, builder);
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            padNumber(regionCode, 2, builder);
//                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                        }
                    }//end for 1
                }//end for 2
            } //1*/
            writer.write(builder.toString());
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

  /*  private static void padNumber(int number, int numberLength, StringBuilder builder) {
        int padSize = numberLength - Integer.toString(number).length();
        for (int i = 0; i < padSize; i++) {
            builder.append(0);
        }
        builder.append(number);
    }*/
}
