public class Main {
    public static void main(String[] args) {
        String[][] X = new String[7][7];
        for (int i = 0; i < X.length; i++) {
            X[i][i] = "*";
            X[i][X.length - 1 - i] = "*";
            for (int j = 0; j < X.length; j++) {
                if (j != i && j != (X.length - 1 - i)) {
                    X[i][j] = " ";
                }
            }
        }
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[i].length; j++) {
                System.out.print(X[i][j]);
            }
            System.out.println();
        }
    }
}
