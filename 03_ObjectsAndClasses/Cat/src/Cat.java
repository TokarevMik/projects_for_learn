public class Cat {
    private double originWeight;
    private double weight;
    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;


    private double countOfFood = 0.0;
    private static int catCount = 0;
    private boolean alive;
    private CatsColor color;

    public void setColor(CatsColor color) {
        this.color = color;
    }

    public CatsColor getColor() {
        return color;
    }


    public Cat() {
        catCount++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        alive = true;

    }

    public Cat(double weight) {
        catCount++;
        this.weight = weight;
        originWeight = weight;
        alive = true;

    }
    public Cat(Cat catOrig) {
        this.color = catOrig.color;
        this.weight = catOrig.weight;
        this.originWeight = catOrig.originWeight;
        this.alive = catOrig.alive;
        this.countOfFood = catOrig.countOfFood;


    }

    public boolean isAlive(double weight) {
        if (weight < MIN_WEIGHT | weight > MAX_WEIGHT) {
            alive = false;
            }
        return alive;
    }

    public static int getCatCount() {

        return catCount;
    }

    public void meow() {
        if (isAlive(weight)) {
            weight = weight - 1;
            System.out.println("Meow");
            if (isAlive(weight)) {
                catCount--;
            }
        } else {
            System.out.println("Can't meow. Cai is dead");
        }
    }

    public void pee() {
        if (isAlive(weight)) {
            weight = weight - 3.0;
            System.out.println("Cat lost 3 g. of weight");
            if (isAlive(weight)) {
                catCount--;
            }
        } else {
            System.out.println("Can't meow. Cat is dead");
        }
    }

    public void feed(double amount) {
        if (isAlive(weight)) {
            weight = weight + amount;
            countOfFood = countOfFood + amount;
            if (isAlive(weight)) {
                catCount--;
            }
        } else {
            System.out.println("Can't eat. Cat is dead");
        }
    }

    public void drink(double amount) {
        if (isAlive(weight)) {
            weight = weight + amount;
            countOfFood = countOfFood + amount;
            if (isAlive(weight)) {
                catCount--;
            }
        } else {
            System.out.println("Can't drink. Cat is dead");
        }
    }

    public double getCountOfFood() {
        return countOfFood;
    }

    public Double getWeight() {
        return weight;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public static int getEyesCount() {
        return EYES_COUNT;
    }

    public static double getMinWeight() {
        return MIN_WEIGHT;
    }

    public static double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCountOfFood(double countOfFood) {
        this.countOfFood = countOfFood;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}