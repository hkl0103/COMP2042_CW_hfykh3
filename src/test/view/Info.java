package test.view;


import test.model.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Info Class:
 * Show the info button to guide user in playing
 *  @author Hee Kai Lee
 *  @since 3/12/2021
 */
public class Info extends JComponent implements MouseListener, MouseMotionListener {

    private static final String infoHeader = "HOW TO PLAY";
    private static final String backSection = "BACK TO HOME";
    private static final String homeMenu = "Press Esc to Home Menu.";
    private static final String rightleftArrow = "Press Right or Left Arrow to move.";
    private static final String spacebarPause = "Press Spacebar to  Start or Pause the Game.";
    private static final String debugSection = "Pressed Shift + Alt + F1 to adjust speed & level.";
    private static final Color textColour = new Color(0, 0, 0, 200);
    private static final Color button = Color.WHITE;

    private Font infoHeaderFont;
    private Font buttonFont;
    private Font textFont;

    private static final int windowWidth = 500;
    private static final int windowHeight = 350;

    private Rectangle menuFace;
    private Rectangle backButton;
    private boolean backClicked;

    GameFrame owner;

    Image bgdImage;


    /**
     * Info constructor
     * @param owner gameframe owner
     * @param area size of window
     */
    public Info(GameFrame owner, Dimension area) {
        bgdImage = Toolkit.getDefaultToolkit().getImage("gray brick.jpg");
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        menuFace = new Rectangle(new Point(0,0),area);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        owner.setResizable(false);

        Dimension btnDim = new Dimension(area.width / 2, area.height / 10);
        backButton = new Rectangle(btnDim);

        infoHeaderFont = new Font("Plastic Love",Font.PLAIN,30);
        textFont = new Font("Mangabey", Font.PLAIN, 20);
        buttonFont = new Font("Impact",Font.PLAIN, backButton.height+4);
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
     * @param g2d graphic2D object
     */
    public void drawMenu(Graphics2D g2d){

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawTitle(g2d);
        drawEcsHomeMenu(g2d);
        drawRightLeftArrow(g2d);
        drawSpacebar(g2d);
        drawDebugConsole(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * A method that draw the title of scoreboard
     * @param g2d graphic2D object
     */
    private void drawTitle(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = infoHeaderFont.getStringBounds(infoHeader,frc);
        int xTitle = (int)(menuFace.getWidth() - instructionsRect.getWidth()) / 10;
        int yTitle = (int)(menuFace.getHeight() / 5);

        g2d.setFont(infoHeaderFont);
        g2d.drawString(infoHeader,xTitle,yTitle);
    }

    /**
     * A method that draw the feature of ecs home menu of scoreboard
     * @param g2d graphic2D object
     */
    private void drawEcsHomeMenu(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(homeMenu,frc);
        int xText = (int)(menuFace.getWidth()+ instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 3);

        g2d.setFont(textFont);
        g2d.drawString(homeMenu,xText,yText);
    }

    /**
     *  A method that draw the feature of ecs home menu instruction of scoreboard
     *  @param g2d graphic2D object
     */
    private void drawRightLeftArrow(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(rightleftArrow,frc);
        int xText = (int)(menuFace.getWidth() + instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 2);

        g2d.setFont(textFont);
        g2d.drawString(rightleftArrow,xText,yText);
    }

    /**
     *  A method that draw the feature of space bar instruction of scoreboard
     *  @param g2d graphic2D object
     */
    private void drawSpacebar(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(spacebarPause,frc);
        int xText = (int)(menuFace.getWidth() + instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight()/1.5);

        g2d.setFont(textFont);
        g2d.drawString(spacebarPause,xText,yText);
    }

    /**
     * A method that draw the feature of debug console instruction of scoreboard
     * @param g2d graphic2D object
     */
    private void drawDebugConsole(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(debugSection,frc);
        int xText = (int)(menuFace.getWidth()/12 + instructionsRect.getWidth() / 12 -11);
        int yText = (int)(menuFace.getHeight() / 1.2);

        g2d.setFont(textFont);
        g2d.drawString(debugSection,xText,yText);
    }

    /**
     * A method that draw the feature of button of scoreboard
     *  @param g2d graphic2D object
     */
    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(backSection,frc);

        g2d.setFont(buttonFont);

        int x = ((menuFace.width + backButton.width )) ;
        int y =((menuFace.height * 5 + backButton.height * 8 )/4);
        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - buttonTextRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - buttonTextRect.getHeight()) / 6;

        x += backButton.x;
        y += backButton.y + (backButton.height * 1 );

        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(button);
            g2d.drawString(backSection,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.drawString(backSection,x,y);
        }
    }


    /**
     * Override method that implements the back to home when clicked
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    /**
     * Override method that implements the back to home when mouse pressed
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x, backButton.y, backButton.width * 20, backButton.height+1);
        }
    }

    /**
     * Override method that implements the back to home mouse clicked and release
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked){
            backClicked = false;
            repaint(backButton.x, backButton.y, backButton.width * 20, backButton.height+1);
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
     * Override method that implements the back to home when cursor moved
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
