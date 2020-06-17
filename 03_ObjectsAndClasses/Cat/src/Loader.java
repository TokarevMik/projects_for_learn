
public class Loader
{
    public static void main(String[] args)
    {
       Cat murzik = getKitten();
       Cat barsik = getKitten();
       Cat musya = getKitten();




    }
    private static Cat getKitten(){
        return new Cat(1100.0);
    }
}