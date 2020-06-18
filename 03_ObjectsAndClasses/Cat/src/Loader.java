
public class Loader
{
    public static void main(String[] args)
    {
       Cat murzik = getKitten();
       murzik.setColor(CatsColor.RED);
        System.out.println("Murzik is " + murzik.getColor());
        Cat vaska = copyCat(murzik);
        System.out.println("Vaska is " + vaska.getColor());





    }
    private static Cat getKitten(){
        return new Cat(1100.0);
    }
    private static Cat copyCat(Cat cat1){
        Cat cat = new Cat(cat1);
        return cat;
    }
}