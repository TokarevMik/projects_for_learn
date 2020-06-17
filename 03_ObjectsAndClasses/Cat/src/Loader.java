
public class Loader
{
    public static void main(String[] args)
    {
        Cat murzik = new Cat();
        Cat musya = new Cat();
        System.out.println("Murzik  is " + murzik.getStatus());
        System.out.println("Musya  is " + musya.getStatus());
        System.out.println("Murzik's weight is " + murzik.getWeight());
        while (murzik.isAlive(murzik.getWeight())){
            murzik.feed(100.0);
        }
        murzik.feed(200.0);
        System.out.println("Murzik is " + murzik.getStatus());




    }
}