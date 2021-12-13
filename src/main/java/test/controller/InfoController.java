package main.java.test.controller;

import main.java.test.model.GameFrame;
import main.java.test.view.HomeMenu;
import main.java.test.view.Info;

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
public class InfoController {
    private Info info;
    private GameFrame gameFrame;

    /**
     * Info Controller Class constructor
     * @param gameFrame gameframe object
     * @param Iview info
     */
    public InfoController(GameFrame gameFrame, Info Iview){
        this.gameFrame = gameFrame;
        this.info = Iview;
        this.info.addMouseListener(new addMouseL());
        this.info.AddMouseMotionListener(new addMouseL());

    }
    class addMouseL implements MouseListener, MouseMotionListener {
        /**
         * Override method that implements the back to home when clicked
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            if(info.getBackButton().contains(p)){
                gameFrame.enableHomeMenu();
            }
        }

        /**
         * Override method that implements the back to home when mouse pressed
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            if(info.getBackButton().contains(p)){
                info.setBackClicked(true);
                info.Repaint(info.getBackButton());
            }
        }

        /**
         * Override method that implements the back to home mouse clicked and release
         * @param mouseEvent mouse clicked
         */
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if(info.isBackClicked()){
                info.setBackClicked(false);
                info.Repaint(info.getBackButton());
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
            if(info.getBackButton().contains(p))
                info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                info.setCursor(Cursor.getDefaultCursor());
        }
    }

}
