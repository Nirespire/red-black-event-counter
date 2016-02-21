import java.util.Scanner;

import redBlackBST.Node;
import util.Util;

public class bbst {

	public static void main(String[] args) {
		String filename;
		
		if(args.length > 0){
			filename = args[0];
		}
		else{
			Scanner input = new Scanner(System.in);
			System.out.println("Input filename: ");
			filename = input.next();
			input.close();
		}
		
		Node[] inputNodes = Util.readInputFile(filename);
	}

}
