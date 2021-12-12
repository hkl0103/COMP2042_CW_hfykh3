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
package test.model;

import test.controller.DebugPanelController;
import test.controller.GameBoardController;
import test.view.GameBoard;
import test.view.HomeMenu;
import test.view.Info;
import test.view.Scoreboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * Game Frame Class:
 * Functioning the connection between each button on HomeMenu
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destory";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private Info infoMenu;
    private Scoreboard scoreBoard;
    private Wall wall;
    private DebugPanel debugPanel;
    private DebugPanelController debugPController;
    private GameBoardController gameBController;


    private boolean gaming;

    /**
     * Create the layout(height n width) of the window of HomeMenu, Info, Scoreboard n Gameboard
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));

        debugPanel = new DebugPanel(wall);

        gameBController = new GameBoardController(wall);

        debugPController = new DebugPanelController(debugPanel);

        gameBoard = new GameBoard(this, gameBController, debugPController);

        homeMenu = new HomeMenu(this,new Dimension(500,380));

        infoMenu = new Info(this,new Dimension(200,200));

        scoreBoard = new Scoreboard(this,new Dimension(500,380));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    /**
     * A method that initialise and present the game frame
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * A method enable the start button to open the gameboard window
     */
    public void enableGameBoard() {
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * A method enable the info button to open the info window
     */
    public void enableInfo() {
        this.dispose();
        this.remove(homeMenu);
        this.add(infoMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * A method enable the back home button to go back to home menu
     */
    public void enableHomeMenu() {
        this.dispose();
        this.remove(infoMenu);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * A method enable the back home button to go back to home menu from scoreboard
     */
    public void enableHomeMenuscore() {
        this.dispose();
        this.remove(scoreBoard);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * A method enable the scoreboard button to open scoreboard window
     */
    public void enableScoreboard() {
        this.dispose();
        this.remove(homeMenu);
        this.add(scoreBoard, BorderLayout.CENTER); //or add leaderboard or scoreboard, now test homemenu?
        this.setUndecorated(true);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }


    /**
     * A mthod that auto locate the position of the whole program based on its device's screen size
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * A method that function when the game is focusing
     * @param windowEvent to show status of screen
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * A method that function when the game lost its focus
     * @param windowEvent to show status of screen
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
