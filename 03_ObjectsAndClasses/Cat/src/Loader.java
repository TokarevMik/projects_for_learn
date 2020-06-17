
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
    private static Cat copyCat(Cat cat1){
        Cat cat = new Cat();
        cat.setColor(cat1.getColor());
        cat.setWeight(cat1.getWeight());
        cat.setOriginWeight(cat1.getOriginWeight());
        cat.setAlive(cat1.isAlive());
        cat.setCountOfFood(cat1.getCountOfFood());
        return cat;
    }
}