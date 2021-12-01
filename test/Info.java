package test;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

public class Info {

    private static final Color BG_COLOR = Color.WHITE;
    private JFrame frame = new JFrame("Instruction of the game");

    ImageIcon start;
    JLabel swording;


    public Info() {


        // the panel with text
        JPanel panel = new JPanel();

        //panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        //set the size of the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;

        // set up the frame and display it
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null); // here's the part where i center the jframe on screen
        frame.setVisible(true);
        frame.setBackground(Color.WHITE);

        //layout the text

        start = new ImageIcon(getClass().getResource("/info.png"));
        JLabel swording = new JLabel(start);
        frame.add(swording);
        //panel.add(sWording);
        //("<html><p>Press to START the game</p></html>",start, SwingConstants.HORIZONTAL);

    }



}