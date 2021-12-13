package main.java.test.controller;

import main.java.test.model.GameFrame;
import main.java.test.view.Scoreboard;
import main.java.test.model.GameFrame;
import main.java.test.view.Scoreboard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Info Controller Class:
 * Act as controller in info
 * @author Hee Kai Lee
 * @since 13/12/2021
 */
public class ScoreController {

    private Scoreboard scoreboard;
    private GameFrame gameFrame;

    /**
     * Scoreboard Controller Class constructor
     * @param gameFrame gameframe object
     * @param Sview scoreboard
     */
    public ScoreController (GameFrame gameFrame, Scoreboard Sview){
        this.gameFrame = gameFrame;
        this.scoreboard = Sview;
        this.scoreboard.addMouseListener(new addMouseL());
        this.scoreboard.AddMouseMotionListener(new addMouseL());
    }


    class addMouseL implements MouseListener, MouseMotionListener {
        /**
         * Override method that implements the pause menu (restart, continue & exit)
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            if(scoreboard.getBackButton().contains(p)){
                gameFrame.enableHomeMenuscore();
            }

        }

        /**
         * Override method that repaint the button in x n y when pressed
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            if(scoreboard.getBackButton().contains(p)){
                scoreboard.setBackClicked(true);
                scoreboard.Repaint(scoreboard.getBackButton());
            }
        }

        /**
         * Override method that repaint the button in x n y when release
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if(scoreboard.isBackClicked()) {
                scoreboard.setBackClicked(false);
                scoreboard.Repaint(scoreboard.getBackButton());
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
            if(scoreboard.getBackButton().contains(p))
                gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameFrame.setCursor(Cursor.getDefaultCursor());

        }
    }
}
