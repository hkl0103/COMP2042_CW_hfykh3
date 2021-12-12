package test.controller;

import test.model.DebugPanel;

/**
 *  Debug Panel Controller Class:
 *  Design in MVC pattern as controller of debug panel class
 *  @author Hee Kai Lee
 *  @since 2/12/2021
 */
public class DebugPanelController {

    private DebugPanel debugpanel;

    /**
     * Debug panel controller constructor
     * @param debugpanel debug panel object
     */
    public DebugPanelController(DebugPanel debugpanel){
        this.debugpanel = debugpanel;
    }

    /**
     * A method that get debug panel
     * @return debug panel object
     */
    public DebugPanel getDebugPanelController(){
        return debugpanel;
    }

    /**
     * A method that set the value in x & y-axis
     * @param x set the speed value of ball in x coordinate direction
     * @param y set the speed value of ball in y coordinate direction
     */
    public void setValue(int x, int y){
        debugpanel.setValues(x, y);
    }


}
