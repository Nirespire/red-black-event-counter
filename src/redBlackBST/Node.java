package redBlackBST;

import util.Color;
import static util.Color.*;

public class Node {
	private int key;
	private int value;
	private Color color;
	private Node left;
	private Node right;
	private Node parent;
	
	
	public Node(int id){
		this.setKey(id);
		setValue(0);
		left = null;
		right = null;
		parent = null;
		setColor(BLACK);
	}
	
	public Node(int id, int value){
		this.setKey(id);
		this.setValue(value);
		left = null;
		right = null;
		parent = null;
		setColor(BLACK);
	}
	
	public Node(int id, int value, Color color){
		this.setKey(id);
		this.setValue(value);
		left = null;
		right = null;
		parent = null;
		setColor(color);
	}
	
	public Node left(){
		return this.left;
	}
	
	public Node right(){
		return this.right;
	}
	
	public void setLeft(Node left){
		this.left = left;
	}
	
	public void setRight(Node right){
		this.right = right;
	}
	
	public Node parent(){
		return this.parent;
	}
	
	public Node grandparent(){
		if(this.parent != null){
			return this.parent.parent;
		}
		return null;
	}
	
	public boolean isLeft(Node n){
		return n == this.left;
	}
	
	public boolean isRight(Node n){
		return n == this.right;
	}
	
	public void setParent(Node parent){
		this.parent = parent;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void flipColor(){
		this.color = (this.color == RED ? BLACK : RED);
	}
	
	public int increaseValue(int amount){
		this.value += amount;
		return this.value;
	}
	public int decreaseValue(int amount){
		this.value -= amount;
		return this.value;
	}
	
	public int degree(){
		int sum = 0;
		if(right != null){
			sum++;
		}
		if(left != null){
			sum++;
		}
		return sum;
	}
	
	
	
	
}
