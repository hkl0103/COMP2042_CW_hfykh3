package main.java.test.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * Abstract Ball Class:
 * shows the location of the ball when moving
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    public Point2D up;
    public Point2D down;
    public Point2D left;
    public Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * To initialise variable
     * @param center center the position
     * @param radiusA radius of the ball
     * @param radiusB radius of the ball
     * @param inner inner colour of the ball(grey)
     * @param border border colour of the ball(dark grey)
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * @param center center the position
     * @param radiusA radius of the ball
     * @param radiusB radius of the ball
     * @return return the shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * a method to show movement of the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * @param x set the speed of ball in x-axis
     * @param y set the speed of ball in y-axis
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     *A method that set the speed of ball in x-axis direction
     * @param s speed of ball in x-axis direction
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * A method that set the speed of ball in y-axis direction
     * @param s speed of ball in y-axis direction
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * A method that reverse the ball at horizontal direction.
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * A method that reverse the ball at vertical direction.
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * A method that get the border colour of the ball.
     * @return return the border colour of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * A method that get the inner colour of the ball.
     * @return return the inner colour of the ball
     */
    public Color getInnerColor(){
        return inner;
   }

    /**
     * A method that return the position of the ball.
     * @return return the center position of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * A method that get the shape and size of the ball.
     * @return return to the shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Set the location of ball when start the game at first
     * @param p the coordinate (x, y) of the ball.
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     *Set the direction which is UP, DOWN, RIGHT, LEFT of the ball when moving
     * @param width width of the ball
     * @param height height of the ball
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * A method that return the speed of ball in x-axis direction
     * @return return speed of ball in x-axis direction
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * A method that return the speed of ball in y-axis direction
     * @return eturn speed of ball in y-axis direction
     */
    public int getSpeedY(){
        return speedY;
    }

}



