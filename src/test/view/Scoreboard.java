package test.view;

import test.model.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;

/**
 * Game Board Class:
 * Functioning the view of gameplay.
 * @author Hee Kai Lee
 * @since 2/12/2021
 */
public class Scoreboard extends JComponent implements MouseListener, MouseMotionListener {


    private static final String infoHeader = "SCORE BOARD";
    private static final String backText = "BACK HOME";

    //private static final Color BG_COLOR = Color.BLACK;
    // private static final Color BORDER_COLOR = new Color(0,0,204); //DarkBlue
    private static final Color DASH_BORDER_COLOR = new  Color(255, 255, 255);
    // private static final Color TEXT_COLOR = new Color(255, 255, 255);//Dark
    private static final Color CLICKED_BUTTON_COLOR = Color.WHITE.brighter();
    private static final Color CLICKED_TEXT = new  Color(0, 0, 0, 200);

    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle backButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private static int x;
    private static int y;
    public static int[][] score;
    public static String Name;

    private Font scoreFont;
    private Font backFont;

    private static final int windowWidth = 500;
    private static final int windowHeight = 350;

    Image bgdImage;

    private GameFrame owner;

    private boolean backClicked;


    /**
     * Functioning to read the txt file and show in the score board
     * @param owner Gameframe owner
     * @param area width and height of the screen
     */
    public Scoreboard (GameFrame owner,Dimension area){

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12); //SIZE
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        // greetingFont = new Font("Serif",Font.BOLD,40);
        scoreFont = new Font("Plastic Love",Font.PLAIN,55);
        backFont = new Font("Impact",Font.PLAIN,20);
        //  textFont = new Font("Serif",Font.BOLD, 25);

    }

    /**
     * A method that draw the scoreboard GUI
     * @param g graphic object
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    /**
     * A method that draw the menu feature of scoreboard
     * @param g2d Graphics2D object
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);
       /* all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.*/

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);

        Load();
    }

    /**
     * A method that read the info in txt file
     */
    public static void Load() {
        try {
            //String one = "";
            //String two = "";
            File file = new File("Highscore.txt"); //load location
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }

            sc.close();
        } catch (FileNotFoundException e){
            System.out.println("Error occur");
            e.printStackTrace();

        } /* catch (IOException e){
            e.printStackTrace();
        }*/
    }


    /**
     * A method that trying to save the file from the user input to txt file
     * @throws IOException
     */
    public static void Save() throws IOException {
        try {
            PrintWriter writer = new PrintWriter("Filetxt/HighScore.txt"); //save location (can add code to change location)
            //writer.write("Name,Score");

            writer.write(System.getProperty("user.name")+"\n24930") ;
            writer.close();
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Error Occur");
            e.printStackTrace();
        }
    }


    /**
     * A method that draw the container of menu container
     * @param g2d Graphics2D object
     */
    private void drawContainer(Graphics2D g2d){

        bgdImage = Toolkit.getDefaultToolkit().getImage("gray brick.jpg");
        Color prev = g2d.getColor();

        //g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        //g2d.setStroke(borderStoke);
        //g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);

        //Image pic = Toolkit.getDefaultToolkit().getImage("Photo/cover-start4.jpg");
        //image = ImageIO.read(getClass().getResource("Photo/cover-start4.jpg"));

        // g2d.drawImage(image, 0, 0, 450, 300, this);
    }

    /**
     * A method that draw the text of scoreboard (back home and title)
     * @param g2d Graphics2D object
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        FontRenderContext frc = g2d.getFontRenderContext();


        Rectangle2D scoreRect = scoreFont.getStringBounds(infoHeader,frc);
        Rectangle2D returnRect = backFont.getStringBounds(backText,frc);

        int sX = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
        int sY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(scoreFont);
        g2d.drawString(infoHeader,sX,sY);

    }

    /**
     * A method that draw the button for the clicked text
     * @param g2d Graphics2D object
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D rRect = backFont.getStringBounds(backText,frc);

        g2d.setFont(backFont);

        int x = ((menuFace.width/2 - backButton.width*2)/4) ;
        int y =(int) ((menuFace.height * 3 + backButton.height * 8)/4);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - rRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - rRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.5);


        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            //g2d.draw(returnButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(backText,x,y);
            g2d.setColor(tmp);
        }
        else{
            //g2d.draw(returnButton);
            g2d.drawString(backText,x,y);
        }



    }

    /**
     * Override method that implements the pause menu (restart, continue & exit)
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenuscore();
        }

    }

    /**
     * Override method that repaint the button in x & y when pressed
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x, backButton.y, backButton.width+1, backButton.height+1);
        }
    }

    /**
     * Override method that repaint the button in x & y when release
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked){
            backClicked = false;
            repaint(backButton.x, backButton.y, backButton.width+1, backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Override method that implements to the button when cursor pointing to it
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}