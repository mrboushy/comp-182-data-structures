/**
 * @author Bishoy Abdelmalik
 * @since  4/5/2019
 */
import java.awt.Point;
import java.util.Deque;
/**abstract class has all the methods for the robots except for learn and remember which will be implemented in each robot seperatly*/
public abstract class Robot extends Robot182 implements  Constant{
	protected double energy;
	protected boolean explore;
	protected Point goal;
	protected Deque<Energy> memory;
	protected RobotMemory plane;
	protected double traveled;
	protected boolean active;
	protected Energy remembered;

	public Robot(Point goal,double charge,RobotMemory aPlane,String botName) {
		super(botName);
		this.goal=goal;
		this.energy=charge;
		this.plane=aPlane;
		this.active=true;
		this.traveled=0;
		isActive();
		isHungry();
		this.remembered=null;

	}
	/**move the robot forward towards the goal or towards an energy location if the robot is active*/
	public void move() {	
		if(isActive()&&isHungry()) {
			if(this.remembered==null) {//get energy goal from memory if no energy goal is set
				this.remembered=remember();
			}	
			moveTo(this.remembered);
			
		}else if(isActive()) {
			this.remembered=null;
			moveTo(this.goal);
		} 
	    if(isActive()) {
	    	detect();
	    }
	   

	}
	/**move toward an energy location unless the energy location passed is null then move to the explore goal*/
	private void moveTo(Energy e) {
		if(e!=null) {
			moveTo(e.getLocation());
		}else {
			moveTo(this.goal);
		}
		
	}
	private void moveTo(Point goal) {
		//check if the input is the goal and check if we are on the goal
		 if (goal==this.goal&&distance(this.goal)== 0) {
		    	do{
		    		this.goal=this.plane.randomPoint();
		    	}while(distance(this.goal)== 0);
		    	goal=this.goal;
		}
		
		double traveled = 0.0;
	   
	    Point last = new Point(this);//save the last point
	    turnToFace(goal);
	    forward(step);
	    traveled += distance(last);//add to the distance traveled the distance to the last point

	    // snap to target ?
	    if (distance(goal) < snapDistance) {
	      traveled += distance(goal);
	      x = goal.x;
	      y = goal.y;
	    }
	    this.energy-=traveled;//reduce the robot charge
	    this.traveled+=traveled;//add the distance traveled in this move to the robot traveled distance 
	 
	    
	    
	    //if we reached the energy location remembered
	    if (this.remembered!=null&&goal==this.remembered.getLocation()&&distance(goal)== 0) {
	       	consumeEnergy(this.remembered);

	    	this.remembered=null;
	    	
	    }
	}
	/**get energy from the energy location 
	 * @param Energy e*/
	private void consumeEnergy(Energy e) {
		if(this.energy<0) {
			this.energy=0;	//reseting energy to 0 if the energy is less than 0 to remove negative values		
		}
		this.energy+=e.supply(robotEnergyCapacity-this.energy);
		
	}
	/**
	 * detects an energy location if its in the robotRobotDetectionDistance and that location is not Emptly
	 * */
	private void detect() {
		for(Energy e:this.plane.getEnergy()) {
			if(distance(e.getLocation())<=robotRobotDetectionDistance&&!e.isEmpty()) {
				learn(e);
			}
		}
	}
	/**
	 * return true if this.energy<(robotEnergyCapacity/2) and also set the explore variable to false if its hungry 
	 * @return boolean true if this.energy<(robotEnergyCapacity/2)
	 * */
	public boolean isHungry() {
		if(this.energy<(robotEnergyCapacity/2)) {
			this.explore=false;
			return true;
		}
		this.explore=true;
		return false;
	}
	/**
	 * @return boolean this.active
	 */
	public boolean isActive() {
		if(energy<=0) {
			this.active=false;
			
	    }
		return this.active;		
	}
	/**@return returns the distance the robot travelled*/
	public double getTraveleDiststance() {
		return this.traveled;		
	}
	
	/**
	 * adds the memory location to the memory of the robot 
	 */
	public abstract void learn(Energy e);
	/**
	 * get the memory location to the memory of the robot 
	 */
	public abstract Energy remember();



}
