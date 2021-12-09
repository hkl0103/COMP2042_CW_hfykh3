package test.view;


import test.controller.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

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


    public Info(GameFrame owner, Dimension area) {
        bgdImage = Toolkit.getDefaultToolkit().getImage("picture/gray brick.jpg");
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

        infoHeaderFont = new Font("Impact",Font.PLAIN,30);
        textFont = new Font("Noto Mono", Font.PLAIN, 20);
        buttonFont = new Font("Impact",Font.PLAIN, backButton.height+4);
    }

    public void paint(Graphics g){
        g.drawImage(bgdImage, 0, 0, this);
        drawMenu((Graphics2D)g);
    }

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

    private void drawTitle(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = infoHeaderFont.getStringBounds(infoHeader,frc);
        int xTitle = (int)(menuFace.getWidth() - instructionsRect.getWidth()) / 6;
        int yTitle = (int)(menuFace.getHeight() / 5);

        g2d.setFont(infoHeaderFont);
        g2d.drawString(infoHeader,xTitle,yTitle);
    }

    private void drawEcsHomeMenu(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(homeMenu,frc);
        int xText = (int)(menuFace.getWidth()+ instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 4);

        g2d.setFont(textFont);
        g2d.drawString(homeMenu,xText,yText);
    }

    private void drawRightLeftArrow(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(rightleftArrow,frc);
        int xText = (int)(menuFace.getWidth() + instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 2);

        g2d.setFont(textFont);
        g2d.drawString(rightleftArrow,xText,yText);
    }

    private void drawSpacebar(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(spacebarPause,frc);
        int xText = (int)(menuFace.getWidth() + instructionsRect.getWidth()) / 15;
        int yText = (int)(menuFace.getHeight() / 1.5);

        g2d.setFont(textFont);
        g2d.drawString(spacebarPause,xText,yText);
    }

    private void drawDebugConsole(Graphics2D g2d){

        g2d.setColor(textColour);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(debugSection,frc);
        int xText = (int)(menuFace.getWidth()/12 + instructionsRect.getWidth() / 12 -11);
        int yText = (int)(menuFace.getHeight() / 1.2);

        g2d.setFont(textFont);
        g2d.drawString(debugSection,xText,yText);
    }

    private void drawButton(Graphics2D g2d) {
        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(backSection,frc);

        g2d.setFont(buttonFont);

        int x = ((menuFace.width + backButton.width )) ;
        int y =(int) ((menuFace.height * 5 + backButton.height * 8 )/4);
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



    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            owner.enableHomeMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x, backButton.y, backButton.width * 20, backButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(backClicked){
            backClicked = false;
            repaint(backButton.x, backButton.y, backButton.width * 20, backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
