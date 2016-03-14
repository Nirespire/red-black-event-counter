package redBlackBST;

import static util.Color.BLACK;
import static util.Color.RED;

import util.Pair;

/**
 * @author Sanjay Nair
 * 
 * Implementation of a Red-Black Tree.
 *
 */
public class Tree {
	public Node root;

	public Tree() {
		this.root = null;
	}
	
	/**
	 * Constructs a Red-Black tree with the specified root,
	 * assuming that all other elements are properly balanced
	 * and colored.
	 * 
	 * @param root root of Red-Black Tree with all remaining Nodes properly colored and balanced
	 */
	public Tree(Node root){
		this.root = root;
	}

	/**
	 * Constructs a new node with key = value = val and inserts it into the Tree.
	 * 
	 * @param val will be used as both key and value for the new Node
	 */
	public void insert(int val) {
		insert(val, val);
	}

	/**
	 * Constructs a new node with given key and value and inserts it into the Tree.
	 * If the id exists, update the Node value.
	 * 
	 * @param id key
	 * @param value value
	 */
	public void insert(int id, int value) {
		//System.out.println("Insert " + id);
		Node current = root;
		Node parent = null;

		while (current != null) {
			if (current.getKey() == id) {
				current.setValue(value);
				return;
			} else if (current.getKey() < id) {
				parent = current;
				current = current.right();
			} else {
				parent = current;
				current = current.left();
			}
		}

		Node newNode = new Node(id, value, RED);

		if (parent != null) {
			if (parent.getKey() < newNode.getKey()) {
				parent.setRight(newNode);
			} else {
				parent.setLeft(newNode);
			}
			newNode.setParent(parent);
		} else {
			root = newNode;
		}

		fixRBInsert(newNode);

	}

	/**
	 * Checks and fixes integrity of Red-Black properties after insertion.
	 * 
	 * @param reference newly inserted Node
	 */
	private void fixRBInsert(Node reference) {
		// System.out.println(reference);

		// Check if root
		if (reference.parent() == null) {
			reference.setColor(BLACK);
			return;
		}
		// Parent is BLACK, no violation
		else if (reference.parent().getColor() == BLACK) {
			return;
		}
		// All violations when 2 consecutive RED nodes
		else if (reference.parent().getColor() == RED) {
			// XYr cases
			// RRr and RLr
			if (reference.grandparent() != null && reference.parent() == reference.grandparent().right()
					&& (reference.grandparent().left() != null && reference.grandparent().left().getColor() == RED)) {

				//System.out.println("RRr/RLr");

				reference.parent().setColor(BLACK);
				reference.grandparent().setColor(RED);
				reference.grandparent().left().setColor(BLACK);

				fixRBInsert(reference.grandparent());
				return;
			}

			// LRr and LLr
			else if (reference.grandparent() != null && reference.parent() == reference.grandparent().left()
					&& (reference.grandparent().right() != null && reference.grandparent().right().getColor() == RED)) {

				//System.out.println("LRr/LLr");

				reference.parent().setColor(BLACK);
				reference.grandparent().setColor(RED);
				reference.grandparent().right().setColor(BLACK);

				fixRBInsert(reference.grandparent());
				return;
			}

			// LLb case
			else if (reference.grandparent() != null && reference.parent() == reference.grandparent().left()
					&& reference == reference.parent().left() && (reference.grandparent().right() == null
							|| reference.grandparent().right().getColor() == BLACK)) {

				//System.out.println("LLb");

				leftRotate(reference.grandparent(), true);
				return;
			}
			// RRb case
			else if (reference.grandparent() != null && reference.parent() == reference.grandparent().right()
					&& reference == reference.parent().right()
					&& (reference.grandparent().left() == null || reference.grandparent().left().getColor() == BLACK)) {

				//System.out.println("RRb");

				rightRotate(reference.grandparent(), true);
				return;
			}
			// LRb case
			else if (reference.grandparent() != null && reference.parent() == reference.grandparent().left()
					&& reference == reference.parent().right() && (reference.grandparent().right() == null
							|| reference.grandparent().right().getColor() == BLACK)) {

				//System.out.println("LRb");

				leftRightRotate(reference.grandparent(), true);

				return;
			}
			// RLb case
			else if (reference.grandparent() != null && reference.parent() == reference.grandparent().right()
					&& reference == reference.parent().left()
					&& (reference.grandparent().left() == null || reference.grandparent().left().getColor() == BLACK)) {

				//System.out.println("RLb");

				rightLeftRotate(reference.grandparent(), true);

				return;
			}

		}
	}

