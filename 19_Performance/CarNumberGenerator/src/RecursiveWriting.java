import java.util.concurrent.RecursiveTask;

public class RecursiveWriting extends RecursiveTask<StringBuilder> {
    StringBuilder builder;
    int regionCode;
    int start, end;

    public RecursiveWriting(int regionCode, int start, int end) {
        this.regionCode = regionCode;
        this.start = start;
        this.end = end;
    }

    char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    @Override
    protected StringBuilder compute() {
        StringBuilder builder = new StringBuilder();
        if((end-start)>150){
            for (int number = start; number < end; number++) {
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
                    }//end for 1
                }//end for 2
            }
        } else {
            int middle = (end+start)/2;
            RecursiveWriting subTask1 = new RecursiveWriting (regionCode,start,middle);
            RecursiveWriting subTask2 = new RecursiveWriting (regionCode,middle,end);
            subTask1.fork();  // Каждая из задач запускается явно
            subTask2.fork();

            builder.append(subTask1.join()).append(subTask2.join());

        }
        return builder;
    }//end compute
    private static void padNumber(int number, int numberLength, StringBuilder builder) {
        int padSize = numberLength - Integer.toString(number).length();
        for (int i = 0; i < padSize; i++) {
            builder.append(0);
        }
        builder.append(number);
    }
}//end class
