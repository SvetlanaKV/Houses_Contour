import java.util.Random;

//Класс для определения координат дома
class Coordinates implements Comparable {
    private double x = 0;
    private double y = 0;
    private double z = 0;

    Coordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Coordinates() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getZ() {
        return z;
    }

    void setX(double x) {
        this.x = x;
    }

    void setY(double y) {
        this.y = y;
    }

    void setZ(double z) {
        this.z = z;
    }

    void setRandom() {
        Random random = new Random();
        this.x = random.nextInt(500);
        this.y = this.x + random.nextInt(300);
        this.z = random.nextInt(500) + 1;
    }

    void setRandomDouble() {
        Random random = new Random();
        this.x = random.nextDouble() * 500;
        this.y = this.x + random.nextDouble() * 300;
        this.z = random.nextDouble() * 500 + 1;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (-((Coordinates) o).getX() + this.getX());
    }
}
