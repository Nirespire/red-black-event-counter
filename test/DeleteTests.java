import org.junit.Assert;
import org.junit.Test;
import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

public class DeleteTests {
	public void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}

	// Root is degree 2, delete right leaf
	@Test
	public void testDeleteLeaf1() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.delete(5);

		Assert.assertNull(t.getNode(5));

		checkValid(t.root);
	}

	// Root is degree 2, delete left leaf
	@Test
	public void testDeleteLeaf2() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.delete(3);

		Assert.assertNull(t.getNode(3));

		checkValid(t.root);
	}

	// Root is degree 1, delete left leaf
	@Test
	public void testDeleteLeaf3() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);

		t.delete(3);

		Assert.assertNull(t.getNode(3));

		checkValid(t.root);
	}

	// Root is degree 1, delete right leaf
	@Test
	public void testDeleteLeaf4() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(5, 5);

		t.delete(5);

		Assert.assertNull(t.getNode(5));

		checkValid(t.root);
	}

	// Root is degree 2, delete root
	@Test
	public void testDeleteRoot1() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);
		t.insert(5, 5);

		t.delete(4);

		Assert.assertNull(t.getNode(4));

		checkValid(t.root);
	}

	// Root is degree 1, delete root
	@Test
	public void testDeleteRoot2() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(3, 3);

		t.delete(4);

		Assert.assertNull(t.getNode(4));

		checkValid(t.root);
	}

	// Root is degree 1, delete root
	@Test
	public void testDeleteRoot3() {
		Tree t = new Tree();

		t.insert(4, 4);
		t.insert(5, 5);

		t.delete(4);

		Assert.assertNull(t.getNode(4));

		checkValid(t.root);
	}

	// Rb0
	// case 1: py is BLACK
	@Test
	public void testDelete_Rb0_case1() {
		Tree t = new Tree();

		t.insert(3);
		t.insert(2);
		t.insert(4);
		t.insert(5);

		t.delete(4);

		Assert.assertNull(t.getNode(4));

		checkValid(t.root);
	}
	
	// Lb0
	// case1: py is BLACK
	@Test
	public void testDelete_Lb0_case1(){
		Tree t = new Tree();

		t.insert(3);
		t.insert(2);
		t.insert(1);
		t.insert(4);

		t.delete(2);
		
		Assert.assertNull(t.getNode(2));
		
		checkValid(t.root);
	}

	// Rb0
	// case 2: py is RED
	@Test
	public void testDelete_Rb0_case2() {
		Tree t = new Tree();

		t.insert(7);
		t.insert(3);
		t.insert(35);
		t.insert(84);
		t.insert(34);
		t.insert(88);
		t.insert(72);

		t.delete(84);

		Assert.assertNull(t.getNode(84));

		checkValid(t.root);
	}

	// Rb1
	// case 1: v's left child is RED
	@Test
	public void testDelete_Rb1_case1() {
		Tree t = new Tree();

		t.insert(3);
		t.insert(2);
		t.insert(1);
		t.insert(4);

		t.delete(4);

		Assert.assertNull(t.getNode(4));

		checkValid(t.root);
	}

	// Rb1
	// case 2: v's right child is RED
	@Test
	public void testDelete_Rb1_case2() {
		Tree t = new Tree();

		t.insert(4);
		t.insert(2);
		t.insert(3);
		t.insert(5);

		t.delete(5);

		Assert.assertNull(t.getNode(5));

		checkValid(t.root);
	}

	// Rb2
	@Test
	public void testDelete_Rb2() {
		Tree t = new Tree();

		t.insert(4);
		t.insert(2);
		t.insert(3);
		t.insert(5);
		t.insert(1);

		t.delete(5);

		Assert.assertNull(t.getNode(5));

		checkValid(t.root);
	}

	// Rr(0)
	@Test
	public void testDelete_Rr0() {
		Tree t = new Tree();

		t.insert(20);
		t.insert(10);
		t.insert(5);
		t.insert(15);
		t.insert(30);

		t.delete(30);

		Assert.assertNull(t.getNode(30));

		checkValid(t.root);
	}

	// Rr(1)
	// case 1: RED node is v's left child
	@Test
	public void testDelete_Rr1_case1() {
		Tree t = new Tree();

		t.insert(20);
		t.insert(10);
		t.insert(5);
		t.insert(12);
		t.insert(11);
		t.insert(30);

		t.delete(30);

		Assert.assertNull(t.getNode(30));

		checkValid(t.root);
	}

	// Rr(1)
	// case 2: RED node is v's right child
	@Test
	public void testDelete_Rr1_case2() {
		Tree t = new Tree();

		t.insert(20);
		t.insert(10);
		t.insert(5);
		t.insert(12);
		t.insert(13);
		t.insert(30);

		t.delete(30);

		Assert.assertNull(t.getNode(30));

		checkValid(t.root);
	}

	// Rr(2)
	@Test
	public void testDelete_Rr2() {
		Tree t = new Tree();

		t.insert(20);
		t.insert(10);
		t.insert(5);
		t.insert(12);
		t.insert(13);
		t.insert(11);
		t.insert(30);

		t.delete(30);

		Assert.assertNull(t.getNode(30));

		checkValid(t.root);
	}

	@Test
	public void testFullDelete() {
		Tree t = new Tree();

		t.insert(7);
		t.insert(3);
		t.insert(35);
		t.insert(84);
		t.insert(34);
		t.insert(88);
		t.insert(72);
		t.insert(9);
		t.insert(98);
		t.insert(57);
		t.insert(55);
		t.insert(5);
		t.insert(98);
		t.insert(67);
		t.insert(70);
		t.insert(24);

		checkValid(t.root);

	}

}
