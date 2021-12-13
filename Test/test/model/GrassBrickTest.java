package test.model;

import main.java.test.model.GrassBrick;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GrassBrickTest {

    GrassBrick grassbrick = new GrassBrick(new Point(10,5), new Dimension(40, 75));
    Rectangle brickface = new Rectangle(new Point(10,5), new Dimension(40,75));

    @Test
    void makeBrickFace() {
        Point pos = new Point(10,5);
        Dimension size = new Dimension(40,75);
        assertEquals(brickface, grassbrick.makeBrickFace(pos,size));
    }

    @Test
    void getBrick() {
        assertEquals(brickface, grassbrick.getBrickFace());
    }
}