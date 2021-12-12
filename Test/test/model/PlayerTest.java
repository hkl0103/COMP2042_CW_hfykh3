package test.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player(new Point(450,330),80,50,new Rectangle(100,50));
    RubberBall ball = new RubberBall(new Point(450,330));

    @Test
    void impact() {
        assertTrue(player.impact(ball));
    }

    @Test
    void move() {
        player.move();
        int x = (int) ball.getPosition().getX()+15;
        Point p = new Point(x,330);
        assertEquals(new Point(465,330),p);
    }

    @Test
    void moveLeft() {
        player.moveLeft();
        assertEquals(-5,player.getMoveAmount());
    }

    @Test
    void moveRight() {
        player.moveRight();
        assertEquals(5,player.getMoveAmount());
    }


    @Test
    void stop() {
        player.stop();
        assertEquals(0,player.getMoveAmount());
    }

    @Test
    void getPlayerFace() {
        Rectangle playerFace = new Rectangle(410,330,80,50);
        assertEquals(playerFace,player.getPlayerFace());
    }

    @Test
    void moveTo() {
        player.moveTo(new Point(450,330));
        Point playerPosition = new Point(450-(80/2),330);
        assertEquals(new Point(410,330), playerPosition);
    }

}