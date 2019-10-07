/**
 * @author Bishoy Abdelmalik
 * @since  4/5/2019
 */
import java.awt.Point;

/**single energy node contains the charge and the the energy node loacation*/
public class Energy {
	private double charge;
	private Point location;
	private boolean empty;
	/**
	 * constructor initialize the variables and call the isEmpty method to check if the energy node is empty
	 * @param Point p
	 * @param double charge
	 */
	Energy(Point p, double charge){
		this.location=p;
		this.charge=charge;
		isEmpty();
		
	}
	/**
	 * check if the energy node is empty
	 * @return true if its empty
	 * */
	public boolean isEmpty() {
		if(this.charge<=0) {
			this.empty= true;
			return this.empty;
		}
		this.empty=false ;
		return this.empty;	
	}
	/**
	 * supply the robot with energy that it requests 
	 * @param double requested
	 * @return double supply after if it checks the amount it will return
	 */
	public double supply(double requested) {
		double supply=0;
		if(!isEmpty()) {
			if(this.charge>=requested) {
				this.charge= this.charge-requested;
				supply=requested;
			}else if(this.charge<requested) {
				supply=this.charge;
				this.charge=0;
			}
		}
		isEmpty();
		return supply;
	}
	/**return the location of the energy*/ 
	public Point getLocation() {
		return this.location;
	}
	/**
	 * returns the distance between this energy node and another energy node
	 * @param Energy e
	 * */
	public double distance(Energy e) {
		return this.location.distance(e.getLocation());
	}


}