
public class Loader {

        private final static int REGIONS = 100;
        private final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
        public static void main (String[]args){
            int separator = divAndCeil(REGIONS, PROCESSORS);
            RecursiveWriting task = new RecursiveWriting(1, 100, separator);
            task.invoke();
        }
        private static int divAndCeil ( int divisor1, int divisor2){
            return (int) Math.ceil((float) divisor1 / (float) divisor2);
        }
    }


