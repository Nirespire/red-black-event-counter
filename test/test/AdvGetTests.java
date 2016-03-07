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
}
