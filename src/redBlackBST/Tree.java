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

		//fixRB(newNode);

	}

	// TODO
	/**
	 * Checks and fixes integrity of Red-Black properties
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
			// TODO 
			// LLb case
			
			// TODO
			// RRb case
			
			// TODO
			// LRb case
			
			// TODO
			// RLb case
			
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
		
		if(toDelete != null){
			if(toDelete.getColor() == RED){
				normalDelete(toDelete);
			}
		}
	}
	
	/**
	 * Follows normal BST rules for deleting a node
	 * @param n
	 */
	private void normalDelete(Node n){
		int deg = n.degree();
		if(deg == 0){
			if(n.parent() != null){
				if(n.left() == n)
					n.setLeft(null);
				else
					n.setRight(null);
			}
		}
		else if(deg == 1){
			if(n.left() != null) {
				n.left().setParent(n.parent());
				n.parent().setLeft(n.left());
			}
			else {
				n.right().setParent(n.parent());
				n.parent().setRight(n.right());
			}
		}
		else if(deg == 2){
			Node min = getMinNode(n.right());
			min.setRight(n.right());
			min.setLeft(n.left());
			
			if(n.parent().left() == n){
				n.parent().setLeft(min);
			}
			else{
				n.parent().setRight(min);
			}
		}
	}
	
	/**
	 * Return node with min key in subtree rooted at input node
	 * @param root
	 * @return
	 */
	private Node getMinNode(Node root){
		Node current = root;
		Node parent = null;
		
		while(current != null){
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

	// TODO
	public void leftRotate(Node reference) {

	}

	// TODO
	public void rightRotate(Node reference) {

	}
}
