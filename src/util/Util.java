package util;

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
		if(root == null){
			return;
		}
		
		printTree(root.left());
		System.out.println(root.getKey());
		printTree(root.right());
	}
	
	public static boolean checkValidBST(Node root){
		if(root == null){
			return true;
		}
		
		if(root.left() == null && root.right() == null){
			return true;
		}
		
		boolean leftValid = true;
		boolean rightValid = true;
		
		if(root.left() != null && root.left().getKey() > root.getKey()){
			return false;	
		}
		else{
			leftValid = checkValidBST(root.left());
		}
		
		if(root.right() != null && root.right().getKey() < root.getKey()){
			return false;
		}
		else{
			rightValid = checkValidBST(root.right());
		}
		
		return leftValid && rightValid;
	}
}