	/**
	 * Retrieves the Node with key == id. Returns null if Node with
	 * the key does not exist in the Tree.
	 * 
	 * @param id key
	 * @return Node with key == id or null
	 */
	public Node getNode(int id) {

		Node current = root;

		while (current != null) {
			if (current.getKey() == id) {
				return current;
			} else if (current.getKey() < id) {
				current = current.right();
			} else {
				current = current.left();
			}
		}

		return null;
	}

	/**
	 * Returns the value of the Node with specified key.
	 * Returns 0 if the Node does not exist in the Tree.
	 * 
	 * @param id key
	 * @return value of the Node with key == id or 0
	 */
	public int getValue(int id) {
		Node n = getNode(id);
		return (n == null ? 0 : n.getValue());
	}

	/**
	 * Returns the Node with min ID greater than input.
	 * Returns null if no next exists.
	 * Provided id does not need to exist in the tree.
	 * 
	 * @param id key for which a Node with next higher key will be found. Does not need to exist in the Tree.
	 * @return Node with min ID greater than input
	 */
	public Node getNextNode(int id) {
		
		Node current = root;
		Node previous = null;
		boolean wentLeft = false;
		
		while(current != null){
			int val = current.getKey();
			
			// id exists in tree
			if(val == id){
				
				// Node has no right subtree, follow path up until a left pointer is
				// traversed
				// Return parent of that first left pointer
				if (current.right() == null) {
					Node next = current.parent();

					while (next != null && current != next.left()) {
						current = next;
						next = current.parent();
					}

					return next;
				}

				// Node has a right subtree, return min Node in that subtree
				return getMinNode(current.right());
			}
			else if(val < id){
				previous = current;
				current = current.right();
				wentLeft = false;
			}
			else{
				previous = current;
				current = current.left();
				wentLeft = true;
			}
		}
		
		
		// Else, id doesn't exist in tree
		// Current is null
		
		// Can't check if external node hanging off left or right by
		// ref comparison, so just check first
		if(wentLeft){
			return previous;
		}
		
		current = previous;
		Node next = previous.parent();
		
		while (next != null && current != next.left()) {
			current = next;
			next = current.parent();
		}

		return next;
	}
	
	/**
	 * Returns the Node with max ID lesser than input.
	 * Returns null if no next exists.
	 * Provided id does not need to exist in the tree.
	 * 
	 * @param id key for which a Node with next lower key will be found. Does not need to exist in the Tree
	 * @return Node with max ID lesser than input
	 */
	public Node getPreviousNode(int id){
		
		Node current = root;
		Node previous = null;
		boolean wentRight = false;
		
		while(current != null){
			int val = current.getKey();
			
			// id exists in tree
			if(val == id){
				
				// Node has no left subtree, follow path up until a left pointer is
				// traversed
				// Return parent of that first left pointer
				if (current.left() == null) {
					Node next = current.parent();

					while (next != null && current != next.right()) {
						current = next;
						next = current.parent();
					}

					return next;
				}

				// Node has a left subtree, return max Node in that subtree
				return getMaxNode(current.left());
			}
			else if(val < id){
				previous = current;
				current = current.right();
				wentRight = true;
			}
			else{
				previous = current;
				current = current.left();
				wentRight = false;
			}
		}
		
		
		// Else, id doesn't exist in tree
		// Current is null
		
		// Can't check if external node hanging off left or right by
		// ref comparison, so just check first
		if(wentRight){
			return previous;
		}
		
		current = previous;
		Node next = previous.parent();
		
		while (next != null && current != next.right()) {
			current = next;
			next = current.parent();
		}

		return next;
	}

