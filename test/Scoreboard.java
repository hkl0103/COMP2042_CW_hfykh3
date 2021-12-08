package test;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;

public class Scoreboard extends JComponent implements MouseListener, MouseMotionListener {


    private static final String infoHeader = "SCORE BOARD";
    private static final String backButton = "BACK HOME";

    //private static final Color BG_COLOR = Color.BLACK;
   // private static final Color BORDER_COLOR = new Color(0,0,204); //DarkBlue
    private static final Color DASH_BORDER_COLOR = new  Color(255, 255, 255);
    // private static final Color TEXT_COLOR = new Color(255, 255, 255);//Dark
    private static final Color CLICKED_BUTTON_COLOR = Color.WHITE.brighter();
    private static final Color CLICKED_TEXT = new  Color(0, 0, 0, 200);

    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle returnButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private static int x;
    private static int y;
    public static int[][] score;
    public static String Name;

    private Font scoreFont;
    private Font returnFont;

    private static final int windowWidth = 500;
    private static final int windowHeight = 350;

    Image bgdImage;

    private GameFrame owner;

    private boolean returnClicked;


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
        returnButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        // greetingFont = new Font("Serif",Font.BOLD,40);
        scoreFont = new Font("Impact",Font.PLAIN,40);
        returnFont = new Font("Impact",Font.PLAIN,20);
        //  textFont = new Font("Serif",Font.BOLD, 25);

    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


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

    public static void Load() {
        try {
            //String one = "";
            //String two = "";
            File file = new File("picture/Highscore.txt"); //load location
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


    private void drawContainer(Graphics2D g2d){

        bgdImage = Toolkit.getDefaultToolkit().getImage("picture/gray brick.jpg");
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

    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        FontRenderContext frc = g2d.getFontRenderContext();


        Rectangle2D scoreRect = scoreFont.getStringBounds(infoHeader,frc);
        Rectangle2D returnRect = returnFont.getStringBounds(backButton,frc);

        int sX = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
        int sY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(scoreFont);
        g2d.drawString(infoHeader,sX,sY);

    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D rRect = returnFont.getStringBounds(backButton,frc);

        g2d.setFont(returnFont);

        int x = ((menuFace.width/2 - returnButton.width*2)/4) ;
        int y =(int) ((menuFace.height * 3 + returnButton.height * 8)/4);

        returnButton.setLocation(x,y);

        x = (int)(returnButton.getWidth() - rRect.getWidth()) / 2;
        y = (int)(returnButton.getHeight() - rRect.getHeight()) / 2;

        x += returnButton.x;
        y += returnButton.y + (returnButton.height * 0.5);


        if(returnClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            //g2d.draw(returnButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(backButton,x,y);
            g2d.setColor(tmp);
        }
        else{
            //g2d.draw(returnButton);
            g2d.drawString(backButton,x,y);
        }



    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(returnButton.contains(p)){
            owner.enableHomeMenuscore();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(returnButton.contains(p)){
            returnClicked = true;
            repaint(returnButton.x, returnButton.y, returnButton.width+1, returnButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(returnClicked){
            returnClicked = false;
            repaint(returnButton.x, returnButton.y, returnButton.width+1, returnButton.height+1);
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(returnButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}