import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WritingClass implements Runnable{
    String path;
    String num;
    public WritingClass(String path, String f) {
        this.path = path;
        this.num = f;
    }
    @Override
    public void run() {
        PrintToFile(path,num);
    }
    private static void PrintToFile(String path,String num) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(path, true));
            writer.write(num);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}