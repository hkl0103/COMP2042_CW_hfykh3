package main.java.test.model;

import main.java.test.model.Brick;

import java.awt.*;
import java.awt.Point;


/**
 * Clay Brick Class:
 * Creation of a new bricks and apply its characteristic
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(105, 28, 28).darker();
    private static final Color DEF_BORDER = new Color(180, 46, 46);
    private static final int CLAY_STRENGTH = 1;

    /**
     * ClayBrick class constructor of its parent class, Brick
     * @param point position of the cement brick
     * @param size the width and height of the cement brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * Override the method of the brick class
     * @param pos  position of brick (location (x, y))
     * @param size size of the brick
     * @return return to the shape and size of the brick
     */
    @Override
    public Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Override the method to return the shape of brick.
     * @return return to the shape n size of the brick
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