	/**
	 * Deletes Node with key == id and rebalances.
	 * Does nothing if key does not exist in Tree.
	 * 
	 * @param id key of Node to be deleted
	 * @return delete success
	 */
	public boolean delete(int id) {

		Node toDelete = getNode(id);

		if (toDelete != null) {
			// Node is RED or root, no re-balancing needed
			if (toDelete.getColor() == RED || toDelete.parent() == null) {
				normalDelete(toDelete);
			}
			// Node is BLACK and not root (has parent), need to check which
			// Re-balance with respect to deficient subtree y
			// Provide py in case y is null
			else {
				Node y = null;
				Pair pyY = normalDelete(toDelete);
				
				if(pyY.isRightChild){
					y = pyY.node.right();
				}
				else{
					y = pyY.node.left();
				}

				fixRbDelete(y, pyY.node, pyY.isRightChild);

			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks and fixes integrity of Red-Black properties after deletion.
	 * 
	 * @param y root of deficient subtree
	 * @param py parent of y, in case y is null
	 * @param isRight if y was the right child of py
	 */
	private void fixRbDelete(Node y, Node py, boolean isRight) {

		// y is deficient subtree, could be null
		// py is parent of y
		
		// Moved deficiency to the root, done
		if (py == null) {
			//System.out.println("Set root black");
			y.setColor(BLACK);
			return;
		}
		
		// DEBUG
//		if (y != null) {
//			System.out.println("Y = " + y.getValue());
//		}
//		else{
//			System.out.println("Y = null");
//		}
//		System.out.println("py = " + py.getValue());

		Node v = null;

		if (isRight) {
			//System.out.println("y is right");
			v = py.left();
		} else {
			//System.out.println("y is left");
			v = py.right();
		}

		/*
		 * Xcn -> 
		 * 	X: y is R or L child of parent 
		 * 	c: y's sibling, v's color 
		 * 	n:number of v's RED children
		 */

		// Rb0 and Lb0
		if (v == null || (v.getColor() == BLACK && v.redDegree() == 0)) {
			//System.out.println("Lb0/Rb0");
			
			// case 1: py is BLACK
			if (py.getColor() == BLACK) {
				//System.out.println("case 1");
				v.flipColor();
				
				if(py.parent() != null){
					isRight = (py == py.parent().right());
				}
				fixRbDelete(py, py.parent(), isRight);
			}
			// case 2: py is RED
			else {
				//System.out.println("case 2");
				v.flipColor();
				py.flipColor();
				return;
			}
		}

		// Rb1
		else if (isRight && (v != null && (v.getColor() == BLACK && v.redDegree() == 1))) {
			//System.out.println("Rb1");

			// case 1: v's left child is RED
			if (v.left() != null && v.left().getColor() == RED) {
				//System.out.println("case 1");
				
				py.setColor(BLACK);
				v.left().setColor(BLACK);
				leftRotate(py, false);

				return;
			}
			// case 2: v's right child is RED
			else if (v.right() != null && v.right().getColor() == RED) {
				//System.out.println("case 2");
				
				py.setColor(BLACK);
				leftRightRotate(py, false);
				root.setColor(BLACK);

				return;
			}
		}

		// Lb1
		else if (!isRight && (v != null && (v.getColor() == BLACK && v.redDegree() == 1))) {
			//System.out.println("Lb1");

			// case 1: v's left child is RED
			if (v.left() != null && v.left().getColor() == RED) {
				//System.out.println("case 1");

				py.setColor(BLACK);
				rightLeftRotate(py, false);
				root.setColor(BLACK);

				return;
			}
			// case 2: v's right child is RED
			else if (v.right() != null && v.right().getColor() == RED) {
				//System.out.println("case 2");

				py.setColor(BLACK);
				v.right().setColor(BLACK);
				rightRotate(py, false);

				return;
			}
			
		}

		// Rb2
		else if (isRight && (v == null || (v.getColor() == BLACK && v.redDegree() == 2))) {
			//System.out.println("Rb2");

			leftRightRotate(py, false);
			
			root.setColor(BLACK);
			return;
		}
		
		// Lb2
		else if (!isRight && (v == null || (v.getColor() == BLACK && v.redDegree() == 2))) {
			//System.out.println("Lb2");
	
			rightLeftRotate(py, false);
			
			root.setColor(BLACK);
			return;
		}

		/*
		 * Rr(n) -> n: red degree of v's right child w
		 * Lr(n) -> n: red degree of v's left child w
		 */

		// Rr(0)
		else if (isRight && (v != null && v.getColor() == RED) && (v.right() != null && v.right().redDegree() == 0)) {
			//System.out.println("Rr0");
			
			v.setColor(BLACK);
			v.right().setColor(RED);
			
			leftRotate(py, false);
			return;
		}
		
		// Lr(0)
		else if (!isRight && (v != null && v.getColor() == RED) && (v.left() != null && v.left().redDegree() == 0)) {
			//System.out.println("Lr0");
			
			v.setColor(BLACK);
			v.left().setColor(RED);
			
			rightRotate(py, false);
			return;
		}

		// Rr(1)
		else if (isRight && (v != null && v.getColor() == RED) && v.right().redDegree() == 1) {
			//System.out.println("Rr1");

			// case 1: w's red child is left child
			if (v.right().left() != null && v.right().left().getColor() == RED) {
				//System.out.println("case 1");
				
				v.right().left().setColor(BLACK);
				
				leftRightRotate(py, false);
				return;
			}
			// case 2: w's red child is right child
			else if (v.right().right() != null && v.right().right().getColor() == RED) {
				//System.out.println("case 2");
				
				customRotateRight(py, v);
				
				return;
			}
		}
		
		// Lr(1)
		else if (!isRight && (v != null && v.getColor() == RED) && v.left().redDegree() == 1) {
			//System.out.println("Lr1");

			// case 1: w's red child is right child
			if (v.left().right() != null && v.left().right().getColor() == RED) {
				//System.out.println("case 1");
				
				v.left().right().setColor(BLACK);
				
				rightLeftRotate(py, false);
				return;
			}
			// case 2: w's red child is left child
			else if (v.left().left() != null && v.left().left().getColor() == RED) {
				//System.out.println("case 2");
				
				customRotateLeft(py, v);

				return;
			}
		}

		// Rr(2) -> (same as Rr(1) case 2
		else if (isRight && (v != null && v.getColor() == RED) && v.right().redDegree() == 2) {
			//System.out.println("Rr2");
			
			customRotateRight(py, v);
			
			return;
		}
		
		// Lr(2) -> (same as Lr(1) case 2
		else if (!isRight && (v != null && v.getColor() == RED) && v.left().redDegree() == 2) {
			//System.out.println("Lr2");
			
			customRotateLeft(py, v);
			
			return;
		}
	}

	/**
	 * Follows normal BST rules for deleting a node
	 * Returns the parent of the subtree from which the node was deleted
	 * and whether it was a right child.
	 * 
	 * @param n Node to be deleted
	 * @return Root of deficient subtree
	 */
	private Pair normalDelete(Node n) {
		//System.out.println("Delete " + n.getKey());
		
		int deg = n.degree();

		if (deg == 0) {
			if (n.parent() != null) {
				if (n.parent().left() == n) {
					n.parent().setLeft(null);
					return new Pair(n.parent(), false);
				} else {
					n.parent().setRight(null);
					return new Pair(n.parent(), true);
				}
			}
			// Node is root
			else {
				root = null;
				return new Pair(null, null);
			}
		} 
		else if (deg == 1) {
			if (n.parent() != null) {
				if (n.left() != null) {
					n.left().setParent(n.parent());
					
					if(n.parent().left() == n){
						n.parent().setLeft(n.left());
						return new Pair(n.parent(), false);
					}
					else{
						n.parent().setRight(n.left());
						return new Pair(n.parent(), true);
					}
				} else {
					n.right().setParent(n.parent());
					
					if(n.parent().left() == n){
						n.parent().setLeft(n.right());
						return new Pair(n.parent(), false);
					}
					else{
						n.parent().setRight(n.right());
						return new Pair(n.parent(), true);
					}
				}
			} 
			// Node is root
			else {
				if (n.left() != null) {
					n.left().setParent(n.parent());
					root = n.left();
				} else {
					n.right().setParent(n.parent());
					root = n.right();
				}
				root.setColor(BLACK);
				return new Pair(null, null);
			}
		} else if (deg == 2) {
			Node min = getMinNode(n.right());

			if (n.right() != min) {
				min.setRight(n.right());
			} else {
				min.setRight(null);
			}

			min.setLeft(n.left());

			if (n.parent() != null) {
				if (n.parent().left() == n) {
					n.parent().setLeft(min);
					return new Pair(n.parent(), false);
				} else {
					n.parent().setRight(min);
					return new Pair(n.parent(), true);
				}
			} else {
				root = min;
				root.setColor(BLACK);
				return new Pair(null, null);
			}
		}
		return new Pair(null, null);
	}

	/**
	 * Return node with min key in subtree rooted at input node.
	 * 
	 * @param root root Node of subtree to be searched
	 * @return Node with the min key
	 */
	private Node getMinNode(Node root) {
		Node current = root;
		Node parent = null;

		while (current != null) {
			parent = current;
			current = current.left();
		}

		return parent;
	}
	
	/**
	 * Return node with max key in subtree rooted at input node.
	 * 
	 * @param root root Node of subtree to be searched
	 * @return Node with the max key
	 */
	private Node getMaxNode(Node root){
		Node current = root;
		Node parent = null;

		while (current != null) {
			parent = current;
			current = current.right();
		}

		return parent;
	}

	/**
	 * Increases the value at the node with provided key. If the node does
	 * not exist, it will be inserted with value m.
	 * 
	 * @param theID key of the node to increase
	 * @param m amount by which that node's value should be increased
	 * @return resulting node value
	 */
	public int increase(int theID, int m) {
		Node result = getNode(theID);

		if (result != null) {
			return result.increaseValue(m);
		} else {
			insert(theID, m);
			return m;
		}
	}

	/**
	 * Decreases the value at the node with provided key. If the value
	 * goes below 0, the node will be deleted. Returns 0 if the node does
	 * not exist or is deleted.
	 * 
	 * @param theID key of the node to decrease
	 * @param m amount by which that node's value should be decreased
	 * @return resulting node value. Will return 0 if deleted
	 */
	public int decrease(int theID, int m) {
		Node result = getNode(theID);

		if (result != null) {
			int newVal = result.decreaseValue(m);
			
			if (newVal > 0) {
				
				return newVal;
			} else {
				delete(theID);
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Basic LL rotation about z.
	 * Flip specifies if RB color flipping is necessary, as with inserts.
	 * 
	 * @param z central Node of rotation
	 * @param flip whether to flip colors as with inserts
	 * @return root Node with respect to the rotation
	 */
	public Node leftRotate(Node z, boolean flip) {
		//System.out.println("L");

		Node y = z.left();
		z.setLeft(y.right());

		if (y.right() != null) {
			y.right().setParent(z);
		}

		y.setParent(z.parent());

		if (z.parent() == null) {
			root = y;
		} else {
			if (z == z.parent().right()) {
				z.parent().setRight(y);
			} else {
				z.parent().setLeft(y);
			}
		}

		y.setRight(z);
		z.setParent(y);

		if(flip){
			y.flipColor();
			z.flipColor();
		}

		return y;

	}

	/**
	 * Basic RR rotation about z.
	 * Flip specifies if RB color flipping is necessary, as with inserts.
	 * 
	 * @param z central Node of rotation
	 * @param flip whether to flip colors as with inserts
	 * @return root Node with respect to the rotation
	 */
	public Node rightRotate(Node z, boolean flip) {
		//System.out.println("R");

		Node y = z.right();

		// Turn y's left sub-tree into x's right sub-tree
		z.setRight(y.left());
		if (y.left() != null) {
			y.left().setParent(z);
		}

		// y's new parent was x's parent
		y.setParent(z.parent());

		// Set the parent to point to y instead of x
		// First see whether at root
		if (z.parent() == null) {
			root = y;
		} else {
			if (z == z.parent().left()) {
				// x was on the left of its parent
				z.parent().setLeft(y);
			} else {
				// else on the right
				z.parent().setRight(y);
			}
		}

		// x on y's left
		y.setLeft(z);
		z.setParent(y);

		if(flip){
			y.flipColor();
			z.flipColor();
		}

		return y;
	}

	/**
	 * Basic LR rotation about z.
	 * Flip specifies if RB color flipping is necessary, as with inserts.
	 * 
	 * @param z central Node of rotation
	 * @param flip whether to flip colors as with inserts
	 */
	public void leftRightRotate(Node z, boolean flip) {
		//System.out.println("LR");
		Node x = z.left();
		Node y = x.right();

		if (z.parent() != null) {
			if (z.parent().left() == z) {
				z.parent().setLeft(y);
			} else {
				z.parent().setRight(y);
			}
		} else {
			root = y;
		}

		y.setParent(z.parent());

		z.setLeft(y.right());
		if (y.right() != null) {
			y.right().setParent(z);
		}

		x.setRight(y.left());
		if (y.left() != null) {
			y.left().setParent(x);
		}

		z.setParent(y);
		y.setRight(z);

		y.setLeft(x);
		x.setParent(y);
		
		if(flip){
			y.flipColor();
			z.flipColor();
		}

	}

	/**
	 * Basic RL rotation about z.
	 * Flip specifies if RB color flipping is necessary, as with inserts.
	 * 
	 * @param z central Node of rotation
	 * @param flip whether to flip colors as with inserts
	 */
	public void rightLeftRotate(Node z, boolean flip) {
		//System.out.println("RL");
		Node x = z.right();
		Node y = x.left();

		if (z.parent() != null) {
			if (z.parent().left() == z) {
				z.parent().setLeft(y);
			} else {
				z.parent().setRight(y);
			}
		} else {
			root = y;
		}

		y.setParent(z.parent());

		z.setRight(y.left());
		if (y.left() != null) {
			y.left().setParent(z);
		}

		x.setLeft(y.right());
		if (y.right() != null) {
			y.right().setParent(x);
		}

		z.setParent(y);
		y.setLeft(z);

		y.setRight(x);
		x.setParent(y);
		
		if(flip){
			y.flipColor();
			z.flipColor();
		}
	}
	
	/**
	 * Used for custom rotations necessary for Rr(1) case 2 and RR(2) delete re-balances.
	 * 
	 * @param py parent of deficient subtree y
	 * @param v sibling of y
	 */
	public void customRotateRight(Node py, Node v){
		Node x = v.right().right();
		x.setColor(BLACK);
		
		v.right().setRight(x.left());
		if(v.right().right() != null){
			v.right().right().setParent(v.right());
		}
		
		py.setLeft(x.right());
		if(py.left() != null){
			py.left().setParent(py);
		}
		
		x.setLeft(v);
		v.setParent(x);
		
		x.setParent(py.parent());
		
		if(py.parent() != null){
			if(py.parent().right() == py){
				py.parent().setRight(x);
			}
			else{
				py.parent().setLeft(x);
			}
			
		}
		else{
			root = x;
		}
		
		x.setRight(py);
		py.setParent(x);
	}
	
	/**
	 * Used for custom rotations necessary for Lr(1) case 2 and Lr(2) delete re-balances.
	 * 
	 * @param py parent of deficient subtree y
	 * @param v sibling of y
	 */
	public void customRotateLeft(Node py, Node v){
		Node x = v.left().left();
		x.setColor(BLACK);
		
		v.left().setLeft(x.right());
		if(v.left().left() != null){
			v.left().left().setParent(v.left());
		}
		
		py.setRight(x.left());
		if(py.right() != null){
			py.right().setParent(py);
		}
		
		x.setRight(v);
		v.setParent(x);
		
		x.setParent(py.parent());
		
		if(py.parent() != null){
			if(py.parent().right() == py){
				py.parent().setRight(x);
			}
			else{
				py.parent().setLeft(x);
			}
			
		}
		else{
			root = x;
		}
		
		x.setLeft(py);
		py.setParent(x);
	}
	
	/**
	 * Returns the sum of values contained in keys in the range [id1, id2]
	 * where id1 &le; id2.
	 * 
	 * @param id1 lower bound for key, inclusive. id1 &le; id2.
	 * @param id2 upper bound for key, inclusive. id2 &ge; id1.
	 * @return sum of values contained in range
	 */
	public int inRange(int id1, int id2){
		if(id2 < id1){
			return 0;
		}
		
		return inRangeHelper(root, id1, id2);
		
	}
	
	/**
	 * Recursive helper function for inRange.
	 * 
	 * @param root root of subtree being recursively traversed
	 * @param id1 lower bound for key, inclusive. id1 &le; id2.
	 * @param id2 upper bound for key, inclusive. id2 &ge; id1.
	 * @return recursive sum of values in range of keys [id1, id2]
	 * @see Tree#inRange(int, int)
	 */
	private int inRangeHelper(Node root, int id1, int id2){
		if(root == null){
			return 0;
		}
		
		int currentKey = root.getKey();
		
		int total = 0;
		
		if(id1 < currentKey){
			total += inRangeHelper(root.left(), id1, id2);
		}
		
		if(id1 <= currentKey && id2 >= currentKey){
			total += root.getValue();
		}
		
		if(id2 > currentKey){
			total += inRangeHelper(root.right(), id1, id2);
		}
		
		return total;
		
		
	}

}
