package amazingLabyrinth;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JLabel;

public class CardLayout extends JLabel {
	
	// fields
	public int numCards;
	
	public static Card[] redCards;
	public static Card[] blueCards;
	public static Card[] greenCards;
	public static Card[] yellowCards;
	
	public int redScore;
	public int blueScore;
	public int greenScore;
	public int yellowScore;

	public JLabel lblRedPlayer = new JLabel("<html>Red Player<br>Score: " + redScore);
	public JLabel lblBluePlayer = new JLabel("<html>Blue Player<br>Score: " + blueScore);
	public JLabel lblGreenPlayer = new JLabel("<html>Green Player<br>Score: " + greenScore);
	public JLabel lblYellowPlayer = new JLabel("<html>Yellow Player<br>Score: " + yellowScore);
	
	private static Card[][] cardMatrix;

	// Default constructor, initializes 4 cards
	public CardLayout() {

		// Sets label colors (did not color yellow for visibility)
		lblRedPlayer.setForeground(Color.RED);
		lblBluePlayer.setForeground(Color.BLUE);
		lblGreenPlayer.setForeground(new Color(0, 128, 0));

		this.numCards = 4;
		setSize(4, 6);
		setLayout(new GridLayout(4, 4));
		cardMatrix = new Card[4][4];

		addCards();

		setVisible(true);

	}

	// Overloaded constructor
	// Initializes the game with number of cards chosen (numCards parameter)
	public CardLayout(int numCards) {
		
		// Sets label colors (did not color yellow for visibility)
		lblRedPlayer.setForeground(Color.RED);
		lblBluePlayer.setForeground(Color.BLUE);
		lblGreenPlayer.setForeground(new Color(0, 128, 0));

		// Sets numCards
		this.numCards = numCards;
		
		// Sets gui size and layout
		setSize((numCards + 1) * 96, 528);
		setLayout(new GridLayout(4, numCards + 1));
		
		// Initializes cardMatrix
		cardMatrix = new Card[4][numCards];

		// Adds cards
		addCards();

		// Assign each row to corresponding player
		redCards = cardMatrix[0];
		blueCards = cardMatrix[1];
		greenCards = cardMatrix[2];
		yellowCards = cardMatrix[3];

		setVisible(true);
	}

	// Adds cards during setup
	private void addCards() {

		// Index of cardArray
		int index = 0;

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < numCards; j++) {
				// Assigns players cards
				cardMatrix[i][j] = LabyrinthTest.cardArray.get(index);
				cardMatrix[i][j].setRow(i);
				cardMatrix[i][j].setCol(j);
				index++;

			}
		}
		// Add cards to GUI
		int row = 1;
		for (Card[] cardRow : cardMatrix) {
			addLabels(row);
			row++;
			for (Card currCard : cardRow) {
				add(currCard);
			}
		}
	}

	// Labels card rows
	public void addLabels(int row) {
		if (row == 1) {
			add(lblRedPlayer);
		} else if (row == 2) {
			add(lblBluePlayer);
		} else if (row == 3) {
			add(lblGreenPlayer);
		} else if (row == 4) {
			add(lblYellowPlayer);
		}

	}

}
