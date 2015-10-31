package nl.m4jit.framework.presentation.swing.compositecomponents;

import java.awt.GridLayout;
import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {

    public static final int LEFT = 0, CENTER = 1, RIGHT = 2;
    private JLabel label1, label2, label3;

    public StatusBar(String text1, String text2, String text3) {

        super();

        setLayout(new GridLayout(1, 3));

        label1 = new JLabel(text1);
        label1.setHorizontalAlignment(JLabel.LEFT);
        label1.setBorder(BorderFactory.createEtchedBorder());
        add(label1);

        label2 = new JLabel(text2);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setBorder(BorderFactory.createEtchedBorder());
        add(label2);

        label3 = new JLabel(text3);
        label3.setHorizontalAlignment(JLabel.RIGHT);
        label3.setBorder(BorderFactory.createEtchedBorder());
        add(label3);
    }

    public void setText(int location, String text) {

        switch (location) {

            case LEFT:
                label1.setText(text);
                break;

            case CENTER:
                label2.setText(text);
                break;

            case RIGHT:
                label3.setText(text);
                break;

            default:
                throw new Error();
        }
    }

}
