package test;

import org.junit.Assert;
import org.junit.Test;

import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

public class AdvGetTests {
	
	public void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}
	
	@Test
	public void testGetNext(){
		
		Tree t = new Tree();
		
		for (int i = 1; i < 100 + 1; i++) {
			t.insert(i, i);
		}
		
		checkValid(t.root);
		
		for(int i = 1; i < 100; i++){
			Node n = t.getNextNode(i);
			
			Assert.assertNotNull(n);
			Assert.assertEquals(i+1, n.getKey());
		}
		
		checkValid(t.root);
	}
	
	@Test
	public void testGetNextNotExist(){
		
		Tree t = new Tree();
		
		for (int i = 1; i < 100 + 1; i++) {
			t.insert(i, i);
		}
		
		t.insert(200, 200);
		
		checkValid(t.root);
		
		for(int i = 101; i < 200; i++){
			Node n = t.getNextNode(i);
			
			Assert.assertNotNull(n);
			Assert.assertEquals(200, n.getKey());
		}
		
		checkValid(t.root);
	}
	
	@Test
	public void testGetPrevious(){
		
		Tree t = new Tree();
		
		for (int i = 1; i < 100 + 1; i++) {
			t.insert(i, i);
		}
		
		checkValid(t.root);
		
		for(int i = 2; i < 100 + 1; i++){
			Node n = t.getPreviousNode(i);
			
			Assert.assertNotNull(n);
			Assert.assertEquals(i-1, n.getKey());
		}
		
		checkValid(t.root);
	}
	
	@Test
	public void testGetPreviousNotExist(){
		
		Tree t = new Tree();
		
		for (int i = 1; i < 100 + 1; i++) {
			t.insert(i, i);
		}
		
		checkValid(t.root);
		
		for(int i = 101; i < 200; i++){
			Node n = t.getPreviousNode(i);
			
			Assert.assertNotNull(n);
			Assert.assertEquals(100, n.getKey());
		}
		
		checkValid(t.root);
	}
	
	@Test
	public void testInRangeBothEndsExist(){
		Tree t = new Tree();

		t.insert(50);
		t.insert(30);
		t.insert(25);
		t.insert(10);
		t.insert(27);
		t.insert(5);
		t.insert(28);
		
		int total = t.inRange(10, 30);
		
		Assert.assertEquals(120, total);
		
	}
	
	@Test
	public void testInRangeNoLeftEnd(){
		Tree t = new Tree();

		t.insert(50);
		t.insert(30);
		t.insert(25);
		t.insert(10);
		t.insert(27);
		t.insert(5);
		t.insert(28);
		
		int total = t.inRange(11, 30);
		
		Assert.assertEquals(110, total);
		
	}
	
	@Test
	public void testInRangeNoRightEnd(){
		Tree t = new Tree();

		t.insert(50);
		t.insert(30);
		t.insert(25);
		t.insert(10);
		t.insert(27);
		t.insert(5);
		t.insert(28);
		
		int total = t.inRange(10, 35);
		
		Assert.assertEquals(120, total);
	}
	
	@Test
	public void testInRangeEndsDontExist(){
		Tree t = new Tree();

		t.insert(50);
		t.insert(30);
		t.insert(25);
		t.insert(10);
		t.insert(27);
		t.insert(5);
		t.insert(28);
		
		int total = t.inRange(7, 35);
		
		Assert.assertEquals(120, total);
	}
	
	@Test
	public void testInRangeEndsEqualExist(){
		Tree t = new Tree();

		t.insert(50);
		t.insert(30);
		t.insert(25);
		t.insert(10);
		t.insert(27);
		t.insert(5);
		t.insert(28);
		
		int total = t.inRange(27, 27);
		
		Assert.assertEquals(27, total);
	}
	
	@Test
	public void testInRangeEndsEqualDontExist(){
		Tree t = new Tree();

		t.insert(50);
		t.insert(30);
		t.insert(25);
		t.insert(10);
		t.insert(27);
		t.insert(5);
		t.insert(28);
		
		int total = t.inRange(29, 29);
		
		Assert.assertEquals(0, total);
	}
}
