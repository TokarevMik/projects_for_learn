
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int v1 = text.indexOf('5');
        int v2 = text.indexOf('у') - 2;
        String cash1 =text.substring(v1,v2);
        int p1 = text.indexOf('-') + 1;
        int p2 = text.indexOf('3') + 1;
        String cash2 =(text.substring(p1,p2)).trim();
        int m1 = text.lastIndexOf('3');
        int m2 = text.lastIndexOf('р');
        String cash3 =(text.substring(m1,m2)).trim();
        System.out.println(Integer.parseInt(cash1)+Integer.parseInt(cash2) + Integer.parseInt(cash3));
    }
}