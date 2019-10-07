/**
 * @author Bishoy Abdelmalik
 * @since  4/5/2019
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**RobotMemory is the application class it have the code to generate the energy locations and control which robot to move*/  
public class RobotMemory implements  Constant  {

	private ArrayList<Energy> energy;//hold the energy locations
	private Random random;
	public RobotMemory(){
		//2 sample instance to hold the traveled distance after each simulation run
		Sample stack=new Sample("Stack Robot");
		Sample queue=new Sample("Queue Robot");
		for(int i=1;i<=simulationTrials;i++) {
			this.energy=new ArrayList<Energy>();
			random=new Random();
			createEnergyLocations();
			QueueRobot queueRobo= new QueueRobot(randomPoint(), robotEnergyCapacity, this, "Queue Robot");
			StackRobot stackRobo=new StackRobot(randomPoint(),robotEnergyCapacity,this,"Stack Robot");
			
			while(stackRobo.isActive()||queueRobo.isActive()) {//while either robot is active
				if(random.nextBoolean()&&queueRobo.isActive()) {//if queue is active and the nextBoolean is true then queue robot moves
					queueRobo.move();
				}else {//if queue is not active or the nextBoolean is false then stack robot moves
					stackRobo.move();				
				}
			}

			queue.add(queueRobo.getTraveleDiststance());
			stack.add(stackRobo.getTraveleDiststance());

		}

		System.out.println(queue.toString());
		System.out.println();
		System.out.println(stack.toString());
		

		

	}
	/**creates the energy locations and add them to ArrayList*/
	private void createEnergyLocations() {
		Energy newEnergy = null;
		boolean ok;  // is newEnergy valid or "OK"
		for(int i = 0; i < energyLocations; i++) {
			ok = false;  
			// create an energy and test its position until a valid position is found 
			while (! ok) { // while new energy is not valid keep looking
			   newEnergy = new Energy(randomPoint(), energyInitialCapacity);
			   ok = true; // assume new energy will be OK
			   // test is new energy is OK
				for(Energy e:this.energy) {
					if(newEnergy.distance(e) <= energyIntervalDistance) {
						ok =false;
					}
				}
				
			}
		    this.energy.add(newEnergy);  // new energy is valid so add to arraylist

		}		
	}

	/**return a point from (-planeExtent, -planeExtent) to (planeExtent, planeExtent)*/
	public Point randomPoint() {
		int x, y;
		x = random.nextInt(planeExtent) - random.nextInt(planeExtent);
		y = random.nextInt(planeExtent) - random.nextInt(planeExtent);
		return new Point(x, y);
	} 
	/**returns the ArrayList that has the energy locations*/
	public  ArrayList<Energy>  getEnergy(){
		return this.energy;
	}
	public static void main(String[] args) {
		new RobotMemory();
	}

}
