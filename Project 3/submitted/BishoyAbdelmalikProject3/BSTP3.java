/**
 * @author Bishoy Abdelmalik
 * @since  5/8/2019
 * @version 1.0
 * Binary Search Tree application class runs the simulations
 */
import java.util.ArrayList;
import java.util.Collections;
public class BSTP3 {
	private int power;
	private ArrayList<Integer> key;
	private final int TRIALS=1000;
	BSTree<BSTElement> tree;
	private Sample createMax, createAverage, deleteMax, deleteAverage, reinsertMax, reinsertAverage;
	BSTP3(int power){
		this.power=power;
		this.createMax=new Sample("createMax");
		this.createAverage=new Sample("createAverage");
		this.deleteMax=new Sample("deleteMax"); 
		this.deleteAverage=new Sample("deleteAverage");
		this.reinsertMax=new Sample("reinsertMax"); 
		this.reinsertAverage=new Sample("reinsertAverage"); 
		createArraylist(this.power);

		
		for (int i=0;i<TRIALS;i++) {
			doTrial();	  
		}
		viewResults();
		
		

	}
	/**
	 * print out results
	 */
	private void viewResults() {
		System.out.println(this.createMax.toString());
		System.out.println(this.createAverage.toString());
		System.out.println(this.deleteMax.toString());
		System.out.println(this.deleteAverage.toString());
		System.out.println(this.reinsertMax.toString());
		System.out.println(this.reinsertAverage.toString());

	}
	/**
	 * perform one trial run
	 */
	private void doTrial() {
		/*create the samples to hold temp data */
		Sample createTrial=new Sample("createTrial");
		Sample deleteTrial=new Sample("deleteTrial");
		Sample reinsertTrial=new Sample("reinsertTrial");
		/*create a new empty BST*/
		this.tree=new BSTree<BSTElement>();
		/*shuffle the array*/
	    Collections.shuffle(this.key);
	    /*insert the keys*/
	    for(int x:key) {
			this.tree.insert(new BSTElement(x));
		}
	    /*verify the BST*/
	    if(this.tree.verify()) {
	    	this.tree.nodeLevel(createTrial);
	    	//System.out.println("size: "+tree.getSize()); used this to verify deletion its bilt into verify itself at the moment

	    }else {
	    	System.out.println("Error Occured");
	    	System.exit(0);
	    }
	    /*delete half the keys*/
	    for(int x=0;x<key.size()/2;x++) {
			this.tree.delete(new BSTElement(key.get(x)));
	    }
	    /*verify the BST*/
	    if(this.tree.verify()) {
	    	this.tree.nodeLevel(deleteTrial);
	    	//System.out.println("size: "+this.tree.getSize());

	    }else {
	    	System.out.println("Error Occured");
	    	System.exit(0);

	    }
	    /*insert the keys that was deleted*/
	    for(int x=0;x<key.size()/2;x++) {
			this.tree.insert(new BSTElement(x));
	    }
	    /*verify the BST*/
	    if(this.tree.verify()) {
	    	this.tree.nodeLevel(reinsertTrial);
	    }else {
	    	System.out.println("Error Occured");
	    	System.exit(0);

	    }
	    /*
	     * add the info from the temp samples to the primary ones
	     */
	    this.createMax.add(createTrial.getMax());
		this.createAverage.add(createTrial.getAverage());
		this.deleteMax.add(deleteTrial.getMax()); 
		this.deleteAverage.add(deleteTrial.getAverage());
		this.reinsertMax.add(reinsertTrial.getMax());
		this.reinsertAverage.add(reinsertTrial.getAverage()); 
		
	}
	/**
	 * create the arrayList key that will be used to populate the BST
	 * @param power
	 */
	private void createArraylist(int power) {
	    int n = (int) Math.pow(2, power) - 1;
	    this.key = new ArrayList<Integer>();
	    for(int i = 0; i < n; i++) { key.add(i);}
	 }
	public static void main(String[] args) {
		new BSTP3(10);

	}
}
