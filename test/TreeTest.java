import java.util.HashMap;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

public class TreeTest {

	public final int NUM_NODES = 9000;

	@Test
	public void testInsert() {
		buildIncreasingTree();
	}

	@Test
	public void testInsert2() {
		Tree t = buildRandomTree();
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
			int val = r.nextInt();
			while (values.containsKey(val)) {
				val = r.nextInt();
			}

			t.insert(val, val);
			values.put(val, val);
		}
		
		Util.printTree(t.root);
		Assert.assertTrue(Util.checkValidBST(t.root));

		return t;
	}

	@Test
	public void testInsertAndGet() {
		Tree t = buildIncreasingTree();

		for (int i = 1; i < NUM_NODES + 1; i++) {
			Node o = t.getNode(i);
			Assert.assertNotNull(o);
			Assert.assertEquals(o.getKey(), i);
			Assert.assertEquals(o.getValue(), i);
		}
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
	}
}
