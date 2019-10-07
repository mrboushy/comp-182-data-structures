import java.awt.Point;
import java.util.LinkedList;

public class QueueRobot extends Robot{

	public QueueRobot(Point goal, double charge, RobotMemory aPlane, String botName) {
		super(goal, charge, aPlane, botName);
		this.memory=new LinkedList<>();
	}
	
	@Override
	public void learn(Energy e) {
		this.memory.remove(e);
		this.memory.addLast(e);	
		
	}

	@Override
	public Energy remember() {
		if(this.memory.isEmpty()) {	
			this.remembered=null;
			return null;
		}
		// set the goal to the an energy location from memory
		this.remembered=this.memory.pollFirst();
		return this.remembered;
	}

}
