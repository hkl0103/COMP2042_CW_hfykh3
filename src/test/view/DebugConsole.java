/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
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
import test.model.Ball;
import test.model.DebugPanel;
import test.model.Wall;
import test.controller.GameBoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *  Debug Console Class:
 *  Functioning in dealing with window
 *  @author Hee Kai Lee
 *  @since 29/11/2021
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugPanelController debugPController;
    private GameBoardController gameboardc;
    private GameBoard gameBoard;
    private Wall wall;


    /**
     * Debug Console constructor
     * @param owner Jframe owner object
     * @param gameboardc Game Board Controller object
     * @param gameBoard GameBoard object
     */
    public DebugConsole(JFrame owner, GameBoardController gameboardc, GameBoard gameBoard){

        this.gameboardc = gameboardc;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPController = new DebugPanelController(new DebugPanel(gameboardc.getWall()));
        this.add(debugPController.getDebugPanelController(),BorderLayout.CENTER);


        this.pack();
    }

    /**
     * A method to initialise the layout of debug console
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * A method that controls the position of debug console
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    /**
     * Override method that open a window (built-in method)
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * Override method that close a window (built-in method)
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    /**
     * Override method that close a window (built-in method)
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     * Override method that minimise a window (built-in method)
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     * Override method that reopen a window after minimising (built-in method)
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = gameboardc.getBall();
        debugPController.setValue(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
