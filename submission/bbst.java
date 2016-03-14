import java.util.Scanner;
import redBlackBST.Node;
import redBlackBST.Tree;
import util.Util;

/*
 * QUESTIONS FOR TA
 * 
 * For Next(theID) and Previous(theID), can we assume theID exists in the tree?
 * 
 * Same for inRange(id1, id2) can we assume both ids exist
 * 
 * When are we getting the input file
 */

public class bbst {

	public static Tree t;

	public static void main(String[] args) {
		
		//Util.createHugeInputFile();
		
		String filename;
		Scanner input = new Scanner(System.in);

		if (args.length > 0) {
			filename = args[0];
			System.out.println("Input filename: " + filename);
		} else {
			System.out.println("Input filename: ");
			filename = input.next();
		}

		t = Util.readInputFile(filename);
		
		System.out.println("You may begin entering commands. Type \"exit\" to terminate program.");
		System.out.println("________________________________________");
		
		String userInput;

		while (true) {
			userInput = input.nextLine();

			if (userInput.equals("exit")) {
				input.close();
				break;
			}

			parseAndExcute(userInput);
		}
	}

	public static void parseAndExcute(String input) {

		if (input == null || input.isEmpty()) {
			return;
		}

		String[] args = input.split(" ");

		if (args.length > 1) {
			switch (args[0]) {
			case "increase":
				increase(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				break;
			case "reduce":
				reduce(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				break;
			case "count":
				count(Integer.parseInt(args[1]));
				break;
			case "inrange":
				inRange(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				break;
			case "next":
				next(Integer.parseInt(args[1]));
				break;
			case "previous":
				previous(Integer.parseInt(args[1]));
				break;
			default:
				System.out.println("invalid");
				break;
			}
		}
	}

	/**
	 * Increase the count of the event theID by m. If theID is not present,
	 * insert it. Print the count of theID after the addition.
	 *
	 * @param theID
	 * @param m
	 */
	public static void increase(int theID, int m) {
		System.out.println(t.increase(theID, m));
	}

	/**
	 * Decrease the count of theID by m. If theID's count becomes less than or
	 * equal to 0, remove theID from the counter. Print the count of theID after
	 * the deletion, or 0 if theID is removed or not present.
	 *
	 * @param theID
	 * @param m
	 */
	public static void reduce(int theID, int m) {
		System.out.println(t.decrease(theID, m));
	}

	/**
	 * Print the count of theID. If not present, print 0.
	 *
	 * @param theID
	 */
	public static void count(int theID) {
		Node n = t.getNode(theID);

		if (n != null) {
			System.out.println(n.getValue());
		} else {
			System.out.println(0);
		}
	}

	/**
	 * Print the total count for IDs between ID1 and ID2 inclusively.
	 *
	 * Note, ID1 <= ID2
	 *
	 * @param id1
	 * @param id2
	 */
	public static void inRange(int id1, int id2) {	
		System.out.println(t.inRange(id1, id2));
	}

	/**
	 * Print the ID and the count of the event with the lowest ID that is
	 * greater that theID. Print "0 0", if there is no next ID.
	 *
	 * @param theID
	 */
	public static void next(int theID) {
		Node n = t.getNextNode(theID);

		if (n != null) {
			System.out.println(n.getKey() + " " + n.getValue());
		} else {
			System.out.println("0 0");
		}
	}

	/**
	 * Print the ID and the count of the event with the greatest key that is
	 * less that theID. Print "0 0", if there is no previous ID.
	 *
	 * @param theID
	 */
	public static void previous(int theID) {
		Node n = t.getPreviousNode(theID);

		if (n != null) {
			System.out.println(n.getKey() + " " + n.getValue());
		} else {
			System.out.println("0 0");
		}
	}

}
