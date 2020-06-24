
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String text2 = text.replaceAll("[^ 0-9]","");
        String [] nums = text2.split("\\s\\s\\s");
        int sum = 0;
        for (String a: nums) {
            sum+=Integer.parseInt(a.trim());
        }
        System.out.println(sum);

    }
}