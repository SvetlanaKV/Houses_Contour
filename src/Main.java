import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;

public class Main extends JFrame {

    static int numberOfHouses = 10;
    static List<House> houses = new ArrayList<>();
    static Queue<Partition> partitions = new PriorityQueue<>();
    static List<House> baseForGraphics = new ArrayList<>();

    Main() {
        super("Houses Contour");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        PaintContour panel = new PaintContour(baseForGraphics, houses);
        Buttons buttons = new Buttons(panel);
        this.add(panel);
        this.add(buttons, BorderLayout.WEST);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    static void generateHouses() {
        /*
        for (int i = 0; i < numberOfHouses; i++) {
            House newHouse = new House();
            newHouse.setRandom();
            houses.add(newHouse);
            Partition newPartitionX = new Partition(newHouse.getX(), newHouse.getZ(), true);
            Partition newPartitionY = new Partition(newHouse.getY(), newHouse.getZ(), false);
            partitions.add(newPartitionX);
            partitions.add(newPartitionY);
        }
        */

        //проверка "схлопывания домов"
        House newHouse = new House();
        newHouse.setX(0);
        newHouse.setY(100);
        newHouse.setZ(100);
        houses.add(newHouse);
        Partition newPartitionX = new Partition(newHouse.getX(), newHouse.getZ(), true);
        Partition newPartitionY = new Partition(newHouse.getY(), newHouse.getZ(), false);
        partitions.add(newPartitionX);
        partitions.add(newPartitionY);

        House newHouse1 = new House();
        newHouse1.setX(100);
        newHouse1.setY(200);
        newHouse1.setZ(100);
        houses.add(newHouse1);
        Partition newPartitionX1 = new Partition(newHouse1.getX(), newHouse1.getZ(), true);
        Partition newPartitionY1 = new Partition(newHouse1.getY(), newHouse1.getZ(), false);
        partitions.add(newPartitionX1);
        partitions.add(newPartitionY1);

        //для проверки дома длиной = 0
        /*
        House newHouse2 = new House();
        newHouse2.setX(100);
        newHouse2.setY(100);
        newHouse2.setZ(200);
        houses.add(newHouse2);
        Partition newPartitionX2 = new Partition(newHouse2.getX(), newHouse2.getZ(), true);
        Partition newPartitionY2 = new Partition(newHouse2.getY(), newHouse2.getZ(), false);
        partitions.add(newPartitionX2);
        partitions.add(newPartitionY2);
        */

        //трудоемкость: 2n log (2n)
    }

    static void generateHousesDouble() {
        for (int i = 0; i < numberOfHouses; i++) {
            House newHouse = new House();
            newHouse.setRandomDouble();
            houses.add(newHouse);
            Partition newPartitionX = new Partition(newHouse.getX(), newHouse.getZ(), true);
            Partition newPartitionY = new Partition(newHouse.getY(), newHouse.getZ(), false);
            partitions.add(newPartitionX);
            partitions.add(newPartitionY);
        }
        //трудоемкость: 2n log (2n) = O(n logn)
    }

    static void clearAll() {
        houses.clear();
        partitions.clear();
        baseForGraphics.clear();
        //трудоемкость 5n = O(n)
    }

    static void findBaseForGraphics() {
        //определяем отрезки, внутри которых максимальные высота будет постоянна
        int partitionInitialSize = partitions.size();
        //храним текущие высоты и их частоту, если таких высот несколько
        TreeMap<Double, Integer> currentZ = new TreeMap<>(Comparator.reverseOrder());
        Partition element = partitions.poll(); //O(1)
        int index = 0;
        for (int i = 1; i < partitionInitialSize; i++) {
            //House newCut = new House(element.getX(), 0, 0);
            if (element.isStart()) {
                if (currentZ.containsKey(element.getZ())) {
                    int frequency = currentZ.get(element.getZ());
                    currentZ.put(element.getZ(), frequency + 1); //O(log n)
                } else {
                    currentZ.put(element.getZ(), 1);
                }
            } else {
                if (currentZ.get(element.getZ()) > 1) {
                    int frequency = currentZ.get(element.getZ());
                    currentZ.put(element.getZ(), frequency - 1);
                } else {
                    currentZ.remove(element.getZ());
                }
            }
            double currentChosenZ;
            if (currentZ.isEmpty()) {
                currentChosenZ = 0;
            } else {
                currentChosenZ = currentZ.firstKey();
            }
            House newCut = new House(element.getX(), 0, currentChosenZ);
            element = partitions.poll();
            newCut.setY(element.getX());
            if (index > 0 && currentChosenZ == baseForGraphics.get(index - 1).getZ()) {
                baseForGraphics.get(index - 1).setY(newCut.getY());
            } else {
                baseForGraphics.add(newCut);
                index++;
            }
        }
        //Трудоемкость 2n * (log 2n + constT ) = O(n log n)
    }

}
