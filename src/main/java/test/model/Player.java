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
package main.java.test.model;

import main.java.test.model.Ball;

import java.awt.*;


/**
 * Player Class:
 * Function that deals with the interface with a player
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class Player {


    public static final Color BORDER_COLOR = Color.WHITE.darker().darker();
    public static final Color INNER_COLOR = Color.LIGHT_GRAY;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * Initialise the variable
     * @param ballPoint position of the ball
     * @param width the width of the ball
     * @param height the height of the ball
     * @param container frame size(area)
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * A method that create rectangle paddle
     * @param width width of paddle
     * @param height height of paddle
     * @return return the position, shape and size of the paddle
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * A method to see whether the ball contact with the paddle
     * @param b the ball
     * @return return boolean value(true) if the ball contacted
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * A method that control the movement of the ball
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * A method that control the paddle to move left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * A method that control the paddle to move right
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * A method that stop everything when the player paused the game
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * A method that get Player face (paddle)
     * @return return to the paddle's shape
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * To set the paddle to the center everytime starting of the game
     * @param p the paddle
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    public int getMoveAmount() {
        return moveAmount;
    }

}
