package AVL;

public class Node {
	private int id;
	private int count;
	public Node left;
	public Node right;
	public Node parent;
	private int bf;
	
	
	public Node(int id){
		this.setId(id);
		setCount(0);
		left = null;
		right = null;
		parent = null;
		setBf(0);
	}
	
	public Node(int id, int count){
		this.setId(id);
		this.setCount(count);
		left = null;
		right = null;
		parent = null;
		setBf(0);
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
	public int getBf() {
		return bf;
	}
	public void setBf(int bf) {
		this.bf = bf;
	}
	
	public void increaseCount(int amount){
		this.count += amount;
	}
	public void decreaseCount(int amount){
		this.count -= amount;
	}
	
	public void increaseBf(int amount){
		if(this.bf + 1 > 2){
			System.out.println("BF too high at " + id);
		}
		this.bf += amount;
	}
	public void decreaseBf(int amount){
		if(this.bf + 1 < -2){
			System.out.println("BF too low at " + id);
		}
		this.bf -= amount;
	}
	
	
	
	
}
