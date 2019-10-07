/**
 * @author Bishoy Abdelmalik
 * @since  4/5/2019
 */
import java.awt.Point;
import java.util.LinkedList;
/**robot that uses queue data structure to save the memory locations it finds*/
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
			return null;
		}
		// set the goal to the an energy location from memory
		this.remembered=this.memory.pollFirst();
		return this.remembered;
	}

}
