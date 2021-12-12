package test.controller;

import test.model.Ball;
import test.model.Brick;
import test.model.Wall;
import test.model.Player;

/**
 * Debug Panel Controller Class:
 * Design in MVC pattern as controller of debug panel class
 * @author Hee Kai Lee
 * @since 2/12/2021
 */
public class GameBoardController {
    private Wall wall;
    private Ball ball;

    /**
     * GameBoardController constructor
     * @param wall return to Wall.java
     */
    public GameBoardController(Wall wall){
        this.wall = wall;
    }

    /**
     * A method that get wall from wall class
     * @return to wall object
     */
    public Wall getWall(){return wall;}

    /**
     * A method that get wall.ball method from wall class
     * @return to ball object
     */
    public Ball getBall(){return wall.ball;}

    /**
     * A method that get wall.bricks method from wall class
     * @return to brick object
     */
    public Brick[] getBrick(){
        return wall.bricks;
    }

    /**
     * A method that get wall.player method from wall class
     * @return to player object
     */
    public Player getPlayer(){
        return wall.player;
    }

    /**
     * A method that bring user to next level from wall class
     */
    public void nextLevel(){
        wall.nextLevel();
    }

    /**
     * A method that control the movement of ball and player from wall class
     */
    public void move(){
        wall.move();
    }

    /**
     * A method that chech the status of brick if impact occured between wall and ball
     * (from wall class)
     */
    public void findImpact(){
        wall.findImpacts();
    }

    /**
     *  A method that counts the number of bricks from wall class
     *  @return return to the brick count object
     */
    public int brickCount(){
        return wall.getBrickCount();
    }

    /**
     * A method that counts the number of ball from wall class
     * @return return to the ball count object
     */
    public int ballCount(){
        return wall.getBallCount();
    }

    /**
     * A method that check if the ball is lost based on wall class
     * @return return to the ball lost object
     */
    public boolean ballLost(){
        return wall.isBallLost();
    }

    /**
     * A method that reset the ball based on wall class
     */
    public void ballReset(){
        wall.ballReset();
    }

    /**
     * A method that reset the ball based on wall class
     */
    public void wallReset(){
        wall.wallReset();
    }

    /**
     * A method that shows boolean value if ball had remaining based on wall class
     * @return return to the ball end object
     */
    public boolean ballEnd(){ return  wall.ballEnd(); }

    /**
     * A method that shows boolean value if wall had remaining based on wall class
     * @return return to the wall done object
     */
    public boolean isDone(){return wall.isDone();}

    /**
     * A method that check if next level still existed
     * @return return to the next level object
     */
    public boolean hasLevel(){ return wall.hasLevel();}

}
