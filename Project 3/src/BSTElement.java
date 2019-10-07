/**
 * @author Bishoy Abdelmalik
 * @since  5/8/2019
 * @version 1.0
 * BST element class is a wrapper class that houses a Integer key  
 */
public class BSTElement implements Comparable<BSTElement> {
	 private Integer key;
	   
	   public BSTElement(int value) {
	      this.key = new Integer(value);
	   }
	      
	   @Override
	public String toString() { return Integer.toString(this.key); }
	      
	   // Shows the result of the comparison in trace print   
	   @Override
	public int compareTo(BSTElement other) {
	      int result = this.key.compareTo(other.key); // Integer imlements compareTo
	      if (result < 0) { 
	         //System.out.print(key + "<" + other.key + " ");  
	         return -1; }
	      else if (result == 0) {
	         //System.out.print(key + "==" + other.key + " "); 
	         return 0; }
	      else { // (result > 0) 
	         //System.out.print(key + ">" +  other.key + " "); 
	         return 1; }
	   }

	

}
