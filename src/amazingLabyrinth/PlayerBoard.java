package amazingLabyrinth;

import java.awt.GridLayout;
import javax.swing.JLabel;

public class PlayerBoard extends JLabel { 
	// Fields

	public static Player[][] playerMatrix = new Player[7][7];

	// Constructor - initializes PlayerBoard
	public PlayerBoard() {
		setSize(672, 672);
		setLayout(new GridLayout(7, 7));

		addPlayers();
		setVisible(true);
	}

	// Adds players and creates "null" players as place holders
	private void addPlayers() {

		for (Player currentPlayer : LabyrinthTest.playerArr) {
			playerMatrix[currentPlayer.getRow()][currentPlayer.getCol()] = currentPlayer;
		}
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				if (playerMatrix[i][j] == (null))
					playerMatrix[i][j] = new Player("null", i, j, -1);
			}

		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				add(playerMatrix[i][j]);
			}

	}

	// Removes all the players from the board
	public void delete() {
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				remove(playerMatrix[i][j]);
	}

	// Adds updated players with updated positions back to the board
	public void refresh() {
		for (Player currentPlayer : LabyrinthTest.playerArr) {
			playerMatrix[currentPlayer.getRow()][currentPlayer.getCol()] = currentPlayer;
		}

		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				if (playerMatrix[i][j] != (null))
					add(playerMatrix[i][j]);
				else
					add(new Player("", -1, -1, -1));
			}

	}

}
