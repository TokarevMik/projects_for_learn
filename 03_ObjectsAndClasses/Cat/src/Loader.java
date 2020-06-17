
public class Loader
{
    public static void main(String[] args)
    {
       Cat murzik = getKitten();
       murzik.setColor(CatsColor.RED);
        System.out.println("Color is " + murzik.getColor());





    }
    private static Cat getKitten(){
        return new Cat(1100.0);
    }
}