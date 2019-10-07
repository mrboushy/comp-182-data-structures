import java.awt.Point;

public class Energy {
	private double charge;
	private Point location;
	private boolean empty;
	Energy(Point p, double charge){
		this.location=p;
		this.charge=charge;
		isEmpty();
		
	}
	public boolean isEmpty() {
		if(this.charge<=0) {
			this.empty= true;
			return this.empty;
		}
		this.empty=false ;
		return this.empty;	
	}
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
	//return the location of the energy 
	public Point getLocation() {
		return this.location;
	}
	public double distance(Energy e) {
		return this.location.distance(e.getLocation());
	}


}