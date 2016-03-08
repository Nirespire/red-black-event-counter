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

public class Util {

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
					&& (n.parent() == null || n.parent().getColor() == BLACK) && verifyProperty4(n.left())
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

	public static void colorDeepestLeafs(Node root) {
		List<Node> nodes = Util.findDeepestNodes(root);

		for (Node i : nodes) {
			i.setColor(RED);
		}
	}

	public static List findDeepestNodes(Node root) {
		Object[] levelNodes = new Object[2];
		levelNodes[0] = 0;
		levelNodes[1] = new ArrayList();
		findDeepestNodes(root, 1, levelNodes);
		return (List) levelNodes[1];
	}

	private static void findDeepestNodes(Node root, int level, Object[] levelNodes) {
		if (root == null)
			return;
		if ((Integer) levelNodes[0] <= level) {
			if ((Integer) levelNodes[0] < level) {
				((List) levelNodes[1]).clear();
			}
			levelNodes[0] = level;
			((List) levelNodes[1]).add(root);
		}
		findDeepestNodes(root.left(), level + 1, levelNodes);
		findDeepestNodes(root.right(), level + 1, levelNodes);
	}

	public boolean validateNode(Node n) {
		boolean isValid = true;

		if (n.parent() != null) {
			boolean isRight = (n.parent().right() == n ? true : false);

			if (isRight) {
				isValid = isValid && n.parent().getKey() < n.getKey();
			} else {
				isValid = isValid && n.parent().getKey() > n.getKey();
			}
		}

		if (n.right() != null) {
			isValid = isValid && n.right().getKey() > n.getKey();
		}

		if (n.left() != null) {
			isValid = isValid && n.left().getKey() < n.getKey();
		}

		return isValid;
	}

}
