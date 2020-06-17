public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    private double countOfFood = 0.0;
    private static int catCount = 0;
    private boolean alive;

    public Cat() {
        catCount++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        alive = true;

    }
    public boolean isAlive(double weight){
        if(weight < minWeight|weight>maxWeight){
            alive = false;
        }
        return alive;
    }
    public static int getCatCount() {

        return catCount;
    }

    public void meow() {
        if( isAlive(weight)){
            weight = weight - 1;
            System.out.println("Meow");
        }else {
            System.out.println("Can't meow. Cai is dead");
        }
    }
    public void pee(){
        if( isAlive(weight)){
            weight = weight - 3.0;
            System.out.println("Cat lost 3 g. of weight");
        } else {
            System.out.println("Can't meow. Cat is dead");
        }
    }
    public void feed(double amount) {
        if( isAlive(weight)){
            weight = weight + amount;
            countOfFood = countOfFood + amount;
        }else {
            System.out.println("Can't eat. Cat is dead");
        }
    }

    public void drink(double amount) {
        if( isAlive(weight)) {
            weight = weight + amount;
            countOfFood = countOfFood + amount;
        } else{
            System.out.println("Can't drink. Cat is dead");
        }
    }

    //public double getCountOfFood() {
    //    return countOfFood;
    //}

    public Double getWeight() {
        return weight;
    }

    public String getStatus() {
        if (weight < minWeight) {
            catCount--;
            return "Dead";
        } else if (weight > maxWeight) {
            catCount--;
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}