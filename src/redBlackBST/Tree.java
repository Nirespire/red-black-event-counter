package redBlackBST;

import static util.Color.*;

public class Tree {
	public Node root;

	public Tree() {
		this.root = null;
	}

	public void insert(int id, int value) {
		Node current = root;
		Node parent = null;

		while (current != null) {
			if (current.getKey() == id) {
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

		fixRB(newNode);

	}

	// TODO
	/**
	 * Checks and fixes integrity of Red-Black properties
	 * 
	 * @param reference
	 */
	private void fixRB(Node reference) {

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
			// RYr case
			// RRr and RLr
			if (reference.grandparent() != null && reference.grandparent().left() != null
					&& reference.grandparent().left().getColor() == RED) {
				reference.parent().setColor(BLACK);
				reference.grandparent().setColor(RED);

				if (reference.grandparent().left().getColor() == RED) {
					reference.grandparent().left().setColor(BLACK);
				}

				fixRB(reference.grandparent());
			}
			// LYr case
			// LRr and LLr
			else if (reference.grandparent() != null && reference.grandparent().right() != null
					&& reference.grandparent().right().getColor() == RED) {

				reference.parent().setColor(BLACK);
				reference.grandparent().setColor(RED);

				if (reference.grandparent().right().getColor() == RED) {
					reference.grandparent().left().setColor(BLACK);
				}

				fixRB(reference.grandparent());
			}
			// LLb case
			else if (reference.grandparent() != null
					&& reference == reference.parent().left()
					&& reference.parent() == reference.grandparent().left()
					&& (reference.grandparent().right() == null || reference.grandparent().right().getColor() == BLACK)) {
				
				leftRotate(reference.grandparent());
			}
			// RRb case
			else if (reference.grandparent() != null
					&& reference == reference.parent().right()
					&& reference.parent() == reference.grandparent().right()
					&& (reference.grandparent().left() == null || reference.grandparent().left().getColor() == BLACK)) {
				
				rightRotate(reference.grandparent());
			}
			// LRb case
			else if (reference.grandparent() != null
					&& reference == reference.parent().right()
					&& reference.parent() == reference.grandparent().left()
					&& (reference.grandparent().right() == null || reference.grandparent().right().getColor() == BLACK)) {
				
				leftRightRotate(reference.grandparent());
			}
			// RLb case
			else if (reference.grandparent() != null
					&& reference == reference.parent().left()
					&& reference.parent() == reference.grandparent().right()
					&& (reference.grandparent().right() == null || reference.grandparent().right().getColor() == BLACK)) {
				
				rightLeftRotate(reference.grandparent());
			}

		}

	}

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

	public int getValue(int id) {
		Node n = getNode(id);
		return (n == null ? 0 : n.getValue());
	}

	// TODO
	public void delete(int id) {

		Node toDelete = getNode(id);

		if (toDelete != null) {
			if (toDelete.getColor() == RED) {
				normalDelete(toDelete);
			}
		}
	}

	/**
	 * Follows normal BST rules for deleting a node
	 * 
	 * @param n
	 */
	private void normalDelete(Node n) {
		int deg = n.degree();
		if (deg == 0) {
			if (n.parent() != null) {
				if (n.left() == n)
					n.setLeft(null);
				else
					n.setRight(null);
			}
		} else if (deg == 1) {
			if (n.left() != null) {
				n.left().setParent(n.parent());
				n.parent().setLeft(n.left());
			} else {
				n.right().setParent(n.parent());
				n.parent().setRight(n.right());
			}
		} else if (deg == 2) {
			Node min = getMinNode(n.right());
			min.setRight(n.right());
			min.setLeft(n.left());

			if (n.parent().left() == n) {
				n.parent().setLeft(min);
			} else {
				n.parent().setRight(min);
			}
		}
	}

	/**
	 * Return node with min key in subtree rooted at input node
	 * 
	 * @param root
	 * @return
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

	public int increase(int theID, int m) {
		Node result = getNode(theID);

		if (result != null) {
			return getNode(theID).increaseValue(m);
		} else {
			insert(theID, m);
			return m;
		}
	}

	public int decrease(int theID, int m) {
		Node result = getNode(theID);

		if (result != null) {
			if (result.decreaseValue(m) > 0) {
				return result.getValue();
			} else {
				delete(theID);
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 		z 	 	 y 
	 * 	   / 		/ \ 
	 *    y    ->  x   z 
	 *   / 
	 *  x
	 */
	public void leftRotate(Node z) {
		Node y = z.left();

		if (z.parent() != null) {
			if (z.parent().left() == z) {
				z.parent().setLeft(z.left());
			} else {
				z.parent().setRight(z.left());
			}
		} else {
			root = y;
		}

		y.setParent(z.parent());
		z.setParent(y);

		z.setLeft(y.right());
		y.setRight(z);

		y.flipColor();
		z.flipColor();

	}

	public void rightRotate(Node z) {
		Node y = z.right();

		if (z.parent() != null) {
			if (z.parent().left() == z) {
				z.parent().setLeft(z.left());
			} else {
				z.parent().setRight(z.left());
			}
		} else {
			root = y;
		}

		y.setParent(z.parent());
		z.setParent(y);

		z.setRight(y.left());
		y.setLeft(z);

		y.flipColor();
		z.flipColor();
	}
	
	public void leftRightRotate(Node z){
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
		if(y.right() != null){
			y.right().setParent(z);
		}
		
		x.setRight(y.left());
		if(y.left() != null){
			y.left().setParent(x);
		}
		
		z.setParent(y);
		y.setRight(z);
		
		y.setLeft(x);
		x.setParent(y);
		
		y.flipColor();
		z.flipColor();
		
	}
	
	public void rightLeftRotate(Node z){
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
		if(y.left() != null){
			y.left().setParent(z);
		}
		
		x.setLeft(y.right());
		if(y.right() != null){
			y.right().setParent(x);
		}
		
		z.setParent(y);
		y.setLeft(z);
		
		y.setRight(x);
		x.setParent(y);
		
		y.flipColor();
		z.flipColor();
	}


	
}
