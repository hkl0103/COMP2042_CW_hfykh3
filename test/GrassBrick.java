package test;

import java.awt.*;
import java.awt.Point;


/**
 * Created by filippo on 04/09/16.
 *
 */
public class GrassBrick extends Brick {

    private static final String NAME = "Grass Brick";
    private static final Color DEF_INNER = new Color(51, 100, 32).darker();
    private static final Color DEF_BORDER = new Color(108, 185, 131);
    private static final int GRASS_STRENGTH = 1;






    public GrassBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER, GRASS_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
