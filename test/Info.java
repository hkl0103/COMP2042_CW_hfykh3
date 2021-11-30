package test;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;


public class Info {

    private JLabel label = new JLabel("<html><p>Hello World!</p></html>", SwingConstants.RIGHT);

    private JLabel hey = new JLabel("<html><p>8888888<br/>9999999</p></html>", SwingConstants.LEFT);
    private JFrame frame = new JFrame();



    public Info() {

        // the panel with text
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));

        //set the size of the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width/6, height/2);

        //layout the text
        panel.add(label);
        panel.add(hey);


        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // here's the part where i center the jframe on screen
        frame.setTitle("Instruction of the game");
        frame.pack();
        frame.setVisible(true);
    }

}