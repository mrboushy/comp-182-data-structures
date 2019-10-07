import java.awt.Point;
import java.util.Deque;

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
	public void move() {	
		if(isActive()&&isHungry()) {
			//get energy goal from memory
			if(this.remembered==null) {
				this.remembered=remember();
			}	
			moveTo(this.remembered);
			
		}else if(isActive()) {
			this.remembered=null;
			moveTo(this.goal);
		} 
	    isActive();
	    detect();
	    /*System.out.println(this.toString()+" charge "+this.energy);
	    System.out.println();*/

	}
	
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
		
		int move = 0;
	    int estimate = (int) Math.ceil(distance(goal) / step);
	    double traveled = 0.0;
	   
	    Point last = new Point(this);
	    turnToFace(goal);
	    forward(step);
	    traveled += distance(last);
	    move++;

	    // snap to target ?
	    if (distance(goal) < snapDistance) {
	      traveled += distance(goal);
	      x = goal.x;
	      y = goal.y;
	      move++;
	    }
	    this.energy-=traveled;
	    this.traveled+=traveled;
	 
	    
	    
	    //if we reached the energy location remembered
	    if (this.remembered!=null&&goal==this.remembered.getLocation()&&distance(goal)== 0) {
	       	consumeEnergy(this.remembered);
	    	this.remembered=null;
	    }
	}
	private void consumeEnergy(Energy e) {
		if(this.energy<0) {
			this.energy=0;	//reseting energy to 0 if the energy is less than 0 to rmove negative values		
		}
		this.energy+=e.supply(robotEnergyCapacity-this.energy);
		
	}
	private void detect() {
		for(Energy e:this.plane.getEnergy()) {
			if(distance(e.getLocation())<=robotRobotDetectionDistance&&!e.isEmpty()) {
				learn(e);
			}
		}
	}
	
	public boolean isHungry() {
		if(this.energy<(robotEnergyCapacity/2)) {
			this.explore=false;
			return true;
		}
		this.explore=true;
		return false;
	}
	public boolean isActive() {
		if(energy<=0) {
			this.active=false;
			
	    }
		return this.active;		
	}
	public double getTraveleDiststance() {
		return this.traveled;		
	}
	
	public abstract void learn(Energy e);
	public abstract Energy remember();



}
