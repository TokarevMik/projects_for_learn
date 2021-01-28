import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static int newWdth = 300;

    public static void main(String[] args) {

        String srcFolder = "/users/sortedmap/Desktop/src";
        String dstFolder = "/users/sortedmap/Desktop/dst";

        File srcDir = new File(srcFolder);
        int processors = Runtime.getRuntime().availableProcessors();
        File[] files = srcDir.listFiles();
        int num = files.length;// размер массива
        int part = num / processors;
        for (int i = 0; i < processors; i++) {
            int start = i * part;
            System.arraycopy(files, start, new File[part], 0, part);
            new Thread(new ImageResizer(files, newWdth, dstFolder)).start();
            if ((i + 1) == processors) {
                start = i * part;
                int fin = files.length;
                System.arraycopy(files, start, new File[part], 0, (fin - start));
                new Thread(new ImageResizer(files, newWdth, dstFolder)).start();
            }
        }

    }
}
