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

import main.java.test.model.Ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Rubber Ball Class:
 * Extending from ball class
 * @author Hee Kai Lee
 * @since 29/11/2021
 */
public class RubberBall extends Ball {

    private static final int DEF_RADIUS = 15;
    private static final Color DEF_INNER_COLOR = new Color(210, 199, 199);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();


    /**
     * Construxtor of rubber ball
     * @param center the center locatin of the ball
     */
    public RubberBall(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


    /**
     * Override method that creathe the shape of ball
     * @param center  center the position
     * @param radiusA radius of the ball
     * @param radiusB radius of the ball
     * @return return to the shape of ball
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / 1);
        double y = center.getY() - (radiusB / 1);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
