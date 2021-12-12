package test.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;
import static test.model.Brick.LEFT_IMPACT;

class CementBrickTest {

    public static final int LEFT = 10;

    private Point pos = new Point(50,150);
    private Dimension size = new Dimension(40, 75);
    CementBrick cementBrick = new CementBrick(pos, size);

    private final Point2D center = new Point2D.Double(300, 430 );
    Rectangle brickface = new Rectangle(new Point(50,150), new Dimension(40,75));


    @Test
    void makeBrickFace() {
       
        Dimension size = new Dimension(40,75);
        assertEquals(brickface, cementBrick.makeBrickFace(pos,size));
    }

    @Test
    void setImpact() {
        assertFalse(cementBrick.setImpact(pos,10));
    }

    @Test
    void getBrick() {
        assertEquals(brickface, cementBrick.getBrick());
    }


    @Test
    void repair() {
        assertFalse(cementBrick.isBroken());
    }

}