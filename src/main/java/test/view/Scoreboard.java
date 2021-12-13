package main.java.test.view;

import main.java.test.model.GameFrame;

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
public class Scoreboard extends JComponent {


    private static final String infoHeader = "SCORE BOARD";
    private static final String people1 = "Alvins   56";
    private static final String people2 = "Hannah   33";
    private static final String people3 = "James    28";
    private static final String people4 = "Vicky    12";
    private static final String backText = "BACK HOME";

    private static final Color DASH_BORDER_COLOR = new  Color(255, 255, 255);
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
    private Font contentFont;

    private static final int windowWidth = 500;
    private static final int windowHeight = 350;

    Image bgdImage;

    //private GameFrame owner;

    private boolean backClicked;


    /**
     * Functioning to read the txt file and show in the score board
     * @param area width and height of the screen
     */
    public Scoreboard (Dimension area){

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setFocusable(true);
        this.requestFocusInWindow();

        //this.owner = owner;

        bgdImage = Toolkit.getDefaultToolkit().getImage("resources/gray brick.jpg");

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 2, area.height / 12); //SIZE
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        scoreFont = new Font("Plastic Love",Font.PLAIN,55);
        backFont = new Font("Impact",Font.PLAIN,25);
        contentFont = new Font("Hashed Browns",Font.PLAIN,20);

    }

    /**
     * A method that draw the scoreboard GUI
     * @param g graphic object
     */
    public void paint(Graphics g){

        g.drawImage(bgdImage, 0, 0, this);
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

        drawText(g2d);
        drawButton(g2d);

        drawpeople1(g2d);
        drawpeople2(g2d);
        drawpeople3(g2d);
        drawpeople4(g2d);

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
            File file = new File("resources/Highscore.txt"); //load location
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }

            sc.close();
        } catch (FileNotFoundException e){
            System.out.println("Error occur");
            e.printStackTrace();

        }
    }


    /**
     * A method that trying to save the file from the user input to txt file
     * @throws IOException
     */
    public static void Save() throws IOException {
        try {
            PrintWriter writer = new PrintWriter("resources/HighScore.txt"); //save location (can add code to change location)
            writer.write("Name,Score");

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

        bgdImage = Toolkit.getDefaultToolkit().getImage("resources/gray brick.jpg");
        Color prev = g2d.getColor();


        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);
        g2d.draw(menuFace);
        g2d.setStroke(tmp);
        g2d.setColor(prev);

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

        int p1X = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
        int p1Y = (int)(menuFace.getHeight() /2);


        g2d.setFont(scoreFont);
        g2d.drawString(infoHeader,sX,sY);
        g2d.setFont(contentFont);
    }

    /**
     *  A method that draw the text of score
     * @param g2d Graphics2D object
     */
    private void drawpeople1(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = contentFont.getStringBounds(people1,frc);
        int xText = (int)(menuFace.getWidth()+ instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 3);

        g2d.setFont(contentFont);
        g2d.drawString(people1,xText,yText);
    }

    /**
     *  A method that draw the text of score
     * @param g2d Graphics2D object
     */
    private void drawpeople2(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = contentFont.getStringBounds(people2,frc);
        int xText = (int)(menuFace.getWidth()+ instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 2);

        g2d.setFont(contentFont);
        g2d.drawString(people2,xText,yText);
    }

    /**
     *  A method that draw the text of score
     * @param g2d Graphics2D object
     */
    private void drawpeople3(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = contentFont.getStringBounds(people3,frc);
        int xText = (int)(menuFace.getWidth()+ instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 1.5);

        g2d.setFont(contentFont);
        g2d.drawString(people3,xText,yText);
    }

    /**
     *  A method that draw the text of score
     * @param g2d Graphics2D object
     */
    private void drawpeople4(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = contentFont.getStringBounds(people4,frc);
        int xText = (int)(menuFace.getWidth()+ instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 1.2);

        g2d.setFont(contentFont);
        g2d.drawString(people4,xText,yText);
    }

    /**
     * A method that draw the button for the clicked text
     * @param g2d Graphics2D object
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D rRect = backFont.getStringBounds(backText,frc);

        g2d.setFont(backFont);

        int x = ((menuFace.width + backButton.width*2)/5+80) ;
        int y =(int) ((menuFace.height * 3 + backButton.height * 8)/4);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - rRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - rRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.8);


        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(backText,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.drawString(backText,x,y);
        }

    }
    public void addMouseEvent(MouseListener mouseEvent) {
        this.addMouseListener(mouseEvent);
    }
    public void AddMouseMotionListener(MouseMotionListener mouseEvent) {
        this.addMouseMotionListener(mouseEvent);
    }

    public Rectangle getBackButton() { return backButton;}

    public void setBackClicked(boolean backClicked) { this.backClicked = backClicked;}

    public boolean isBackClicked(){
        return backClicked;
    }

    public void Repaint(Rectangle button){
        repaint(button.x,button.y,button.width+1,button.height+1);
    }
}