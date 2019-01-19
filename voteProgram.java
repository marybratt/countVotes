import java.sql.Array;
import java.util.Scanner;

public class voteProgram {
	public static int noOfVotes;
	public static String[] votesList;
	public static int counterA;
	public static int counterB;
	public static int counterInvalid;
	public static String voteList = "";
	public static boolean numVotesOk = false;
	static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		noOfVotes = getNumOfVotes();
//		input.nextLine();
		System.out.println("Enter list of " + noOfVotes + " votes (A or B). "
				+ "\nAny votes past " + noOfVotes + " will not be counted as invalid");
		voteList = input.next();
		votesList = voteList.split("", voteList.length());
		calcWinner();
		displayWinner();
		input.close();
	}

	public static int getNumOfVotes() {
		//		Scanner input = new Scanner(System.in);
		boolean isNumeric = false;
		while (!isNumeric) {
			try {
				System.out.println("How many votes will there be (1 to 15)?");
				noOfVotes = input.nextInt();
				input.nextLine();
				isNumeric = true;
			} 
			catch(Exception e) {
				System.out.println("You must enter a  number between 1 and 15!");
				isNumeric = false;
				input.nextLine();
			}
		}
			while (!numVotesOk) {
//				System.out.println("How many votes will there be (1 to 15)?");
//				noOfVotes = input.nextInt();
//				input.nextLine();
				if (noOfVotes > 0 && noOfVotes <= 15) {
					numVotesOk = true;
				} else {
					System.out.println("The number of votes must be between 1 and 15!");
					numVotesOk = false;
					noOfVotes = input.nextInt();
					input.nextLine();
				}
			}
			//System.out.println("no of votes: "+ noOfVotes);
			return noOfVotes;
		}
	
	private static void displayWinner() {
		if (counterA == counterB ) {
			System.out.println("This was a tie! Everyone wins!");
			System.out.println("Contestant A ended with " + counterA + " votes");
			System.out.println("Contestant B ended with " + counterB + " votes");
			System.out.println("There were " + counterInvalid + " invalid votes");
		} else if (counterA > counterB ) {
			System.out.println("Contestant A wins with "+ counterA + " votes");
			System.out.println("Contestant B ended with " + counterB + " votes");
			System.out.println("There were " + counterInvalid + " invalid votes");
		} else if (counterB > counterA) {
			System.out.println("Contestant B wins with "+ counterB + " votes");
			System.out.println("Contestant A ended with " + counterA + " votes");
			System.out.println("There were " + counterInvalid + " invalid votes");
		} else {
			System.out.println("Could not determine a winner");
		}
	
		
	}

	private static void calcWinner() {
		counterA = 0;
		counterB = 0;
		counterInvalid= 0;
		int upperLimit = 0;
		if (voteList.length() < noOfVotes) {
			upperLimit = voteList.length();
		} else {
			upperLimit = noOfVotes;
		}
		for (int i = 0; i < upperLimit; i++) {
			if (votesList[i].toUpperCase().equals("A")) {
				counterA++;
			} else if (votesList[i].toUpperCase().equals("B")) {
				counterB++;
			} else {
				counterInvalid++;
			}
		}
		int sum = counterA + counterB + counterInvalid;
		if (sum < voteList.length()) {
			counterInvalid = counterInvalid + (voteList.length() - sum);
		} else if (sum >= voteList.length()) {
			counterInvalid = counterInvalid + (noOfVotes-sum);
		}
	}

//	private static void checkValidVotes() {
//			for (int i = 0; i < noOfVotes; i++) {
////				System.out.println(votesList[i] + " " + i + " " );
//				if (!votesList[i].toUpperCase().equals("A") && !votesList[i].toUpperCase().equals("B")) {
////				if (votesList[i].equals("A") || votesList[i].equals(anObject)"a" || votesList[i] == "B" || votesList[i] == "b") {
////					System.out.println(votesList[i].toUpperCase());
////				} else {
//					//System.out.println(votesList[i].toUpperCase());
//					System.out.println("You can only enter A or B. Please enter your votes again");
//					break;
//				}
//			}
//	}

}
# countVotes
