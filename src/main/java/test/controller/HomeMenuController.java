package main.java.test.controller;

import main.java.test.model.GameFrame;
import main.java.test.view.HomeMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 *  HomeMenu Controller Class:
 *  Act as controller in home Menu
 *  @author Hee Kai Lee
 *  @since 13/12/2021
 */
public class HomeMenuController {
    private HomeMenu homeMenu;
    private GameFrame gameFrame;

    /**
     * HomeMenu Controller Class constructor
     * @param gameFrame gameframe owner
     * @param hMview home menu
     */
    public HomeMenuController(GameFrame gameFrame, HomeMenu hMview) {
        this.gameFrame = gameFrame;
        this.homeMenu = hMview;
        this.homeMenu.addMouseListener(new addMouseL());
        this.homeMenu.AddMouseMotionListener(new addMouseL());

    }

    class addMouseL implements MouseListener, MouseMotionListener{

        /**
         * Override method that implements the back to home when clicked
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            if(homeMenu.getStartButton().contains(p)){
                gameFrame.enableGameBoard();
            }

            else  if(homeMenu.getInfoButton().contains(p)){
                gameFrame.enableInfo();
            }
            else  if(homeMenu.getScoreButton().contains(p)){
                gameFrame.enableScoreboard();
            }
            else if(homeMenu.getMenuButton().contains(p)){
                System.out.println("Goodbye " + System.getProperty("user.name"));
                System.exit(0);
            }

        }

        /**
         *  Override method that implements the back to home when mouse pressed
         *  @param mouseEvent mouse clicked
         */
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            if(homeMenu.getStartButton().contains(p)){
                homeMenu.setStartClicked(true);
                homeMenu.Repaint(homeMenu.getStartButton());
            }
            else if(homeMenu.getMenuButton().contains(p)){
                homeMenu.setMenuClicked(true);
                homeMenu.Repaint(homeMenu.getMenuButton());
            }
            else if(homeMenu.getInfoButton().contains(p)){
                homeMenu.setInfoClicked(true);
                homeMenu.Repaint(homeMenu.getInfoButton());
            }
            else if(homeMenu.getScoreButton().contains(p)) {
                homeMenu.setScoreClicked(true);
                homeMenu.Repaint(homeMenu.getScoreButton());
            }
        }

        /**
         * Override method that implements the back to home mouse clicked and release
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if(homeMenu.isStartClicked() ){
                homeMenu.setInfoClicked(false);
                homeMenu.Repaint(homeMenu.getInfoButton());
            }
            else if(homeMenu.isMenuClicked()) {
                homeMenu.setMenuClicked(false);
                homeMenu.Repaint(homeMenu.getMenuButton());
            }
            else if(homeMenu.isInfoClicked()) {
                homeMenu.setInfoClicked(false);
                homeMenu.Repaint(homeMenu.getInfoButton());
            }
            else if(homeMenu.isScoreClicked()) {
                homeMenu.setScoreClicked(false);
                homeMenu.Repaint(homeMenu.getScoreButton());
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
            if(homeMenu.getStartButton().contains(p) || homeMenu.getMenuButton().contains(p) || homeMenu.getInfoButton().contains(p)|| homeMenu.getScoreButton().contains(p))
                homeMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                homeMenu.setCursor(Cursor.getDefaultCursor());

        }
    }

}

