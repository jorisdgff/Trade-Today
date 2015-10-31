package nl.m4jit.framework.presentation.swing.compositecomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author joris
 */
public class SplashScreen extends JWindow {

    private int totaltime, interval, currenttime;
    private boolean ready;
    private JLabel backgroundLabel, textLabel, copyLabel;
    private JPanel southPanel;
    //private JProgressBar progressBar;
    private Timer timer;
    private JFrame frame;

    public SplashScreen(String backgroundPath, String text, int fontsize, String copy) {

        totaltime = 3000;
        interval = 100;

        backgroundLabel = new JLabel(new ImageIcon(backgroundPath));
        backgroundLabel.setLayout(new BorderLayout());
        add(backgroundLabel);

        textLabel = new JLabel("<html><center>" + text);
        textLabel.setFont(new Font(null, Font.BOLD, fontsize));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        backgroundLabel.add(textLabel, BorderLayout.CENTER);

        southPanel = new JPanel(new BorderLayout());
        southPanel.setOpaque(false);
        backgroundLabel.add(southPanel, BorderLayout.SOUTH);

        /*progressBar = new JProgressBar(0, totaltime);
        progressBar.setBorderPainted(false);
        southPanel.add(progressBar, BorderLayout.NORTH);*/

        copyLabel = new JLabel(copy);
        copyLabel.setFont(new Font(null, Font.PLAIN, 12));
        copyLabel.setHorizontalAlignment(JLabel.CENTER);
        copyLabel.setPreferredSize(new Dimension(0, 25));
        southPanel.add(copyLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        /*timer = new Timer(interval, this);
        timer.start();*/
    }

    /*public void setFrame(JFrame val) {

        frame = val;

        if (ready) {

            dispose();
            frame.setVisible(true);
        }
    }*/

    /*public void actionPerformed(ActionEvent ev) {

        currenttime += interval;
        progressBar.setValue(currenttime);

        if (currenttime == totaltime) {

            timer.stop();
            
            if (frame != null) {

                dispose();
                frame.setVisible(true);
            } else {

                ready = true;
            }
        }

        /*progressValue++;
        
         if(progressValue == progressMax + 2){

         try {

         Thread.sleep(400);
         timer.stop();
         
         Thread.sleep(400);

                
         } catch (InterruptedException ex) {

         }
         }
    }*/
}
