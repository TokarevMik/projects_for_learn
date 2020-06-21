public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        Integer a = 12345;
        Integer b = 10;
        Integer c = 5059191;
        System.out.println(sumDigits(a));
        System.out.println(sumDigits(b));
        System.out.println(sumDigits(c));
    }

    public static Integer sumDigits(Integer number)
    {
        Integer sum = 0;
        String num = number.toString();
        int count  = num.length();
        for (int i = 0; i <count ; i++) {
            char a = num.charAt(i);
            String b = String.valueOf(a);
            sum += Integer.parseInt(b);
           }
        //@TODO: write code here
        return sum;
    }
}
