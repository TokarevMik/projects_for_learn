
public class Loader
{
    public static void main(String[] args)
    {
        Cat murzik = new Cat();
        Cat barsik = new Cat();

        System.out.println(murzik.getStatus());
        System.out.println(barsik.getStatus());
        System.out.println("*********************");
        System.out.println(murzik.getWeight());
        System.out.println(barsik.getWeight());
        System.out.println("*********************");
        murzik.feed(100.0);
        barsik.meow();
        System.out.println(murzik.getWeight());
        System.out.println(barsik.getWeight());
        System.out.println("*********************");
        while (murzik.getWeight()<9000.0){
            murzik.feed(100.0);
            murzik.drink(50.0);
        }
        while (barsik.getWeight()>1000.0){
            barsik.meow();
        }
        System.out.println(murzik.getStatus());
        System.out.println(barsik.getStatus());
        System.out.println("*********************");

    }
}