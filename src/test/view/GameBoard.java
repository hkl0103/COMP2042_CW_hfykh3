/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021 Hee Kai Lee(hkl0103)
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
package test.view;

import test.controller.DebugPanelController;
import test.controller.GameBoardController;
import test.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


/**
 * Game Board Class:
 * Functioning the view of gameplay.
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(94, 137, 229);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.BLACK;
    private final GameFrame owner;

    private GameBoardController gameBController;
    private Timer gameTimer;

    private Wall wall;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;


    private int score = 0;


    /**
     * gameBoard constructor that control the views during gameplay
     *  @param owner GamrFrame onwer
     */
    public GameBoard(JFrame owner, GameBoardController gameBController, DebugPanelController debugPanelController){
        super();

        strLen = 0;
        showPauseMenu = false;
        this.owner = (GameFrame) owner;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        this.initialize();
        this.gameBController = gameBController;
        message = "";
        gameBController = new GameBoardController(new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),35,4,6/2,new Point(300,430)));


        debugConsole = new DebugConsole(owner, gameBController,this);
        //initialize the first level
        this.gameBController.nextLevel();

        gameTimer = new Timer(10,e ->{
            this.gameBController.move();
            this.gameBController.findImpact();
            message = String.format("Bricks: %d Balls %d", this.gameBController.brickCount(), this.gameBController.ballCount());
            if(this.gameBController.ballLost()){
                if(this.gameBController.ballEnd()){
                    this.gameBController.wallReset();
                    message = "Game over";
                    //this.owner.enableGameOverMenu();
                }
                this.gameBController.ballReset();
                gameTimer.stop();
            }
            else if(this.gameBController.isDone()){
                if(this.gameBController.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    this.gameBController.ballReset();
                    this.gameBController.wallReset();
                    this.gameBController.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }

            repaint();
        });

    }

    /**
     * A method that initializing the gameboard
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * A method that draw the GUI in game board view
     * @param g graphic object
     */
    public void paint(Graphics g){


        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
        g2d.setColor(Color.WHITE);
        g2d.drawString(message,250,225);

        drawBall(gameBController.getBall(),g2d);

        for(Brick b : gameBController.getBrick())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(gameBController.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();

        //score
        g.setColor(Color.green);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score : " +score,500,30);
    }

    /**
     * A method that clear the gam board
     * @param g2d Graphic2D obeject
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * A method that draw the feature of brick in gameboard
     * @param brick brick
     * @param g2d graphic2D object
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());
        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());
        g2d.setColor(tmp);
    }

    /**
     * A method that draw the feature of ball in gameboard
     * @param ball ball (rubber ball)
     * @param g2d graphic2D object
     */
    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();
        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);
        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);
        g2d.setColor(tmp);
    }

    /**
     * A method that draw the interface feature of player in gameboard
     * @param p player object
     * @param g2d graphic2D object
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * A method that draw gameboard screen itself
     * @param g2d graphic2D object
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * A method thatobsure the gameboard
     * @param g2d graphic2D object
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * A method that draw pause menu
     * @param g2d graphic2D object
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_LEFT:
                gameBController.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                gameBController.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                gameBController.getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameBController.getPlayer().stop();
    }

    /**
     * Override method that implements the pause menu (restart, continue & exit)
     * @param mouseEvent mouse clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            gameBController.ballReset();
            gameBController.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

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
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Override method that see whether the gameplay is focusing
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

}
