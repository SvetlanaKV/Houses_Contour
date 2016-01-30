import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;

public class Main extends JFrame {

    static int numberOfHouses = 10;
    static List<Coordinates> houses = new ArrayList<>();
    static TreeSet<Double> partition = new TreeSet<>();
    static List<Coordinates> baseForGraphics = new ArrayList<>();

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
        for (int i = 0; i < numberOfHouses; i++) {
            Coordinates newHouse = new Coordinates();
            newHouse.setRandom();
            houses.add(newHouse);
            partition.add(newHouse.getX());
            partition.add(newHouse.getY());
        }
        /*
        houses.sort(new Comparator<Coordinates>() {
            public int compare(Coordinates o1, Coordinates o2) {
                return o1.compareTo(o2);
            }
        });
        */
    }

    static void generateHousesDouble() {
        for (int i = 0; i < numberOfHouses; i++) {
            Coordinates newHouse = new Coordinates();
            newHouse.setRandomDouble();
            houses.add(newHouse);
            partition.add(newHouse.getX());
            partition.add(newHouse.getY());
        }
        /*
        houses.sort(new Comparator<Coordinates>() {
            public int compare(Coordinates o1, Coordinates o2) {
                return o1.compareTo(o2);
            }
        });
        */
    }

    static void clearAll() {
        houses.clear();
        partition.clear();
        baseForGraphics.clear();
    }

    static void findBaseForGraphics() {
        //определяем отрезки, внутри которых максимальные высота будет постоянна
        int partitionInitialSize = partition.size();
        double element = partition.pollFirst();
        for (int i = 1; i < partitionInitialSize; i++) {
            Coordinates newCut = new Coordinates(element, 0, 0);
            element = partition.pollFirst();
            newCut.setY(element);
            baseForGraphics.add(newCut);
        }
        //определяем максимальную высоту у отрезков
        for (int i = 0; i < baseForGraphics.size(); i++) {
            double start = baseForGraphics.get(i).getX();
            double finish = baseForGraphics.get(i).getY();
            for (int j = 0; j < houses.size(); j++) {
                if (houses.get(j).getX() <= start && houses.get(j).getY() >= finish) {
                    if (baseForGraphics.get(i).getZ() < houses.get(j).getZ()) {
                        baseForGraphics.get(i).setZ(houses.get(j).getZ());
                    }
                }
            }
        }
    }

}
