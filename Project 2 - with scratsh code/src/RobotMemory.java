import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class RobotMemory implements  Constant  {

	private ArrayList<Energy> energy;
	private Random random;
	public RobotMemory(){
		Sample stack=new Sample("Stack Robot");
		Sample queue=new Sample("Queue Robot");
		for(int i=1;i<=simulationTrials;i++) {
			this.energy=new ArrayList<Energy>();
			random=new Random();
			createEnergyLocations();
			QueueRobot queueRobo= new QueueRobot(randomPoint(), robotEnergyCapacity, this, "Queue Robot");
			StackRobot stackRobo=new StackRobot(randomPoint(),robotEnergyCapacity,this,"Stack Robot");
			while(stackRobo.isActive()||queueRobo.isActive()) {
				if(random.nextBoolean()&&queueRobo.isActive()) {
					queueRobo.move();
				}else {
					stackRobo.move();				
				}
			}

			queue.add(queueRobo.getTraveleDiststance());
			stack.add(stackRobo.getTraveleDiststance());
			/*System.out.print("Queue Robot traveled:");
			System.out.print(queueRobo.getTraveleDiststance());
			System.out.println();

			System.out.print("Stack Robot traveled:");
			System.out.print(stackRobo.getTraveleDiststance());
			System.out.println();
			System.out.println();*/

		}

		System.out.println(queue.toString());
		System.out.println(stack.toString());

		

	}
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
		
		/* used that to test that the min distance is bigger than 20
		 * 	Sample s=new Sample("Energy");
			
			for(int i = 0; i < energyLocations; i++) {
				for(Energy e:this.energy) {
					if(e!=this.energy.get(i)) {
					s.add(this.energy.get(i).distance(e));
					}
				}
			}
			System.out.println(s.toString());*/
		
		
	}



	//return a point from (-planeExtent, -planeExtent) to (planeExtent, planeExtent)
	public Point randomPoint() {
		int x, y;
		x = random.nextInt(planeExtent) - random.nextInt(planeExtent);
		y = random.nextInt(planeExtent) - random.nextInt(planeExtent);
		return new Point(x, y);
	} 
	public  ArrayList<Energy>  getEnergy(){
		return this.energy;
	}
	public static void main(String[] args) {
		new RobotMemory();
	}

}
