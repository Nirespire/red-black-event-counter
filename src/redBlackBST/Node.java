package redBlackBST;

import util.Color;
import static util.Color.*;

public class Node {
	private int id;
	private int count;
	public Tree left;
	public Tree right;
	public Node parent;
	private Color color;
	
	
	public Node(int id){
		this.setId(id);
		setCount(0);
		left = null;
		right = null;
		parent = null;
		setColor(Black);
	}
	
	public Node(int id, int count){
		this.setId(id);
		this.setCount(count);
		left = null;
		right = null;
		parent = null;
		setColor(Black);
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void increaseCount(int amount){
		this.count += amount;
	}
	public void decreaseCount(int amount){
		this.count -= amount;
	}
	
	
	
	
}
