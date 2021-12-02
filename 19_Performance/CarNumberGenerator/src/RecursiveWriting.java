import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

public class RecursiveWriting extends RecursiveAction {
    private int regionCodeStart;
    private int regionCodeEnd;
    private int separator;

    public RecursiveWriting(int regionCodeStart, int regionCodeEnd, int separator) {
        this.regionCodeStart = regionCodeStart;
        this.regionCodeEnd = regionCodeEnd;
        this.separator = separator;
    }

    @Override
    protected void compute() {
        try {
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            if ((regionCodeEnd - regionCodeStart) <= separator) {
                long start = System.currentTimeMillis();
                PrintWriter writer = new PrintWriter("res/numbersFrom" + regionCodeStart + "To" + regionCodeEnd + ".txt");

                for (int regionCode = regionCodeStart; regionCode <= this.regionCodeEnd; regionCode++) {

                    StringBuilder builder = new StringBuilder();
                    for (int number = 1; number < 1000; number++) {
                        for (char firstLetter : letters) {
                            for (char secondLetter : letters) {
                                for (char thirdLetter : letters) {
                                    builder.append(firstLetter);
                                    padNumber(number, 3, builder);
                                    builder.append(secondLetter);
                                    builder.append(thirdLetter);
                                    padNumber(regionCode, 2, builder);
                                    builder.append("\n");
                                }
                            }
                        }
                    }
                    writer.write(builder.toString());
                }
                writer.flush();
                writer.close();
                System.out.println((System.currentTimeMillis() - start) + " ms");
            }
            else {
                int regionMiddle = (regionCodeStart + regionCodeEnd) / 2;
                invokeAll(new RecursiveWriting(regionCodeStart, regionMiddle, separator),
                        new RecursiveWriting(regionMiddle, regionCodeEnd, separator));
            }

        } catch (Exception ex) {
            System.out.println("Ошибка: " + ex);
        }
    }

    private static void padNumber(int number, int numberLength, StringBuilder builder) {
        int padSize = numberLength - Integer.toString(number).length();
        for (int i = 0; i < padSize; i++) {
            builder.append(0);
        }
        builder.append(number);
    }
}
