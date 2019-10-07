// Robot182 class developed for Comp 182/L assignment.  Use but do not 
// modify this class.  Extend this class for your abstract Robot class.

import java.awt.Point;

/**
Robot182 class developed for Comp 182/L assignment.  Use but do not 
modify this class.  Extend this class for your abstract Robot class.
<p>
Robot182 uses modified methods from Barb Ericson's 
<ul type = disc>
<li> <a href = "https://www.cs.uic.edu/~i101/doc/SimpleTurtle.html"> SimpleTurtle.java class
documentation</a>
<li> <a href = "http://coweb.cc.gatech.edu/mediaComp-plan/uploads/101/bookClasses-03-04-10.zip">
all source files</a>
</ul>
<p>
Converted from angles to radians.
<p>
Robot moves forward step "units" on its heading.  
<p>
Robot inherits its position (x,y) from Point
Robot is initially positioned at (0,0)
Robot's heading, the direction it is facing / moving, is initially down (0.0)

@since 2/15/2018
@version 1
@author Mike Barnes
*/

@SuppressWarnings("serial")    // no serializable warnings

public abstract class Robot182 extends Point {

  protected String name;
  protected double heading;  // direction Robot will move 
  
  
  public Robot182 (String botName) {
    super(0, 0);  // initially at (0, 0) coordinate
    name = botName;
    heading = 0.0;  // facing down
    }
  
   /**
   Method to turn towards the given Point
   Adapted from B. Ericson's SimpleTurtle.java class
   @param pt location to turn towards
   */
  public void turnToFace(Point pt) {
    double dx = pt.x - x;
    double dy = pt.y - y;
    double arcTan = 0.0;
    double radians = 0.0;
    
    // avoid a divide by 0
    if (dx == 0){
      // if below the current point
      if (dy > 0)
        heading = Math.PI;      
      // if above the current point
      else if (dy < 0)
        heading = 0;
      }
    // dx isn't 0 so can divide by it
    else {
      arcTan = Math.atan(dy / dx);
      if (dx < 0)
        heading = arcTan - Math.PI / 2.0;
      else 
        heading = arcTan + Math.PI / 2.0;
      }
    }

  /**
   Method to move Robot182 forward the given number of pixels 
   Adapted from B. Ericson's SimpleTurtle.java class
   @param step number of units to walk forward in the heading direction
   */
  public void forward(int step) {
    int oldX = x;
    int oldY = y;
    // change the current position
    x = oldX + (int) (step *  Math.sin(heading));
    y = oldY + (int) (step * -Math.cos(heading));
    }
 
   public String toString() {
    return String.format("%s at (%3d, %3d) heading = %6.3f", name, x, y, heading);
    }

  }