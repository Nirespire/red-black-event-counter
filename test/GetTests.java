import org.junit.Assert;
import org.junit.Test;
import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

public class GetTests {
	public void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}

	// Naturally balanced
	@Test
	public void testGet() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);
		
		Assert.assertNotNull(t.getNode(4));
		Assert.assertNotNull(t.getNode(3));
		Assert.assertNotNull(t.getNode(5));
		
		Assert.assertEquals(4, t.getValue(4));
		Assert.assertEquals(3, t.getValue(3));
		Assert.assertEquals(5, t.getValue(5));
		
		Assert.assertEquals(t.getNode(4), t.root);
		Assert.assertEquals(t.getNode(3), t.root.left());
		Assert.assertEquals(t.getNode(5), t.root.right());
		
		Assert.assertEquals(t.getNode(3).parent(), t.root);
		Assert.assertEquals(t.getNode(5).parent(), t.root);
		
		Assert.assertNull(t.getNode(3).left());
		Assert.assertNull(t.getNode(3).right());
		Assert.assertNull(t.getNode(5).left());
		Assert.assertNull(t.getNode(5).right());
		
		checkValid(t.root);

	}

	// LLb imbalance
	@Test
	public void testGet2() {
		Tree t = new Tree();

		t.insert(5, 5);
		t.insert(4, 4);
		t.insert(3, 3);
		
		Assert.assertNotNull(t.getNode(4));
		Assert.assertNotNull(t.getNode(3));
		Assert.assertNotNull(t.getNode(5));
		
		Assert.assertEquals(4, t.getValue(4));
		Assert.assertEquals(3, t.getValue(3));
		Assert.assertEquals(5, t.getValue(5));
		
		Assert.assertEquals(t.getNode(4), t.root);
		Assert.assertEquals(t.getNode(3), t.root.left());
		Assert.assertEquals(t.getNode(5), t.root.right());
		
		Assert.assertEquals(t.getNode(3).parent(), t.root);
		Assert.assertEquals(t.getNode(5).parent(), t.root);
		
		Assert.assertNull(t.getNode(3).left());
		Assert.assertNull(t.getNode(3).right());
		Assert.assertNull(t.getNode(5).left());
		Assert.assertNull(t.getNode(5).right());
		
		checkValid(t.root);
	}

	// RRb imbalance
	@Test
	public void testGet3() {
		Tree t = new Tree();

		t.insert(3, 3);
		t.insert(4, 4);
		t.insert(5, 5);
		
		Assert.assertNotNull(t.getNode(4));
		Assert.assertNotNull(t.getNode(3));
		Assert.assertNotNull(t.getNode(5));
		
		Assert.assertEquals(4, t.getValue(4));
		Assert.assertEquals(3, t.getValue(3));
		Assert.assertEquals(5, t.getValue(5));
		
		Assert.assertEquals(t.getNode(4), t.root);
		Assert.assertEquals(t.getNode(3), t.root.left());
		Assert.assertEquals(t.getNode(5), t.root.right());
		
		Assert.assertEquals(t.getNode(3).parent(), t.root);
		Assert.assertEquals(t.getNode(5).parent(), t.root);
		
		Assert.assertNull(t.getNode(3).left());
		Assert.assertNull(t.getNode(3).right());
		Assert.assertNull(t.getNode(5).left());
		Assert.assertNull(t.getNode(5).right());
		
		checkValid(t.root);
	}

	// LRb imbalance
	@Test
	public void testGet4() {
		Tree t = new Tree();

		t.insert(5, 5);
		t.insert(3, 3);
		t.insert(4, 4);
		
		Assert.assertNotNull(t.getNode(4));
		Assert.assertNotNull(t.getNode(3));
		Assert.assertNotNull(t.getNode(5));
		
		Assert.assertEquals(4, t.getValue(4));
		Assert.assertEquals(3, t.getValue(3));
		Assert.assertEquals(5, t.getValue(5));
		
		Assert.assertEquals(t.getNode(4), t.root);
		Assert.assertEquals(t.getNode(3), t.root.left());
		Assert.assertEquals(t.getNode(5), t.root.right());
		
		Assert.assertEquals(t.getNode(3).parent(), t.root);
		Assert.assertEquals(t.getNode(5).parent(), t.root);
		
		Assert.assertNull(t.getNode(3).left());
		Assert.assertNull(t.getNode(3).right());
		Assert.assertNull(t.getNode(5).left());
		Assert.assertNull(t.getNode(5).right());
		
		checkValid(t.root);
	}

	// RLb imbalance
	@Test
	public void testGet5() {
		Tree t = new Tree();

		t.insert(3, 3);
		t.insert(5, 5);
		t.insert(4, 4);
		
		Assert.assertNotNull(t.getNode(4));
		Assert.assertNotNull(t.getNode(3));
		Assert.assertNotNull(t.getNode(5));
		
		Assert.assertEquals(4, t.getValue(4));
		Assert.assertEquals(3, t.getValue(3));
		Assert.assertEquals(5, t.getValue(5));
		
		Assert.assertEquals(t.getNode(4), t.root);
		Assert.assertEquals(t.getNode(3), t.root.left());
		Assert.assertEquals(t.getNode(5), t.root.right());
		
		Assert.assertEquals(t.getNode(3).parent(), t.root);
		Assert.assertEquals(t.getNode(5).parent(), t.root);
		
		Assert.assertNull(t.getNode(3).left());
		Assert.assertNull(t.getNode(3).right());
		Assert.assertNull(t.getNode(5).left());
		Assert.assertNull(t.getNode(5).right());
		
		checkValid(t.root);
	}

	// RRr
	@Test
	public void testGet6() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.insert(6, 6);
		

		checkValid(t.root);
		
		
	}

	// LLr
	@Test
	public void testGet7() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.insert(2, 2);

		checkValid(t.root);
	}

	// RLr
	@Test
	public void testGet8() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(6, 6);

		t.insert(5, 5);

		checkValid(t.root);
	}

	// LRr
	@Test
	public void testGet9() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(2, 2);
		t.insert(6, 6);

		t.insert(3, 3);

		checkValid(t.root);
	}
}
