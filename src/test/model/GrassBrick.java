package test.model;

import test.model.Brick;

import java.awt.*;
import java.awt.Point;


/**
 * Grass Brick Class:
 * Creation of a new bricks and apply its characteristic
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class GrassBrick extends Brick {

    private static final String NAME = "Grass Brick";
    private static final Color DEF_INNER = new Color(51, 100, 32).darker();
    private static final Color DEF_BORDER = new Color(108, 185, 131);
    private static final int GRASS_STRENGTH = 1;


    /**
     * ClayBrick class constructor of its parent class, Brick
     * @param point position of brick (location (x, y))
     * @param size size of the brick
     */
    public GrassBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER, GRASS_STRENGTH);
    }

    /**
     * Override the method of the brick class
     * @param pos  position of brick (location (x, y))
     * @param size size of the brick
     * @return return to the shape and size of the brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Override the method to return the shape of brick.
     * @return return to the shape & size of the brick
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
