import java.util.Random;

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
	
	public void testInsert10(){
		Tree t = new Tree();
		
		t.insert(1, 1);
		t.insert(2, 2);
		t.insert(3, 3);

		checkValid(t.root);
	}
	
	@Test
	public void testIncreasingInserts(){
		Tree t = new Tree();

		for (int i = 1; i < 1000 + 1; i++) {
			t.insert(i, i);
			checkValid(t.root);
		}
		
	}
	
	@Test
	public void testDecreasingInserts(){
		Tree t = new Tree();

		for (int i = 1000; i > 0; i--) {
			t.insert(i, i);
			checkValid(t.root);
		}
	}
	
	@Test
	public void testRandomInserts(){
		Tree t = new Tree();
		Random r = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < 100; i++){
			int val = r.nextInt(100);
			t.insert(val, val);
			checkValid(t.root);
		}
	}
	
	@Test
	public void duplicateInsertTest(){
		Tree t = new Tree();
		
		t.insert(1, 1);
		t.insert(2, 2);
		t.insert(3, 3);
		
		t.insert(1, 1);
		
		checkValid(t.root);
	}
	
	// 7,3,35,84,34,88,72,9,98(trouble),57,55,5,98,67,70,24
	@Test
	public void testBrokenInsertCase(){
		
		Tree t = new Tree();
		
		t.insert(7);
		t.insert(3);
		t.insert(35);
		t.insert(84);
		t.insert(34);
		t.insert(88);
		t.insert(72);
		t.insert(9);
		System.out.println("--TROUBLE--");
		t.insert(98);
		checkValid(t.root);
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
