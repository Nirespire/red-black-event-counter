package util;

import static util.Color.BLACK;
import static util.Color.RED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import redBlackBST.Node;
import redBlackBST.Tree;

/**
 * 
 * @author Sanjay Nair
 *
 */
public class Util {

	/**
	 * Constructs a Tree from the provided file assuming it is 
	 * in the proper format and the keys are in non-decreasing,
	 * sorted order.
	 * 
	 * @param filename filename containing sorted keys and values
	 * @return Red-Black Tree containing provided keys and values
	 */
	public static Tree readInputFile(String filename) {

		BufferedReader br = null;
		int[] keys = null;
		int[] values = null;

		try {

			String line[];

			br = new BufferedReader(new FileReader(filename));

			int count = Integer.parseInt(br.readLine());

			keys = new int[count];
			values = new int[count];

			for (int i = 0; i < count; i++) {
				line = br.readLine().split(" ");
				keys[i] = Integer.parseInt(line[0]);
				values[i] = Integer.parseInt(line[1]);
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
		
		System.out.println("Done reading file");

		Node root = Util.buildBBSTFromSortedArray(keys, values, 0, keys.length - 1);
		
		System.out.println("Done Building Tree");
		
		Util.colorDeepestLeafs(root);
		
		System.out.println("Done Coloring Tree");

		Tree output = new Tree(root);

		return output;
	}

	/**
	 * DEBUG. Prints out roughly formatted Tree.
	 * 
	 * @param root root of tree to be printed
	 */
	public static void printTree(Node root) {
		if (root == null) {
			return;
		}

		printTree(root.left());
		System.out.println(root.getKey() + " " + root.getColor().name());
		printTree(root.right());
	}

	/**
	 * DEBUG. Prints out roughly formatted Tree.
	 * 
	 * @param root root of Tree to be printed
	 * @param indent recursive parameter to further indent deeper node keys and values
	 */
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

	/**
	 * Validates whether the Tree rooted at the input adheres to
	 * the unbalanced Binary Search Tree properties. Does not test for any
	 * other rules.
	 * 
	 * @param root root of Binary Tree to be validated
	 * @return valid BST
	 */
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

	/**
	 * Validates whether the Tree rooted at the input adheres to
	 * all the properties of a Red-Black Tree.
	 * 
	 * @param root root of Red-Black Tree to be validated
	 * @return RB Tree valid
	 */
	public static boolean checkValidRbBST(Node root) {

		boolean one = verifyProperty1(root);
		boolean two = verifyProperty2(root);
		boolean four = verifyProperty4(root);
		try {
			verifyProperty5(root);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		;

		if (!one) {
			System.out.println("All Nodes not RED or BLACK");
		}
		if (!two) {
			System.out.println("Root not BLACK");
		}
		if (!four) {
			System.out.println("RED Nodes adjacent to each other");
		}

		return one && two && four;
	}


	/**
	 * Red-Black Property 1:
	 * All nodes are RED or BLACK.
	 * 
	 * @param n root of Tree to validate
	 * @return valid Tree for property 1
	 */
	public static boolean verifyProperty1(Node n) {
		if (n == null)
			return true;
		boolean redOrBlack = n.getColor() == RED || n.getColor() == BLACK;
		return redOrBlack && verifyProperty1(n.left()) && verifyProperty1(n.right());
	}

	/**
	 * Red-Black Property 2:
	 * Root is BLACK.
	 * 
	 * @param root root of Tree to validate
	 * @return valid Tree for property 2
	 */
	public static boolean verifyProperty2(Node root) {
		return root == null || root.getColor() == BLACK;
	}

	/**
	 * Red-Black Property 2:
	 * No 2 RED Nodes are next to each other.
	 * 
	 * @param n root of Tree to validate
	 * @return valid Tree for property 3
	 */
	public static boolean verifyProperty4(Node n) {
		if (n == null || n.getColor() == BLACK)
			return true;
		else {
			return (n.left() == null || n.left().getColor() == BLACK)
					&& (n.right() == null || n.right().getColor() == BLACK)
					&& (n.parent() == null || n.parent().getColor() == BLACK) && verifyProperty4(n.left())
					&& verifyProperty4(n.right());
		}
	}

	/**
	 * Red-Black Property 5:
	 * All paths to external nodes should pass the same number of BLACK nodes.
	 * 
	 * @param root root of Tree to validate
	 * @throws Exception BLACK height violation
	 */
	public static void verifyProperty5(Node root) throws Exception {
		verifyProperty5Helper(root, 0, -1);
	}

	/**
	 * Recursive helper for verifyProperty5
	 * 
	 * @param n n root of Tree to validate
	 * @param blackCount current BLACK height
	 * @param pathBlackCount current BLACK height along this subtree path
	 * @return recursive BLACK height
	 * @throws Exception BLACK height violation
	 * @see Util#verifyProperty5(Node)
	 */
	private static int verifyProperty5Helper(Node n, int blackCount, int pathBlackCount) throws Exception {
		if (n == null) {
			if (pathBlackCount == -1) {
				pathBlackCount = blackCount;
			} else {
				if (blackCount != pathBlackCount) {
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

	/**
	 * Recursively constructs a Balanced Binary Search Tree from a sorted
	 * input list of keys and values.
	 * 
	 * @param keys array of integer keys
	 * @param values array of integer values corresponding to keys
	 * @param start start index inclusive
	 * @param end end index non-inclusive
	 * @return root Node of constructed BBST
	 */
	public static Node buildBBSTFromSortedArray(int[] keys, int[] values, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		
		Node left = buildBBSTFromSortedArray(keys, values, start, mid - 1);
		Node right = buildBBSTFromSortedArray(keys, values, mid + 1, end);
		
		Node root = new Node(keys[mid], values[mid]);

		root.setLeft(left);
		if (left != null) {
			left.setParent(root);
		}

		root.setRight(right);
		if (right != null) {
			right.setParent(root);
		}

		return root;
	}

	/**
	 * Colors the Nodes RED at the deepest level of Tree
	 * rooted at input.
	 * 
	 * @param root root of Tree to be colored
	 */
	public static void colorDeepestLeafs(Node root) {
		List<Node> nodes = Util.findDeepestNodes(root);

		for (Node i : nodes) {
			i.setColor(RED);
		}
	}

	/**
	 * Returns a list of Nodes at the deepest level in a Tree
	 * rooted at the input.
	 * 
	 * @param root root of Tree to be searched
	 * @return List of Nodes at deepest level in the Tree
	 */
	public static List<Node> findDeepestNodes(Node root) {
		Object[] levelNodes = new Object[2];
		levelNodes[0] = 0;
		levelNodes[1] = new ArrayList<Node>();
		findDeepestNodesHelper(root, 1, levelNodes);
		return (List<Node>) levelNodes[1];
	}

	/**
	 * Recursive helper to collect and return the Nodes at the deepest
	 * level in a Tree rooted at the input.
	 * 
	 * Note: generic Object casting is used to preserve Node references 
	 * when function returns.
	 * 
	 * @param root root of Tree currently being searched
	 * @param level current level being searched
	 * @param levelNodes Object array where levelNode[0] holds an integer corresponding to the level of the ArrayList of Nodes held in levelNode[1]
	 */
	private static void findDeepestNodesHelper(Node root, int level, Object[] levelNodes) {
		if (root == null)
			return;
		if ((Integer) levelNodes[0] <= level) {
			if ((Integer) levelNodes[0] < level) {
				((List<Node>) levelNodes[1]).clear();
			}
			levelNodes[0] = level;
			((List<Node>) levelNodes[1]).add(root);
		}
		findDeepestNodesHelper(root.left(), level + 1, levelNodes);
		findDeepestNodesHelper(root.right(), level + 1, levelNodes);
	}
	
	/**
	 * DEBUG. Generates a large input file in the specific format for
	 * testing file reading and initial Tree construction performance.
	 */
	public static void createHugeInputFile() {
		try {

			File file = new File("input3.exclude.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter bw = new PrintWriter(file.getAbsoluteFile());

			int numElements = 150000 * 300;
			bw.println(numElements);

			for (int i = 1; i <= numElements; i++) {
				bw.println(i + " " + i);
			}

			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
