import org.junit.Assert;
import org.junit.Test;
import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

public class InsertTests {
	public void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}

	// Naturally balanced
	@Test
	public void testInsert() {
		Tree t = new Tree();

		checkValid(t.root);
		t.insert(4, 4);
		checkValid(t.root);
		t.insert(3, 3);
		checkValid(t.root);
		t.insert(5, 5);
		checkValid(t.root);
	}

	// LLb imbalance
	@Test
	public void testInsert2() {
		Tree t = new Tree();

		checkValid(t.root);
		t.insert(5, 5);
		checkValid(t.root);
		t.insert(4, 4);
		checkValid(t.root);
		t.insert(3, 3);
		checkValid(t.root);
	}

	// RRb imbalance
	@Test
	public void testInsert3() {
		Tree t = new Tree();

		checkValid(t.root);
		t.insert(3, 3);
		checkValid(t.root);
		t.insert(4, 4);
		checkValid(t.root);
		t.insert(5, 5);
		checkValid(t.root);
	}

	// LRb imbalance
	@Test
	public void testInsert4() {
		Tree t = new Tree();

		checkValid(t.root);
		t.insert(5, 5);
		checkValid(t.root);
		t.insert(3, 3);
		checkValid(t.root);
		t.insert(4, 4);
		checkValid(t.root);
	}

	// RLb imbalance
	@Test
	public void testInsert5() {
		Tree t = new Tree();

		checkValid(t.root);
		t.insert(3, 3);
		checkValid(t.root);
		t.insert(5, 5);
		checkValid(t.root);
		t.insert(4, 4);
		checkValid(t.root);
	}

	// RRr
	@Test
	public void testInsert6() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.insert(6, 6);

		checkValid(t.root);
	}

	// LLr
	@Test
	public void testInsert7() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.insert(2, 2);

		checkValid(t.root);
	}

	// RLr
	@Test
	public void testInsert8() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(6, 6);

		t.insert(5, 5);

		checkValid(t.root);
	}

	// LRr
	@Test
	public void testInsert9() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(2, 2);
		t.insert(6, 6);

		t.insert(3, 3);

		checkValid(t.root);
	}
}
