package util;

import static util.Color.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import redBlackBST.Node;
import redBlackBST.Tree;

public class Util {
	public static Node[] readInputFile(String filename) {
		Node[] output = null;

		BufferedReader br = null;

		try {

			String line[];

			br = new BufferedReader(new FileReader(filename));

			int count = Integer.parseInt(br.readLine());
			output = new Node[count];

			for (int i = 0; i < count; i++) {
				line = br.readLine().split(" ");
				Node newNode = new Node(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
				output[i] = newNode;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return output;
	}

	public static void printTree(Node root) {
		if (root == null) {
			return;
		}

		printTree(root.left());
		System.out.println(root.getKey() + " " + root.getColor().name());
		printTree(root.right());
	}

	public static void printTree2(Node root, int indent) {

		for (int i = 0; i < indent; i++) {
			System.out.print("   ");
		}
		if (root == null) {
			System.out.println("null");
			return;
		}
		System.out.println(root.getValue() + " " + root.getColor().name());
		if (root.left() == null && root.right() == null) {
			return;
		}
		printTree2(root.left(), indent + 1);
		printTree2(root.right(), indent + 1);
	}

	public static boolean checkValidBST(Node root) {
		if (root == null) {
			return true;
		}

		if (root.left() == null && root.right() == null) {
			return true;
		}

		boolean leftValid = true;
		boolean rightValid = true;

		if (root.left() != null && root.left().getKey() > root.getKey()) {
			System.out.println("BST violation: " + root.left().getKey() + " /< " + root.getKey());
			return false;
		} else {
			leftValid = checkValidBST(root.left());
		}

		if (root.right() != null && root.right().getKey() < root.getKey()) {
			System.out.println("BST violation: " + root.right().getKey() + " /> " + root.getKey());
			return false;
		} else {
			rightValid = checkValidBST(root.right());
		}

		return leftValid && rightValid;
	}
	
	public static boolean checkValidRbBST(Node root){
		
		boolean one = verifyProperty1(root);
		boolean two = verifyProperty2(root);
		boolean four = verifyProperty4(root);
		try{
			verifyProperty5(root);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		};
		
		if(!one){
			System.out.println("All Nodes not RED or BLACK");
		}
		if(!two){
			System.out.println("Root not BLACK");
		}
		if(!four){
			System.out.println("RED Nodes adjacent to each other");
		}
		
		return one && two && four ;
	}

	// Property 1
	// All nodes RED or BLACK
	public static boolean verifyProperty1(Node n) {
		if (n == null)
			return true;
		boolean redOrBlack = n.getColor() == RED || n.getColor() == BLACK;
		return redOrBlack && verifyProperty1(n.left()) && verifyProperty1(n.right());
	}

	// Property 2
	// root is BLACK
	public static boolean verifyProperty2(Node root) {
		return root == null || root.getColor() == BLACK;
	}

	// Property 4
	// No 2 RED nodes together
	public static boolean verifyProperty4(Node n) {
		if (n == null || n.getColor() == BLACK)
			return true;
		else {
			return (n.left() == null || n.left().getColor() == BLACK)
			&& (n.right() == null || n.right().getColor() == BLACK)
			&& (n.parent() == null || n.parent().getColor() == BLACK)
			&& verifyProperty4(n.left())
			&& verifyProperty4(n.right());
		}
	}
	
	// Property 5
	// All paths to external nodes should pass the same number of BLACK nodes
	public static void verifyProperty5(Node root) throws Exception {
	    verifyProperty5Helper(root, 0, -1);
	}

	private static int verifyProperty5Helper(Node n, int blackCount, int pathBlackCount) throws Exception {
		if (n == null) {
	        if (pathBlackCount == -1) {
	            pathBlackCount = blackCount;
	        } else {
	            if(blackCount != pathBlackCount){
	            	throw new Exception("BLACK height error");
	            }
	        }
	        return pathBlackCount;
	    }
		
		if (n.getColor() == Color.BLACK) {
	        blackCount++;
	    }
	    
	    pathBlackCount = verifyProperty5Helper(n.left(), blackCount, pathBlackCount);
	    pathBlackCount = verifyProperty5Helper(n.right(), blackCount, pathBlackCount);
	    return pathBlackCount;
	}

}
