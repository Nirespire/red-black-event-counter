package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import AVL.Node;

public class Util {
	public static Node[] readInputFile(String filename){
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
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		
		return output;
	}
}
