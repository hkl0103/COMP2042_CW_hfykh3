package test.model;

import main.java.test.model.ClayBrick;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {

    ClayBrick claybrick = new ClayBrick(new Point(10,5), new Dimension(40, 75));
    Rectangle brickface = new Rectangle(new Point(10,5), new Dimension(40,75));

    @Test
    void makeBrickFace() {

        Point pos = new Point(10,5);
        Dimension size = new Dimension(40,75);
        assertEquals(brickface, claybrick.makeBrickFace(pos,size));
    }

    @Test
    void getBrick() {
        assertEquals(brickface, claybrick.getBrickFace());
    }
}