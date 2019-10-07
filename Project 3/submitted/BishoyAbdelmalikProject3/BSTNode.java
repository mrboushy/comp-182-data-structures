/**
 * @author Bishoy Abdelmalik
 * @since  5/8/2019
 * @version 1.0
 * BST node class has the left and right pointers and and setters and getters methods 
 */
public class  BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>> {
	private E element;
	 private BSTNode <E> left;
	 private  BSTNode <E> right;
	public BSTNode(E element) {
		this.element  = element;
		this.left = null;
		this.right = null;
	 }
	public void setLeft(E element) {
		 setLeft(new BSTNode<>(element));
	}
	public void setLeft(BSTNode<E> node ) {
		this.left=node;
	}
	 public void setRight(E element) { 
		 setRight(new BSTNode<>(element)); 
	}
	 public void setRight(BSTNode<E> node) { 
		 this.right=node; 
	}
	    
	public void set(E element) {this.element=element;}     
	public E get() { return this.element; }
	
	@Override
	public int compareTo( BSTNode<E> other) {
	      return  this.element.compareTo( other.get()) ; 

	}
	
	public void removeLeft() {
		this.left=null;
	}
	public void removeRight() {
		this.right=null;
	}
	public BSTNode<E> getLeft() {
		return this.left;
	}
	public BSTNode<E> getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}
	
	@Override
	public String toString() {
		return this.get().toString();
	}

	

	
	   
	

}
