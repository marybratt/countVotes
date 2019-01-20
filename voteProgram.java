import java.sql.Array;
import java.util.Scanner;

public class voteProgram {
	public static int noOfVotes;// number of votes user will enter
	public static String[] votesList;//array of the votes entered
	public static int counterA;// count of the number of a's entered
	public static int counterB;// count of the number of b's entered
	public static int counterInvalid;// count of the number of char that are not A or B
	public static String voteList = ""; // string used to accept the user input of votes
	public static boolean numVotesOk = false;// flag to show if there is an invalid number
	static final Scanner input = new Scanner(System.in);// scanner call to allow user input
	
	public static void main(String[] args) {
		noOfVotes = getNumOfVotes(); // request the number of votes to be entered
		// show the information needed to count the votes and 
		//how invalid char will be handled
		System.out.println("Enter list of " + noOfVotes + " votes (A or B). "
				+ "\nAny votes past " + noOfVotes + " will not be counted as invalid");
		voteList = input.next();// accepts the user input as a string
		votesList = voteList.split("", voteList.length());//split the above string into an array
		calcWinner();// count the number of all votes to determine the winner
		displayWinner();// display the winner with statistics
		input.close();//close the scanner
	}

	// request user to enter the number of votes to expect
	// checks to make sure entry is a number between 1 and 15
	public static int getNumOfVotes() {
		boolean isNumeric = false;
		while (!isNumeric) {
			try { // check if entry is numeric
				System.out.println("How many votes will there be (1 to 15)?");
				noOfVotes = input.nextInt();
				input.nextLine();
				isNumeric = true;
			} 
			catch(Exception e) { //if not numeric will output message 
				System.out.println("You must enter a  number between 1 and 15!");
				isNumeric = false;
				input.nextLine();
			}
		}
			while (!numVotesOk) {// check to see if entry is between1 and 15
				if (noOfVotes > 0 && noOfVotes <= 15) {
					numVotesOk = true;
				} else { // if not between 1 and 15 output error message
					System.out.println("The number of votes must be between 1 and 15!");
					numVotesOk = false;
					noOfVotes = input.nextInt();
					input.nextLine();
				}
			}
			//System.out.println("no of votes: "+ noOfVotes);
			return noOfVotes; // returns the number of votes to expect as an integer
		}
	
	// will diplay the winner with statistics of who got how many votes
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
	// determine if element in the array is an A/B or other (invalid)
	// increase correct counter
	private static void calcWinner() {
		counterA = 0;
		counterB = 0;
		counterInvalid= 0;
		int upperLimit = 0;
		// determine if the string is < than the number indicated
		// if so then will use the string length as the iterator 
		if (voteList.length() < noOfVotes) { 
			upperLimit = voteList.length();
		} else { // if string not longer then will use user entry as iterator 
			upperLimit = noOfVotes;
		}
		// will loop to the number decided above
		for (int i = 0; i < upperLimit; i++) {
			if (votesList[i].toUpperCase().equals("A")) {
				counterA++;
			} else if (votesList[i].toUpperCase().equals("B")) {
				counterB++;
			} else {
				counterInvalid++;
			}
		}
		// will adjust the invalid number depending on whether the string
		// was shorter or longer than the user entered number
		int sum = counterA + counterB + counterInvalid;
		if (sum < voteList.length()) {
			counterInvalid = counterInvalid + (voteList.length() - sum);
		} else if (sum >= voteList.length()) {
			counterInvalid = counterInvalid + (noOfVotes-sum);
		}
	}
}
