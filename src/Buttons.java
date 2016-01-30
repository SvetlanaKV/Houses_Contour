
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//класс для определения кнопок
public class Buttons extends JPanel {
    private JButton buttonGenerateInt, ButtonGenerateDouble, buttonPaint;

    public Buttons(PaintContour pc) {
        setLayout(new GridLayout(3, 1));
        buttonGenerateInt = new JButton("Generate Houses (int coordinates)");
        ButtonGenerateDouble = new JButton("Generate Houses (double coordinates)");
        buttonPaint = new JButton("Paint Contour");
        buttonPaint.setEnabled(false);
        buttonGenerateInt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.clearAll();
                Main.generateHouses();
                buttonPaint.setEnabled(true);
            }
        });
        ButtonGenerateDouble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.clearAll();
                Main.generateHousesDouble();
                buttonPaint.setEnabled(true);
            }
        });
        buttonPaint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.findBaseForGraphics();
                buttonPaint.setEnabled(false);
            }
        });
        add(buttonGenerateInt);
        add(ButtonGenerateDouble);
        add(buttonPaint);
    }
}
