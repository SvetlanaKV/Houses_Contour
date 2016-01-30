
//Класс для определения участков с постоянной максимальной высотой
public class Partition implements Comparable {

    private double x = 0;
    private double z = 0;
    private boolean isStart = false;

    Partition(double x, double z, boolean isStart) {
        this.x = x;
        this.z = z;
        this.isStart = isStart;
    }

    Partition() {
        this.x = 0;
        this.z = 0;
        this.isStart = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    @Override
    public int compareTo(Object o) {
        int comp = (int) (this.getX() - ((Partition) o).getX());
        if (comp == 0) {
            if (this.isStart()) {
                if (((Partition) o).isStart()) {
                    comp = 0;
                } else {
                    comp = -1;
                }
            } else {
                if (((Partition) o).isStart()) {
                    comp = 1;
                } else {
                    comp = 0;
                }
            }
        }
        return comp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partition partition = (Partition) o;
        if (Double.compare(partition.x, x) != 0) return false;
        if (Double.compare(partition.z, z) != 0) return false;
        return isStart == partition.isStart;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isStart ? 1 : 0);
        return result;
    }
}
