public class Main {
    public static void main(String[] args) {
        String[][] X = new String[7][7];
        for (int i = 0; i <7; i++) {
             X[i][i] = "*";
             X[i][6-i] ="*";
            for (int j = 0; j <7 ; j++) {
                if(j!=i&&j!=(6-i)){
                    X[i][j]=" ";
                }

            }
        }
        for (int i = 0; i <X.length ; i++) {

            for (int j = 0; j <X[i].length ; j++) {
                System.out.print(X[i][j]);
            }
            System.out.println();

        }
    }
}
