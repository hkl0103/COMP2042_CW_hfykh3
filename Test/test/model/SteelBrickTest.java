package test.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {

    private Point pos = new Point(50,150);
    private Dimension size = new Dimension(40, 75);
    SteelBrick steelBrick = new SteelBrick(pos, size);

    private final Point2D center = new Point2D.Double(300, 430 );
    Rectangle brickface = new Rectangle(new Point(50,150), new Dimension(40,75));

    @Test
    void makeBrickFace() {
        Dimension size = new Dimension(40,75);
        assertEquals(brickface, steelBrick.makeBrickFace(pos,size));
    }

    @Test
    void getBrick() {

        assertEquals(brickface, steelBrick.getBrickFace());
    }

    @Test
    void setImpact() {
        assertFalse(steelBrick.setImpact(pos,10));
    }

    @Test
    void impact() {
        steelBrick.impact();
        assertFalse(steelBrick.isBroken());
    }
}