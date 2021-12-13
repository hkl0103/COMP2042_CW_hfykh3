/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021 Hee Kai Lee (hkl0103)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.java.test.view;

import main.java.test.model.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * Home Menu Class:
 * Functioning the view screen of homepage with start, info, quit and leaderboard button
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class HomeMenu extends JComponent{

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "BRICK DESTROY";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "START";
    private static final String INFO_TEXT = "HELP";
    private static final String MENU_TEXT = "QUIT";
    private static final String SCOREBOARD_TEXT = "HIGHSCORE";
    //do info at start menu


    private static final Color BORDER_COLOR = new Color(200, 8, 21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(255, 255, 255);//egyptian blue
    private static final Color CLICKED_TEXT = Color.BLACK;
    private static final int BORDER_SIZE = 10;
    private static final float[] DASHES = {20, 10};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;
    private Rectangle infoButton;
    private Rectangle scoreButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private final Font buttonFont;

    //private GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean infoClicked;
    private boolean scoreClicked;


    /**
     * Creating buttons and home menu constructor
     * @param area  size of window screen
     */
    public HomeMenu(Dimension area) {


        this.setFocusable(true);
        this.requestFocusInWindow();

        //this.owner = owner;

        menuFace = new Rectangle(new Point(0, 0), area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        scoreButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, DASHES, 0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono", Font.PLAIN, 10);
        gameTitleFont = new Font("San Marino Beach", Font.BOLD, 50);
        creditsFont = new Font("Monospaced", Font.PLAIN, 10);
        buttonFont = new Font("Impact", Font.PLAIN, startButton.height + 2);

    }


    /**
     * A method that paints the home menu screen
     *
     * @param g graphic object
     */
    public void paint(Graphics g) {
        drawMenu((Graphics2D) g);
    }


    /**
     * A method that draws the home menu with using container, texts &buttons
     *
     * @param g2d graphic2D object
     */
    public void drawMenu(Graphics2D g2d) {

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x, y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x, -y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * A method that draws the container using in game board
     *
     * @param g2d graphic2D object
     */
    private void drawContainer(Graphics2D g2d) {

        MusicPlayer bgm = new MusicPlayer();
        bgm.playSound("resources/backgroundmusic.wav");

        Image background = Toolkit.getDefaultToolkit().getImage("resources/brick.jpg");

        Color prev = g2d.getColor();


        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);
        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);
        g2d.setStroke(tmp);
        g2d.setColor(prev);
        g2d.drawImage(background, 0, 0, this);

    }


    /**
     * A method that draws the texts using in game board
     *
     * @param g2d graphic2D object
     */
    private void drawText(Graphics2D g2d) {

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        int sX, sY;

        sX = (int) (menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int) (menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS, sX, sY);

        sX = (int) (menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE, sX, sY);

        sX = (int) (menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS, sX, sY);


    }

    /**
     * A method that draws the clicking button using in game board
     *
     * @param g2d graphic2D object
     */
    private void drawButton(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT, frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT, frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INFO_TEXT, frc);
        Rectangle2D sTxtRect = buttonFont.getStringBounds(SCOREBOARD_TEXT, frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y = (int) ((menuFace.height - startButton.height) * 0.55);

        startButton.setLocation(x, y);

        x = (int) (startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int) (startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height);


        if (startClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.drawString(START_TEXT, x, y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;
        //start button setting

        menuButton.setLocation(x, y);


        x = (int) (menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int) (menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (menuButton.height);

        if (menuClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.drawString(MENU_TEXT, x, y);
        }

        x = menuButton.x;
        y = menuButton.y;

        y *= 1.2;
        //exit button setting

        infoButton.setLocation(x, y);

        x = (int) (infoButton.getWidth() - iTxtRect.getWidth()) / 2;
        y = (int) (infoButton.getHeight() - iTxtRect.getHeight()) / 2;

        x += infoButton.x;
        y += infoButton.y + (infoButton.height);


        if (infoClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.drawString(INFO_TEXT, x, y);
        }

        x = infoButton.x;
        y = infoButton.y;

        y *= 1.15;
        //info button setting

        scoreButton.setLocation(x, y);

        x = (int) (scoreButton.getWidth() - sTxtRect.getWidth()) / 2;
        y = (int) (scoreButton.getHeight() - sTxtRect.getHeight()) / 2;

        x += scoreButton.x;
        y += scoreButton.y + (scoreButton.height);


        if (scoreClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(SCOREBOARD_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.drawString(SCOREBOARD_TEXT, x, y);
        }


    }

    public void addMouseEvent(MouseListener mouseEvent) {
        this.addMouseListener(mouseEvent);
    }

    public void AddMouseMotionListener(MouseMotionListener mouseEvent) {
        this.addMouseMotionListener(mouseEvent);
    }

    public Rectangle getStartButton(){
        return startButton;
    }

    public Rectangle getMenuButton(){
        return menuButton;
    }

    public Rectangle getInfoButton(){
        return infoButton;
    }

    public Rectangle getScoreButton(){
        return scoreButton;
    }

    public boolean isStartClicked(){
        return startClicked;
    }

    public boolean isMenuClicked(){
        return menuClicked;
    }

    public boolean isInfoClicked(){
        return infoClicked;
    }

    public boolean isScoreClicked(){
        return scoreClicked;
    }

    public void Repaint(Rectangle button){
        repaint(button.x,button.y,button.width+1,button.height+1);
    }

    public void setStartClicked(boolean startClicked){
        this.startClicked = startClicked;
    }

    public void setMenuClicked(boolean menuClicked){
        this.menuClicked = menuClicked;
    }

    public void setInfoClicked(boolean infoClicked){
        this.infoClicked = infoClicked;
    }

    public void setScoreClicked(boolean scoreClicked){
        this.scoreClicked = scoreClicked;
    }
}



