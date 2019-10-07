// Robot182Demo class developed for Comp 182/L assignment.  This class shows
// how you can use the abstract Robot182 class.

import java.awt.Point;
import java.util.Random;

/**
Robot182Demo class developed for Comp 182/L assignment.  This class shows
how you can use the abstract Robot182 class.
<p>
Robbie the robot has a set of random exploration trials.  
On each "step" in moveTo(Point) Robbie will turnToFace(Point target) its
target Point and take a step forward towards its target. 
<p>
Robot has "trials" explorations on a 2D plane from (-300, 300) to (300, 300).
Each move forward is "steps" long.The robot usually moves a slightly longer 
distance than its target distance because of the casting from doubles to int 
in turnToFace(Point) method.

@since 2/15/2018
@version 1
@author Mike Barnes
*/

@SuppressWarnings("serial")    // no serializable warnings

public class Robot182Demo extends Robot182 {
  // plane for robbie to explore
  private final int WIDTH = 300;
  private final int HEIGHT = 300;
  private final static String robotName = "Robbie";
  private final int step = 10;  // distance forward for each move
  private final int trials = 10;
  private Random random;

  /**
  Make the robot and perform the exploration trials.
  */
  public Robot182Demo() {
    super(robotName);
    random = new Random();
    // explore trials
    for (int i = 0; i < trials; i++)  {
      System.out.printf("Trial %d Robot at (%3d, %3d) %n", i, this.x, this.y);
      moveTo(randomPt());
      }
    }
  
  /**
  @return a random Point on the exploration plance
  */  
  private Point randomPt() {
    return new Point(-WIDTH + random.nextInt(2 * WIDTH), -HEIGHT + random.nextInt(2 * HEIGHT));
    }

  /**
  Take as many "steps" as needed to move to its target
  @param target location of target
  */    
  private void moveTo(Point target) {
    int move = 0;
    int estimate = (int) Math.ceil(distance(target) / step);
    double traveled = 0.0;
    System.out.printf("target at (%3d, %3d) %n", target.x, target.y);
    System.out.printf("distance to target %.2f, estimated moves = %3d %n", distance(target), estimate);
    while (distance(target) > 1.5 * step) {
      Point last = new Point(this);
      turnToFace(target);
      forward(step);
      traveled += distance(last);
      move++;
      }
    // snap to target ?
    if (distance(target) > 0.0) {
      traveled += distance(target);
      x = target.x;
      y = target.y;
      move++;
      }
    System.out.printf("snapped to target, distance = %.2f, moves = %3d, traveled = %.2f %n%n", 
      distance(target), move, traveled);   
    }

  public static void main(String [] arg) {
    new Robot182Demo();
    }
 }
    