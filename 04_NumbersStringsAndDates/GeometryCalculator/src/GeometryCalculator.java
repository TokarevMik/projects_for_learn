public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.pow(Math.abs(radius),2) * Math.PI;
        }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return ((4.0/3.0) * Math.pow(Math.abs(radius),3) * Math.PI);
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        if((a+b)>c&(b+c)>a&(c+a)>b){
            return true;
        }

        return false;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if(isTriangleRightAngled(a,b,c)){
            double p = (a+b+c)/2;
            return Math.sqrt(p * (p-a) * (p-b) * (p-c));
        }
        return -1.0;
    }
}
