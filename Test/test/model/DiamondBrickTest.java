package test.model;

import main.java.test.model.DiamondBrick;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class DiamondBrickTest {

    public static final int LEFT = 10;

    private Point pos = new Point(50,150);
    private Dimension size = new Dimension(40, 75);
    DiamondBrick diamondbrick = new DiamondBrick(pos, size);

    private final Point2D center = new Point2D.Double(300, 430 );
    Rectangle brickface = new Rectangle(new Point(50,150), new Dimension(40,75));

    @Test
    void makeBrickFace() {
        Dimension size = new Dimension(40,75);
        assertEquals(brickface, diamondbrick.makeBrickFace(pos,size));
    }

    @Test
    void getBrick() {
        assertEquals(brickface, diamondbrick.getBrickFace());
    }

    @Test
    void setImpact() {
        assertFalse(diamondbrick.setImpact(pos,10));
    }

    @Test
    void impact() {
        diamondbrick.impact();
        assertFalse(diamondbrick.isBroken());
    }
}