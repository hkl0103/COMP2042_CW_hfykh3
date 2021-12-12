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
 * Steel Brick Class:
 * Creation of a new bricks and apply its characteristic
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.WHITE;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }

    /**
     * Steel Brick class constructor of its parent class, Brick
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
     * Override the method that see if the brick is broken
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
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
