package util;

import redBlackBST.Node;

/**
 * Utility class used in Red-Black Tree deletion.
 * 
 * Node field is the root of the subtree from which a delete occurred.
 * IsRightChild boolean exists in the case that node is null and it externally
 * impossible to deduce whether the deleted subtree is the left or right
 * child of its parent.
 * 
 * @author Sanjay Nair
 */
public class Pair {
	public Node node;
	public Boolean isRightChild;
	
	public Pair(Node a, Boolean b){
		this.node = a;
		this.isRightChild = b;
	}
}
