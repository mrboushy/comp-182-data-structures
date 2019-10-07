/**
 * @author Bishoy Abdelmalik
 * @since  5/8/2019
 * @version 1.0
 * Binary Search Tree main class has the root and size instance variables and controls inserts and deletions of nodes
 */
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BSTree <E extends Comparable<E>>  {

	private int size;
	private BSTNode <E>  root;
	public BSTree() {
		this.size = 0;
		this.root = null;
	}
	/**
	 * Insert Node in the BST
	 * @param newNode
	 * @param parent
	 * @return BSTNode<E> 
	 */
	public void insert(E element) {
		if(this.root!=null) {
			insert(new BSTNode<E>(element),this.root);
		}else {
			this.root=new BSTNode<E>(element);
		}
		this.size++;
	}
	/**
	 * Insert Node in the BST
	 * @param newNode
	 * @param parent
	 * @return BSTNode<E> 
	 */
	public BSTNode<E> insert(BSTNode<E> newNode, BSTNode <E>  parent) {
		if(parent==null) {
			return newNode;
		}else if(parent.compareTo(newNode) > 0) {
			parent.setLeft(insert(newNode,parent.getLeft()));	
		}else if (parent.compareTo(newNode)<0){
			parent.setRight(insert(newNode,parent.getRight()));
		}else {
		   //silent is golden 
		   //if its a duplicate don't insert it (do nothing)
		   this.size--; // this fixes the tree size if there is a duplicate so it doesnt increase 
		}
		
		return parent;
		   
	}

	/**
	 * Delete a node from the BST 
	 * using stack to store parents and recursive methods to find the node and its replacement
	 * @param node
	 */
	private void delete(BSTNode<E> node) {
		Deque<BSTNode<E>> parentsOfNodeToBeDeleted=new LinkedList<>();;

		BSTNode<E> tempNode=find(node,parentsOfNodeToBeDeleted);
		if(tempNode==null) {//if node doesnt exist do nothing
			//System.out.println(node.get()+"doesn't exist");
			return;
		}
		boolean isRoot =(tempNode.compareTo(this.root)==0)?true:false;
		// get node parent if it has one
		BSTNode<E> parentOfNodeToBeDeleted=(!parentsOfNodeToBeDeleted.isEmpty())?parentsOfNodeToBeDeleted.pop():null;

		if(tempNode.getLeft()==null&&tempNode.getRight()==null) {// if its a leaf 
			if (isRoot) {
				this.root=null;
				return;
			}
			// if its a leaf that has a parent 
			if(parentOfNodeToBeDeleted.getLeft()!=null&&tempNode.compareTo(parentOfNodeToBeDeleted.getLeft())==0) {// check if the node to be deleted is the left or right child of the parent
				parentOfNodeToBeDeleted.removeLeft();
			}else {
				parentOfNodeToBeDeleted.removeRight();

			}
			return;
		}
		Deque<BSTNode<E>> parentsOfReplacmentNode=new LinkedList<>();
		BSTNode<E> replacmentNode=findReplacmentR(tempNode.getRight(),parentsOfReplacmentNode);
		BSTNode<E> parentOfReplacmentNode=(!parentsOfReplacmentNode.isEmpty())?parentsOfReplacmentNode.pop():null;

		if(isRoot) {
			if(replacmentNode==null) {//if there is no right tree
				parentsOfReplacmentNode.clear();
				replacmentNode=findReplacmentL(tempNode.getLeft(),parentsOfReplacmentNode);
				parentOfReplacmentNode=(!parentsOfReplacmentNode.isEmpty())?parentsOfReplacmentNode.pop():null;
				if(parentOfReplacmentNode!=null) {// handle the case where we are grabbing the next value 
					parentOfReplacmentNode.setRight(replacmentNode.getLeft());
					replacmentNode.setLeft(this.root.getLeft());
					replacmentNode.setRight(this.root.getRight());
				}else {//handle the case where there is no parent to account for and no shifting elements 
					replacmentNode.setRight(this.root.getRight());
				}
				this.root=replacmentNode;
				return;
			}
			
			
			if(parentOfReplacmentNode!=null) {// handle the case where we are grabbing the next value 
				parentOfReplacmentNode.setLeft(replacmentNode.getRight());
				replacmentNode.setLeft(this.root.getLeft());
				replacmentNode.setRight(this.root.getRight());
			}else {//handle the case where there is no parent to account for and no shifting elements 
				replacmentNode.setLeft(this.root.getLeft());
			}
			this.root=replacmentNode;
		}else {
			if(replacmentNode==null) {
				if(parentOfNodeToBeDeleted.getLeft()!=null&&tempNode.compareTo(parentOfNodeToBeDeleted.getLeft())==0) {
					if(tempNode.getLeft()==null) {
						parentOfNodeToBeDeleted.setLeft(tempNode.getRight());
					}else {
						parentOfNodeToBeDeleted.setLeft(tempNode.getLeft());
					}
				}else {
					if(tempNode.getRight()==null) {
						parentOfNodeToBeDeleted.setRight(tempNode.getLeft());

					}else {
						parentOfNodeToBeDeleted.setRight(tempNode.getRight());
					}
				}
				return;
			}
			
			if(parentOfReplacmentNode!=null) {
				parentOfReplacmentNode.setLeft(replacmentNode.getRight());
				replacmentNode.setRight(tempNode.getRight());

			} 
			replacmentNode.setLeft(tempNode.getLeft());
			
			if(parentOfNodeToBeDeleted.compareTo(tempNode)>0) {
				parentOfNodeToBeDeleted.setLeft(replacmentNode);
				
			}else {
				parentOfNodeToBeDeleted.setRight(replacmentNode);

			}
		}
		
		
	}

	/**
	 * Delete a node from the BST
	 * @param element
	 */
	public void delete(E element) {
		if(this.root==null) {
			return;
		}
		delete(new BSTNode<E>(element));
		this.size--;
		
	}
	/**
	 * Find a node in the BST without saving its parents 
	 * @param element
	 * @return BSTNode<E>
	 */
	public  BSTNode<E> find(E element) {
		 return find(new BSTNode<E>(element));
	}
	private BSTNode<E> find(BSTNode<E> node){
		if(this.root.compareTo(node)==0) {
			return this.root;
		}
		return find(node,this.root);
	}
	/**
	 * Find a node in the BST without saving its parents 
	 * @param node
	 * @param root
	 * @return BSTNode<E>
	 */
	private BSTNode<E> find(BSTNode<E> node,BSTNode<E> root){
		if(root==null) {return root;} 
		if (root.compareTo(node)==0) {return root;}
		if(root.compareTo(node)> 0) {
			return find(node,root.getLeft());
		}else {
			return find(node,root.getRight());
		}
	}
	/**
	 * Find a node in the BST
	 * @param node:BSTNode<E> 
	 * @param nodes:Deque<BSTNode<E>>
	 * @return BSTNode<E> 
	 */
	private BSTNode<E> find(BSTNode<E> node,Deque<BSTNode<E>> nodes){
		if(this.root.compareTo(node)==0) {
			return this.root;
		}
		return find(node,this.root,nodes);
	}
	/**
	 * Find a node in the BST and adds its parents to the nodes stack
	 * @param node:BSTNode<E> 
	 * @param nodes:Deque<BSTNode<E>>
	 * @return BSTNode<E> 
	 */
    private BSTNode<E> find(BSTNode<E> node, BSTNode<E> root, Deque<BSTNode<E>> nodes) {
    	nodes.addFirst(root);
    	if(root==null) {
    		nodes.clear();
    		return root;
    	} 
    	
		if (root.compareTo(node)==0) {
			nodes.pop();
			return root;
		}
		if(root.compareTo(node)> 0) {
			return find(node,root.getLeft(),nodes);
		}else {
			return find(node,root.getRight(),nodes);
		}
	}


    /**
     * find replacement node to be used in deletion the BSTNode<E> node passed to it should be right child of the root
     * findReplacmentR is designed to work with the right child of the root
     * findReplacmentL is designed to work with the left child of the root
     * @param node:BSTNode<E> 
     * @param nodes:Deque<BSTNode<E>>
     * @return BSTNode<E>
     */
    private BSTNode<E> findReplacmentR(BSTNode<E> node,Deque<BSTNode<E>> nodes){
		if(node==null) {
			return node;
		}
		nodes.addFirst(node);

		if(node.getLeft()==null) {
			nodes.pop();

			return node;
		}
		return findReplacmentR(node.getLeft(),nodes);
	}
    /**
     * find replacement node to be used in deletion and add its parents to the nodes stack
     * The BSTNode<E> node passed to it should be left child of the root
     * findReplacmentR is designed to work with the right child of the root
     * findReplacmentL is designed to work with the left child of the root
     * @param node:BSTNode<E> 
     * @param nodes:Deque<BSTNode<E>>
     * @return BSTNode<E>
     */
	private BSTNode<E> findReplacmentL(BSTNode<E> node,Deque<BSTNode<E>> nodes){
		if(node==null) {
			return node;
		}
		nodes.addFirst(node);

		if(node.getRight()==null) {
			nodes.pop();

			return node;
		}
		return findReplacmentL(node.getRight(),nodes);
	}
	
	/**
	 * verify the the number of elements with the the size variable make sure they are equal
	 * @return boolean
	 */
	private boolean verifySize() {
		int i=count();
		if(this.size==i) {
			return true;
		}
		return false;
		
	}
	/**
	 * go through the tree save each element in an ArrayList then return the arraylist size
	 * @return int
	 */
	public int count() {
		ArrayList<BSTNode<E>> a=new ArrayList<>();
		count( this.root,a);
		 return a.size();	 
	}
	/**
	 * in order traversal to save the BST elements into the ArrayList to count them
	 * @param root:BSTNode<E> 
	 * @param a:ArrayList<BSTNode<E>>
	 */
	private void count(BSTNode<E> root,ArrayList<BSTNode<E>> a) {
		if(root==null) {
			   return;
		 }
		count(root.getLeft(),a);
		a.add(root);
		count(root.getRight(),a);
	}
	/**
	 * verify if the tree is valid or not
	 * by comparing the root with the left and right childs
	 * @param root
	 * @return boolean
	 */
	private  boolean verify(BSTNode <E> root) {
		if(root==null) {
			   return true;
		 }
		if((root.getLeft()==null||root.compareTo(root.getLeft())>0)&&(root.getRight()==null||root.compareTo(root.getRight())<0)) {
			 verify(root.getLeft());
			 verify(root.getRight());
			 return true;
		}else {
			return false;
		}
	
	}
	/**
	 * verify if the tree is valid or not
	 * @return boolean
	 */
	public boolean verify() {
		 try { 
			 boolean v=verify(this.root);
			 if(!verifySize()) {
					v=false;
			 }
			 assert v;
			 return v;
			 }  // test assertion
		 catch (AssertionError e) { 
			System.out.println("assertion verify failed: " + e); 
			return false;
		 }
	}
	/**
	 * Save the level of all the nodes to the sample 
	 * @param s:Sample
	 */
	public void nodeLevel(Sample s) {
		if(this.root==null) {return;}
		nodeLevel(this.root,s,1);
	}
	/**
	 * Save the level of all the nodes to the sample 
	 * @param s:Sample
	 */
	private void nodeLevel(BSTNode<E> root,Sample s,int level) {
		if(root==null) {
			return;
		}			
		s.add(level);
		level++;
		nodeLevel(root.getLeft(),s,level);
		nodeLevel(root.getRight(),s,level);

	}
	/**
	 * returns the BST size ie number of elements 
	 * @return int
	 */
	public int getSize() {return this.size;}

	
	/*
	 * the traversal methods were used during development to help me visualize the tree 
	 * Can be used to print out all the elements in the tree
	 */
	public void inOrderTraversal() {inOrderTraversal(this.root);}
	public void preOrderTraversal() {preOrderTraversal(this.root);}
	public void postOrderTraversal() {postOrderTraversal(this.root);}

	private void inOrderTraversal( BSTNode <E> root) {
	   if(root==null) {
		   return;
	   }
	   inOrderTraversal(root.getLeft());
	   System.out.println(root.toString());
	   inOrderTraversal(root.getRight());
	  
	}
	
	private void preOrderTraversal( BSTNode <E> root) {
	   if(root==null) {
		   return;
	   }
	   System.out.println(root.toString());
	   preOrderTraversal(root.getLeft());
	   preOrderTraversal(root.getRight());
		   
	}
	private void postOrderTraversal( BSTNode <E> root) {
	   if(root==null) {
		   return;
	   }
	   postOrderTraversal(root.getLeft());
	   postOrderTraversal(root.getRight());
	   System.out.println(root.toString());	   
	}

	
}
