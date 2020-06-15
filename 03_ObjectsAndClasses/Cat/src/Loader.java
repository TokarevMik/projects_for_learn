
public class Loader
{
    public static void main(String[] args)
    {
        Cat murzik = new Cat();
        System.out.println("Кот весит " + murzik.getWeight());
        murzik.feed(150.0);
        murzik.pee();
        murzik.pee();
        murzik.pee();
        murzik.pee();
        System.out.println("******************");
        System.out.println("Теперь кот весит "+murzik.getWeight());
        System.out.println("Кот съел " + murzik.getCountOfFood());



    }
}