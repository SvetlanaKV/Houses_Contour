import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class PaintContour extends JPanel {

    private List<Coordinates> forGraphics;
    private List<Coordinates> initialHouses;
    private Graphics2D graphics;

    public PaintContour(List<Coordinates> forGraphics, List<Coordinates> initialHouses) {
        this.forGraphics = forGraphics;
        this.initialHouses = initialHouses;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(850, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        this.graphics = g2D;
        drawGraphic();
    }

    public void drawGraphic() {
        //рисуем исходные дома
        Color newColor = new Color(23, 28, 46);
        graphics.setColor(newColor);
        for (int i = 0; i < initialHouses.size(); i++) {
            graphics.draw(new Line2D.Double(initialHouses.get(i).getX(), getHeight(),
                    initialHouses.get(i).getX(), getHeight() - initialHouses.get(i).getZ()));
            graphics.draw(new Line2D.Double(initialHouses.get(i).getX(), getHeight() - initialHouses.get(i).getZ(),
                    initialHouses.get(i).getY(), getHeight() - initialHouses.get(i).getZ()));
            graphics.draw(new Line2D.Double(initialHouses.get(i).getY(), getHeight() - initialHouses.get(i).getZ(),
                    initialHouses.get(i).getY(), getHeight()));
            graphics.draw(new Line2D.Double(initialHouses.get(i).getX(), getHeight(),
                    initialHouses.get(i).getY(), getHeight()));
        }

        //рисуем контур
        newColor = new Color(255, 42, 50);
        graphics.setColor(newColor);
        double x1, x2, y1, y2;
        if (forGraphics.size() > 0) {
            //первая линия
            x1 = forGraphics.get(0).getX();
            x2 = 0;
            y1 = forGraphics.get(0).getX();
            y2 = forGraphics.get(0).getZ();
            graphics.draw(new Line2D.Double(x1, getHeight() - x2, y1, getHeight() - y2));
            //высоты и переходы
            for (int i = 0; i < forGraphics.size() - 1; i++) {
                x1 = forGraphics.get(i).getX();
                x2 = forGraphics.get(i).getZ();
                y1 = forGraphics.get(i).getY();
                y2 = forGraphics.get(i).getZ();
                graphics.draw(new Line2D.Double(x1, getHeight() - x2, y1, getHeight() - y2));
                if (forGraphics.get(i).getZ() != forGraphics.get(i + 1).getZ()) {
                    x1 = forGraphics.get(i).getY();
                    x2 = forGraphics.get(i).getZ();
                    y1 = forGraphics.get(i).getY();
                    y2 = forGraphics.get(i + 1).getZ();
                    graphics.draw(new Line2D.Double(x1, getHeight() - x2, y1, getHeight() - y2));
                }
            }
            //последнее здание
            x1 = forGraphics.get(forGraphics.size() - 1).getX();
            x2 = forGraphics.get(forGraphics.size() - 1).getZ();
            y1 = forGraphics.get(forGraphics.size() - 1).getY();
            y2 = forGraphics.get(forGraphics.size() - 1).getZ();
            graphics.draw(new Line2D.Double(x1, getHeight() - x2, y1, getHeight() - y2));
            x1 = forGraphics.get(forGraphics.size() - 1).getY();
            x2 = forGraphics.get(forGraphics.size() - 1).getZ();
            y1 = forGraphics.get(forGraphics.size() - 1).getY();
            y2 = 0;
            graphics.draw(new Line2D.Double(x1, getHeight() - x2, y1, getHeight() - y2));
        }
        graphics.dispose();
        this.repaint();
    }

}
