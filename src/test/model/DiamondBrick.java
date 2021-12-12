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
package test.model;

import test.model.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * Golden Brick Class:
 * Creation of a new bricks and apply its characteristic
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class DiamondBrick extends Brick {

    private static final String NAME = "Golden Brick";
    private static final Color DEF_INNER = new Color(96, 204, 190);
    private static final Color DEF_BORDER = new Color(165, 255, 234);
    private static final int STEEL_STRENGTH = 3;
    private static final double GOLDEN_PROBABILITY = 0.9;

    private Random rnd;
    private Shape brickFace;

    /**
     * GoldenBrick class constructor of its parent class, Brick
     * @param point position of the cement brick
     * @param size the width and height of the cement brick
     */
    public DiamondBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
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
        return brickFace;
    }

    /**
     * Override the method that see if the brick is broken or not.
     * @param point impact point
     * @param dir   impact direction
     * @return return if the brick is destroyed
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * Override method that reduce the brick strength
     */
    public void impact(){
        if(rnd.nextDouble() < GOLDEN_PROBABILITY){
            super.impact();
        }
    }

}
