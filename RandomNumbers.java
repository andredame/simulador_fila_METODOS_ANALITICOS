public class RandomNumbers {
    private double seed;
    private double a;
    private double c;
    private double m;

    public RandomNumbers(double seed, double a, double c, double m) {
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public double next() {
        seed = (a * seed + c) % m;
        return seed / m;
    }

    
}
