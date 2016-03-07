package test;

import org.junit.Assert;
import org.junit.Test;

import redBlackBST.Node;
import util.KeyValuePair;
import util.Util;

public class BbstTest {
	
	public static void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}
	
	@Test
	public void testCreateBBST(){
		
		final int NUM_NODES = 10000000;
		
		KeyValuePair[] items = new KeyValuePair[NUM_NODES];
		for(int i = 1; i <= NUM_NODES; i++){
			items[i-1] = new KeyValuePair(i, i);
		}
		
		Node root = Util.buildBBSTFromSortedArray(items, 0, items.length-1);
		
		Util.colorDeepestLeafs(root);

		checkValid(root);

	}
}
