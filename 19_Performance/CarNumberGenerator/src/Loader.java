import java.io.*;
import java.nio.charset.StandardCharsets;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            for (int number = 1; number < 1000; number++) {
                StringBuilder builder = new StringBuilder();
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
//                writer.write(builder.toString());
                Thread thread1 = new Thread(new WritingClass("res/numbers1.txt", builder.toString()));
                thread1.start();
                thread1.join();
                Thread thread2 = new Thread(new WritingClass("res/numbers2.txt", builder.toString()));
                thread2.start();
                thread2.join();
            }
        }
//        writer.flush();
//        writer.close();
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void padNumber(int number, int numberLength, StringBuilder builder) {
        int padSize = numberLength - Integer.toString(number).length();
        for (int i = 0; i < padSize; i++) {
            builder.append(0);
        }
        builder.append(number);
    }
}
