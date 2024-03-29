package test;
import java.util.HashMap;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

public class TreeTest {

	public final int NUM_NODES = 9000;
	
	public static void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}
	
	/**
	 * Creates Tree with inserting of increasing keys
	 * 
	 * @return
	 */
	public Tree buildIncreasingTree() {
		Tree t = new Tree();

		// Loop to create tree with increasing keys
		for (int i = 1; i < NUM_NODES + 1; i++) {
			t.insert(i, i);
		}
		
		checkValid(t.root);

		return t;
	}

	/**
	 * Creates a Tree with insertions of random keys with no duplicates
	 * 
	 * @return
	 */
	public Tree buildRandomTree() {
		Tree t = new Tree();
		HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();

		Random r = new Random(System.currentTimeMillis());

		for (int i = 0; i < NUM_NODES; i++) {
			int val = r.nextInt(NUM_NODES * 2);
			while (values.containsKey(val)) {
				val = r.nextInt();
			}

			t.insert(val, val);
			
			checkValid(t.root);
			
			values.put(val, val);
		}
		
		checkValid(t.root);

		return t;
	}
	
	public Tree buildCustomRandomTree(int size){
		Tree t = new Tree();
		HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();

		Random r = new Random(System.currentTimeMillis());

		for (int i = 0; i < size; i++) {
			int val = r.nextInt(size * 2);
			while (values.containsKey(val)) {
				val = r.nextInt();
			}

			t.insert(val, val);
			values.put(val, val);
		}
		
		checkValid(t.root);

		return t;
	}
	
	@Test
	public void printTree2Test(){
		Tree t = buildCustomRandomTree(10);
		Util.printTree2(t.root, 0);
	}

	@Test
	public void testInsertAndGet() {
		Tree t = buildIncreasingTree();

		for (int i = 1; i < NUM_NODES + 1; i++) {
			Node o = t.getNode(i);
			Assert.assertNotNull(o);
			Assert.assertEquals(o.getKey(), i);
			Assert.assertEquals(o.getValue(), i);
			
			if(o.left() != null){
				Assert.assertTrue(o.left().getValue() < o.getValue());
			}
			
			if(o.right() != null){
				Assert.assertTrue(o.right().getValue() > o.getValue());
			}
		}
		
		checkValid(t.root);
	}
	
	@Test
	public void testInsertAndGet2() {
		Tree t = buildRandomTree();
		
		int i = 0;
		for (i = 1; i < NUM_NODES + 1; i++) {
			Node o = t.getNode(i);
			if(o != null){
				Assert.assertEquals(o.getKey(), i);
				Assert.assertEquals(o.getValue(), i);
				
				if(o.left() != null){
					Assert.assertTrue(o.left().getValue() < o.getValue());
				}
				
				if(o.right() != null){
					Assert.assertTrue(o.right().getValue() > o.getValue());
				}
			}
		}
		
		Assert.assertTrue(i > 0);
		
		checkValid(t.root);
	}

	@Test
	public void testExistingIncrease() {
		Tree t = buildIncreasingTree();

		// Get value should return correct value
		// Means Node exists
		int oldVal = t.getValue(NUM_NODES);
		Assert.assertEquals(NUM_NODES, oldVal);

		// Increase should return oldVal + 5
		int newVal = t.increase(NUM_NODES, 5);
		Assert.assertEquals(oldVal + 5, newVal);

		// Make sure you can get the node and it returns the right value
		Node o = t.getNode(NUM_NODES);
		Assert.assertNotNull(o);
		Assert.assertEquals(NUM_NODES + 5, o.getValue());
		
		checkValid(t.root);
	}

	@Test
	public void testNotExistingIncrease() {
		Tree t = buildIncreasingTree();

		// Get Value should return 0 for non-existing node
		int oldVal = t.getValue(NUM_NODES + 10);
		Assert.assertEquals(0, oldVal);

		// Get Node should return null
		Node o = t.getNode(NUM_NODES + 10);
		Assert.assertNull(o);

		// Increasing a non-existing node should insert it with the amount as
		// the value
		int newVal = t.increase(NUM_NODES + 10, 5);
		Assert.assertEquals(5, newVal);

		// Should be able to get the node as well
		o = t.getNode(NUM_NODES + 10);
		Assert.assertNotNull(o);
		Assert.assertEquals(5, o.getValue());
		
		checkValid(t.root);
	}

	@Test
	public void testExistingDecrease() {
		Tree t = buildIncreasingTree();

		// Should be able to get old value
		int oldVal = t.getValue(NUM_NODES);
		Assert.assertEquals(NUM_NODES, oldVal);

		// Decrease should return updated value
		int newVal = t.decrease(NUM_NODES, 5);
		Assert.assertEquals(NUM_NODES - 5, newVal);

		// Should be able to get updated node
		// NOT deleted because value !< 0
		Node o = t.getNode(NUM_NODES);
		Assert.assertNotNull(o);
		Assert.assertEquals(NUM_NODES - 5, o.getValue());
		
		checkValid(t.root);
	}

	@Test
	public void testNotExisitingDecrease() {
		Tree t = buildIncreasingTree();

		// Get Value should return 0 for non-existing node
		int oldVal = t.getValue(NUM_NODES + 10);
		Assert.assertEquals(0, oldVal);

		// Get Node should return null
		Node o = t.getNode(NUM_NODES + 10);
		Assert.assertNull(o);

		// Decrease on non-existing node does nothing, returns 0
		int newVal = t.decrease(NUM_NODES + 10, 5);
		Assert.assertEquals(0, newVal);

		// No new Node should have been inserted
		o = t.getNode(NUM_NODES + 10);
		Assert.assertNull(o);
		
		checkValid(t.root);
	}

	@Test
	public void testDeletingDecrease() {
		Tree t = buildIncreasingTree();

		// Should be able to get old value of existing node
		int oldVal = t.getValue(NUM_NODES);
		Assert.assertEquals(NUM_NODES, oldVal);
		
		// Decrease value by existing value so it gets set to 0
		t.decrease(NUM_NODES, oldVal);
		
		// 0 value node should be deleted after decrease
		int newVal = t.getValue(NUM_NODES);
		Assert.assertEquals(0, newVal);
		
		// Node shouldn't exist anymore
		Node o = t.getNode(NUM_NODES);
		Assert.assertNull(o);
		
		checkValid(t.root);
	}
}
