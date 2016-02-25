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
				line = br.readLine().split(",");
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
		System.out.println(root.getKey());
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
		System.out.println(root.getValue());
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
			return false;
		} else {
			leftValid = checkValidBST(root.left());
		}

		if (root.right() != null && root.right().getKey() < root.getKey()) {
			return false;
		} else {
			rightValid = checkValidBST(root.right());
		}

		return leftValid && rightValid;
	}

	public static boolean checkBalancedRbBST(Node root, int black) {
		Node x = root;
		while (x != null) {
			if (x.getColor() == BLACK)
				black++;
			x = x.left();
		}
		
		if (root == null) return black == 0;
        if (root.getColor() != RED) black--;
        return checkBalancedRbBST(root.left(), black) && checkBalancedRbBST(root.right(), black);
	}

}
