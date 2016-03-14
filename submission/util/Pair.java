package util;

import redBlackBST.Node;

/**
 * Pair
 * 
 * Utility class used in Red-Black Tree deletion.
 * 
 * node field is the root of the subtree from which a delete occurred.
 * isRightChild boolean exists in the case that node is null and it externally
 * impossible to deduce whether the deleted subtree is the left or right
 * child of its parent.
 */
public class Pair {
	public Node node;
	public Boolean isRightChild;
	
	public Pair(Node a, Boolean b){
		this.node = a;
		this.isRightChild = b;
	}
}
