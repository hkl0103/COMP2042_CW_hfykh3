package test.model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * Abstract Brick Class:
 * Design of the bricks and its operation.
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private String name;
    public Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * A class that create a brick object.
     * @param pos position of brick
     * @param size width and height of brick
     * @param border border colour of brick
     * @param inner inner colour of brick
     * @param strength strength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    /**
     * A method that create a shape of a brick.
     * @param pos position of brick (location (x, y))
     * @param size size of the brick
     * @return return to the shape & size of the brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * A method that see if the brick is broken or not.
     * @param point impact point
     * @param dir impact direction
     * @return return if the brick is destroyed
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     *  A method return to shape of brick
     * @return return to the shape & size of the brick
     */
    public abstract Shape getBrick();

    /**
     *  A method that get the border colour of the brick
     * @return return to border colour of brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * A method that get the inner colour of the brick
     * @return return to inner colour of brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * A method that determine the impact direction between the ball and bricks.
     * @param b the ball
     * @return return to the impact direction
     */
    public final int findImpact(test.model.Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * A method that determine the brick is broken
     * @return return true if the brick is broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * A method that reset the condition of the brick
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * A method that reduce the brick strength
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * A method that return to the brick face.
     * @return return to the brick face
     */
    public Shape getBrickFace(){
        return brickFace;
    }


}





