/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) (C) 2021 Hee Kai Lee (hkl0103)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.java.test.model;

import main.java.test.model.*;
import main.java.test.model.Ball;
import main.java.test.model.Brick;
import main.java.test.model.Crack;
import main.java.test.model.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Wall Class:
 * Functioning the brick, level and scoring system
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class Wall {

    private static final int LEVELS_COUNT = 6;

    private static final int CLAY = 1;
    private static final int GRASS = 2;
    private static final int STEEL = 3;
    private static final int CEMENT = 4;
    private static final int DIAMOND = 5;

    private final Random rnd;
    private Rectangle area;

    public Brick[] bricks;
    public Crack crack;
    public Ball ball;
    public Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * Innitialise the variables
     * @param drawArea rectangle area
     * @param brickCount no.of bricks
     * @param lineCount layer of the wall in game
     * @param brickDimensionRatio the ratio of the brick present (height n width)
     * @param ballPos ball position
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

    }

    /**
     * @param drawArea rectangle area
     * @param brickCnt no.of bricks
     * @param lineCnt layer of walls in game
     * @param brickSizeRatio ratio of the brick (height n width)
     * @param type a type of brick
     * @return return to the single type of brick
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * @param drawArea rectangle area
     * @param brickCnt no.of bricks
     * @param lineCnt layer of walls in game
     * @param brickSizeRatio ratio of the brick (height n width)
     * @param typeA a  type of brick
     * @param typeB another  type of brick
     * @return return to the double type of brick
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * A method to make rubber ball and centered its position
     * @param ballPos position of ball
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }


    /**
     * A method that creates the level and features pf each level.
     * @param drawArea rectangle area
     * @param brickCount no.of bricks
     * @param lineCount layer of walls in game
     * @param brickDimensionRatio ratio of the brick between 2 different type
     * @return return to the levels with different brick
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,GRASS);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL, DIAMOND);
        return tmp;
    }

    /**
     * A mthod that function the movement of ball n paddle
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * A method that how the status when the ball and brick had contacted
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;

        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * A method that determine whether the ball and brick had contacted
     * @return return boolean if have any impact
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up,crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right,crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left,crack.LEFT);
            }
        }
        return false;
    }

    /**
     * A method that return the boolean values
     * @return return boolean if have any impact
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * A method that counts the brick
     * @return return to the no.of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * A method that counts the ball
     * @return return to the no.of ball
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * A method that determine the ball if present
     * @return return to boolean vale if ball lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * A method that reset the ball and paddle if all ball is gone
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * A method that reset the wall and paddle if all ball is gone
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * A method that see if there have any ball left
     * @return return 0 if no ball remaining
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     *  A method that see if the wall is all destroyed
     * @return return 0 if no wall remaining
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * A method that move to next level if the wall is all destroyed
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * A method that see if next level is still exists
     * @return return true/false if no next level
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * A method that set ball speed in x-axis
     * @param s ball speed
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * A method that set ball speed in y-axis
     * @param s ball speed
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     *A method that reset all ball count to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * A method that declare the type of brick
     * @param point position of bricks
     * @param size siz of bricks
     * @param type type of bricks
     * @return return to Brick
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case GRASS:
            out = new GrassBrick(point,size);
            break;
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case DIAMOND:
                out = new DiamondBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

}
