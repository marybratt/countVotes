import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class voteGUI extends JFrame{
	private JTextField noOfVotesTextField;//field to enter number of votes to process
	private JTextField voteTextField;//string of char that are split into array for counting
	private static JButton againBtn;// play again?
	private static JTextArea resultTextArea;// field for error messages and final results
	private String numVotes; // that accepts the noOfVotesField as a string
	private int checkedNum = 0; // a field that will hold the number of votes in integer form from numVotes
	private boolean validNumber = false; //used to show if we have a valid number
	private static int noOfVotes;// field to hold the integer value of noOfVotesTextField
	private JLabel promptLabel;// message output here to let user know what to enter
	private JLabel noteLabel;// note to inform user of qualification of the vote count
	public static String[] votesList;// array of the votes input
	private static int counterA;// counts the number of A's entered
	private static int counterB;// counts the number of B's entered
	private static int counterInvalid;// counts the number of entries that do not meet qualifications
	public static String voteList; // field used to accept the voteTextField entry
	private JLabel note3Label;// continuation of the noteLabel to give additional information
	
	//constructor that will display the fields from the GUI
	public voteGUI() {
		setSize(new Dimension(841, 647));
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		
		JLabel titleLabel = new JLabel("Who will win!!!");
		titleLabel.setForeground(Color.RED);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe Print", Font.BOLD, 45));
		titleLabel.setBounds(0, 0, 810, 78);
		getContentPane().add(titleLabel);
		
		JLabel questionLabel = new JLabel("How many votes will we be counting?");
		questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		questionLabel.setForeground(Color.BLUE);
		questionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		questionLabel.setBounds(10, 91, 489, 65);
		getContentPane().add(questionLabel);
		
		// field that accepts user input of the number of votes to be entered
		noOfVotesTextField = new JTextField();
		// event will send to verify that is is a number and is a valid number between 1 and 15
		// if it is not will output error message and reset field
		//if it does have a valid number it will output the prompt and qualification messages
		noOfVotesTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numVotes = noOfVotesTextField.getText();
				resultTextArea.setText("");
				checkEntry(numVotes);
				if (validNumber) {
					noOfVotes = checkedNum;//saves the integer value
					voteTextField.setBorder(new LineBorder(Color.RED, 2));
					promptLabel.setText("Enter list of " + noOfVotes + " votes (A or B).");
					noteLabel.setText("Any votes over " + noOfVotes + " will be counted as invalid");
					note3Label.setText("If under " + noOfVotes + " the remaining blanks will be counted as invalid");
					voteTextField.requestFocusInWindow();
				}else {// clears the fields to reset to blank
					noOfVotesTextField.setText("");
					noOfVotesTextField.requestFocusInWindow();
				}
			}
		});
		noOfVotesTextField.setForeground(Color.RED);
		noOfVotesTextField.setFont(new Font("Dialog", Font.BOLD, 20));
		noOfVotesTextField.setBounds(575, 104, 116, 42);
		getContentPane().add(noOfVotesTextField);
		noOfVotesTextField.setColumns(10);
		
		// prompts user how many and what type of letters to enter
		promptLabel = new JLabel("");
		promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		promptLabel.setForeground(Color.BLUE);
		promptLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		promptLabel.setBounds(33, 163, 702, 42);
		getContentPane().add(promptLabel);
		
		//field that the user will use to submit a string of votes (a/b)
		voteTextField = new JTextField();
		// event will call the method to process all votes for verification
		voteTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processVotes();
			}	
		});
		voteTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
		voteTextField.setBackground(Color.LIGHT_GRAY);
		voteTextField.setForeground(Color.RED);
		voteTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		voteTextField.setBounds(166, 218, 392, 42);
		getContentPane().add(voteTextField);
		voteTextField.setColumns(10);
		
		// output field to show how char after the entered number of vote will be handled
		noteLabel = new JLabel("");
		noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noteLabel.setForeground(Color.BLUE);
		noteLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		noteLabel.setBounds(33, 273, 702, 42);
		getContentPane().add(noteLabel);
		
		// field that will hold all of the error messages and final results
		resultTextArea = new JTextArea();
		resultTextArea.setBackground(Color.LIGHT_GRAY);
		resultTextArea.setWrapStyleWord(true);
		resultTextArea.setForeground(Color.BLACK);
		resultTextArea.setFont(new Font("Dialog", Font.BOLD, 20));
		resultTextArea.setBounds(134, 372, 557, 138);
		getContentPane().add(resultTextArea);
		
		// label that displays how entries under the entered amount will be handled
		note3Label = new JLabel("");
		note3Label.setHorizontalAlignment(SwingConstants.CENTER);
		note3Label.setForeground(Color.BLUE);
		note3Label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		note3Label.setBounds(23, 317, 758, 42);
		getContentPane().add(note3Label);
		
		againBtn = new JButton("Again?");
		//handles the processing to clear all fields to start over
		againBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noOfVotesTextField.setText("");
				voteTextField.setText("");
				resultTextArea.setText("");
				promptLabel.setText("");
				noteLabel.setText("");
				note3Label.setText("");
				voteTextField.setBorder(new EmptyBorder(0, 0, 0, 0));//makes the field disappear
				againBtn.setEnabled(false);//turning off the button until next game is completed
				getContentPane().repaint();
				noOfVotesTextField.requestFocusInWindow();
			}
		});
		againBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		againBtn.setEnabled(false);
		againBtn.setBounds(307, 530, 173, 25);
		getContentPane().add(againBtn);
		
		getContentPane().repaint();// used to make sure all of the fields will be shown
		
		noOfVotesTextField.requestFocusInWindow();// set the cursor to this field to begin
	}

	// method that is used to handle the voting string that the user entered
	protected void processVotes() {
		String voteList = voteTextField.getText();// get the string
		//split into an array, will stop at end of user input
		// even if it does not equal the number of votes that user indicated it should be
		votesList = voteList.split("", voteList.length());
		int noVotesEntered = voteList.length();// get the number of actually entered
		calcWinner(noVotesEntered);//process array to count number of a's, b's, and invalid
		displayWinner();//display results
	}

		// displays the winners and the final results of the count
		private static void displayWinner() {
			if (counterA == counterB ) {
				resultTextArea.setText("This was a tie! Everyone wins! \n");
				resultTextArea.append("Contestant A ended with " + counterA + " votes\n");
				resultTextArea.append("Contestant B ended with " + counterB + " votes\n");
				resultTextArea.append("There were " + counterInvalid + " invalid votes");
			} else if (counterA > counterB ) {
				resultTextArea.setText("Contestant A wins with "+ counterA + " votes\n");
				resultTextArea.append("Contestant B ended with " + counterB + " votes\n");
				resultTextArea.append("There were " + counterInvalid + " invalid votes");
			} else if (counterB > counterA) {
				resultTextArea.setText("Contestant B wins with "+ counterB + " votes\n");
				resultTextArea.append("Contestant A ended with " + counterA + " votes\n");
				resultTextArea.append("There were " + counterInvalid + " invalid votes");
			} else {
				resultTextArea.setText("Could not determine a winner");
			}
			resultTextArea.repaint();
			againBtn.setEnabled(true);//set this button so that they can use to run again
		}
	
		//goes through the array and counts depending on if a or b, 
		// and if not count as invalid
	private static void calcWinner(int noVotesEntered) {
		counterA = 0;
		counterB = 0;
		counterInvalid= 0;
		int upperLimit = 0;
		if (noVotesEntered < noOfVotes) {
			upperLimit = noVotesEntered;
		} else {
			upperLimit = noOfVotes;
		}
		for (int i = 0; i < upperLimit; i++) {
			//uses .toUpperCase() to make sure that an a or b will be checked as A or B
			if (votesList[i].toUpperCase().equals("A")) {
				counterA++;
			} else if (votesList[i].toUpperCase().equals("B")) {
				counterB++;
			} else {
				counterInvalid++;
			}
		}
		// this section is used to find out if the string is longer or shorter 
		// than the number indicated by the user
		int sum = counterA + counterB + counterInvalid;
		// if number counted is less than user indicated number will add additional
		// to account for the difference(blanks)
		if (sum < noVotesEntered) {
			counterInvalid = counterInvalid + (noVotesEntered - sum);
		// if number is equal to or more than user indicated will add difference
			//between the additional letters as invalid - will not check above past user
			//entered number
		} else if (sum >= noVotesEntered) {
			counterInvalid = counterInvalid + (noOfVotes-sum);
		}

	}
	// check if the entry is an integer and if it is, is it between 1 and 15
	private void checkEntry(String numVotes) {
			checkedNum = 0;
			try {
				// check the angle to see if integer
				checkedNum = Integer.parseInt(numVotes);//returns int value if number
				// if it is an integer that will check if between 1 and 15
				if (checkedNum > 0 && checkedNum <= 15) {
					validNumber = true;
				}
				else {// in not between 1 and 15 output error message
					resultTextArea.setText("You must enter a number between 1 and 15");
					validNumber = false;
					resultTextArea.repaint();
				}
			}
				catch(Exception e) { // what to do if is not an integer
					resultTextArea.setText("You must enter a number between 1 and 15");
					validNumber = false;
					resultTextArea.repaint();
				}
			//	return validNumber;
		}

	public static void main(String[] args) {
		new voteGUI();
	}
}
# countVotes
# countVotes
# countVotes
