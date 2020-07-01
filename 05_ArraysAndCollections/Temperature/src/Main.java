public class Main {
    public static int healthCount = 0;
    public static final float MIN_TEMPERATURE = 32.0f;
    public static final float MAX_TEMPERATURE = 40.0f;
    public static final float MIN_HEALTH = 36.2f;
    public static final float MAX_HEALTH = 36.9f;
    public static void main(String[] args) {

        final int patients  = 10;
        float temperatureOfAll = 0;
        float [] temperature = new float[patients];
        System.out.print("Тeмпературы всех пациентов: ");
        for (int i = 0; i <patients ; i++) {
            temperature[i] = MIN_TEMPERATURE + (float) (Math.random()*(MAX_TEMPERATURE-MIN_TEMPERATURE));
            System.out.print(temperature[i] + " ");
            isHealth(temperature[i]);
            temperatureOfAll+=temperature[i];
        }
        System.out.println();
        System.out.println("Средняя температура по больнице " + (temperatureOfAll/patients));
        System.out.println("Количество здоровых: " + healthCount);

    }
    static void isHealth(float t){
        if(t>=MIN_HEALTH&&t<=MAX_HEALTH){
           healthCount++;
        }
    }
}
