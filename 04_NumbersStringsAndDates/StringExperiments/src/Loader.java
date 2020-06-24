
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String text2 = text.replaceAll("[^ 0-9]","");
        System.out.println(text2);
        String [] nums = text2.split("\\s+");
        int sum = 0;
        for (String a: nums) {
            System.out.println("*******");
            System.out.println(a);
            //System.out.println(Integer.parseInt(a.trim()));
        }
        //System.out.println(sum);

    }
}