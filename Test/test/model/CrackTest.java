package test.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class CrackTest {

    CementBrick cementBrick = new CementBrick(new Point(50,30),new Dimension(50,80));
    Crack crack = new Crack(cementBrick,3,20);
    private final Point2D center = new Point2D.Double(300, 430 );
    Point point = new Point(20,30);

    @Test
    void reset() {
        assertFalse(cementBrick.isBroken());
    }

    @Test
    void makeCrack() {
        crack.makeCrack(center,20);
    }

    @Test
    void testMakeCrack() {
        crack.makeCrack(point,new Point(20,30));
        assertNotNull(crack);
    }
}