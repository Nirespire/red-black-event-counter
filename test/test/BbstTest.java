package test;

import org.junit.Assert;
import org.junit.Test;

import redBlackBST.Node;
import util.Util;

public class BbstTest {
	
	public static void checkValid(Node root) {
		Assert.assertTrue(Util.checkValidBST(root));
		Assert.assertTrue(Util.checkValidRbBST(root));
	}
	
	@Test
	public void testCreateBBST(){
		
		final int NUM_NODES = 10000000;
		
		int[] keys = new int[NUM_NODES];
		int[] values = new int[NUM_NODES];
		for(int i = 1; i <= NUM_NODES; i++){
			keys[i-1] = i;
			values[i-1] = i;
		}
		
		Node root = Util.buildBBSTFromSortedArray(keys, values, 0, keys.length-1);
		
		Util.colorDeepestLeafs(root);

		checkValid(root);

	}
}
