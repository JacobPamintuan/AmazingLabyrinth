package amazingLabyrinth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MoveButtons extends JLabel {
	// fields

	public static Player currentPlayer = LabyrinthTest.playerArr[0];
	public JLabel currentPlayerlbl = new JLabel(currentPlayer.getPlayerImage());

	private JButton UP = new JButton(new ImageIcon("betterImages/btnUP.png"));
	private JButton DOWN = new JButton(new ImageIcon("betterImages/btnDOWN.png"));
	private JButton LEFT = new JButton(new ImageIcon("betterImages/btnLEFT.png"));
	private JButton RIGHT = new JButton(new ImageIcon("betterImages/btnRIGHT.png"));

	private JButton finishMove = new JButton("Done");
	private JLabel playerLbl = new JLabel("     Player:");

	// Constructor
	public MoveButtons() {
		setSize(400, 192);
		setLayout(null);

		disableAll();

		addButtons();

		setVisible(true);
	}

	// Disables all move buttons
	public void disableAll() {
		UP.setEnabled(false);
		DOWN.setEnabled(false);
		LEFT.setEnabled(false);
		RIGHT.setEnabled(false);
		finishMove.setEnabled(false);
	}

	// Enables all move buttons
	public void enableAll() {
		UP.setEnabled(false);
		DOWN.setEnabled(false);
		LEFT.setEnabled(false);
		RIGHT.setEnabled(false);
		finishMove.setEnabled(true);

		validMoves(currentPlayer);
	}

	// Checks when the player clicks "Done" whether or not the player ended the turn
	// on one of their treasures
	// If true, sets treasure to found
	// Replaces image of card with new image indicating card has been found
	private void isPlayerTreasure(int row, int col) {

		for (int i = 0; i < LabyrinthGUI.card.numCards; i++) {

			if (currentPlayer.getPlayerColor().equals("Red")) {
				if (Board.tileMatrix[row][col].getItem().equals(CardLayout.redCards[i].getItem())
						&& !CardLayout.redCards[i].isFound()) {
					currentPlayer.setScore(currentPlayer.getScore() + 1);
					CardLayout.redCards[i].setFound(true); // Sets treasure to found
					CardLayout.redCards[i].setIcon(new ImageIcon("betterImages/Found.png")); // Replaces image with
																								// indication that card
																								// has been found
				}
			} else if (currentPlayer.getPlayerColor().equals("Blue")) {
				if (Board.tileMatrix[row][col].getItem().equals(CardLayout.blueCards[i].getItem())
						&& !CardLayout.blueCards[i].isFound()) {
					currentPlayer.setScore(currentPlayer.getScore() + 1);
					CardLayout.blueCards[i].setFound(true);
					CardLayout.blueCards[i].setIcon(new ImageIcon("betterImages/Found.png"));
				}
			} else if (currentPlayer.getPlayerColor().equals("Green")) {
				if (Board.tileMatrix[row][col].getItem().equals(CardLayout.greenCards[i].getItem())
						&& !CardLayout.greenCards[i].isFound()) {
					currentPlayer.setScore(currentPlayer.getScore() + 1);
					CardLayout.greenCards[i].setFound(true);
					CardLayout.greenCards[i].setIcon(new ImageIcon("betterImages/Found.png"));
				}
			} else if (currentPlayer.getPlayerColor().equals("Yellow"))
				if (Board.tileMatrix[row][col].getItem().equals(CardLayout.yellowCards[i].getItem())
						&& !CardLayout.yellowCards[i].isFound()) {
					currentPlayer.setScore(currentPlayer.getScore() + 1);
					CardLayout.yellowCards[i].setFound(true);
					CardLayout.yellowCards[i].setIcon(new ImageIcon("betterImages/Found.png"));
				}

		}

	}

	// Game over, announces which player won
	public void GAMEOVER() {
		ButtonBoard.disableAll();
		LabyrinthGUI.moveButtons.disableAll();

		// Displays message and trophy color based on winner

		if (LabyrinthTest.winner.getPlayerColor().equals("Red")) {
			JOptionPane.showMessageDialog(LabyrinthTest.app,
					"CONGRATULATIONS! " + LabyrinthTest.winner.getPlayerColor() + " player wins.", "GAME ENDS",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("betterImages/Red Trophy.png"));
		} else if (LabyrinthTest.winner.getPlayerColor().equals("Blue")) {
			JOptionPane.showMessageDialog(LabyrinthTest.app,
					"CONGRATULATIONS! " + LabyrinthTest.winner.getPlayerColor() + " player wins.", "GAME ENDS",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("betterImages/Blue Trophy.png"));
		} else if (LabyrinthTest.winner.getPlayerColor().equals("Green")) {
			JOptionPane.showMessageDialog(LabyrinthTest.app,
					"CONGRATULATIONS! " + LabyrinthTest.winner.getPlayerColor() + " player wins.", "GAME ENDS",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("betterImages/Green Trophy.png"));
		} else if (LabyrinthTest.winner.getPlayerColor().equals("Yellow")) {
			JOptionPane.showMessageDialog(LabyrinthTest.app,
					"CONGRATULATIONS! " + LabyrinthTest.winner.getPlayerColor() + " player wins.", "GAME ENDS",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("betterImages/Yellow Trophy.png"));
		}
		System.exit(0);
	}

	// Checks if player has won
	public boolean isGameOver() {
		for (int i = 0; i < 4; i++) {

			if (LabyrinthTest.playerArr[i].getScore() == LabyrinthGUI.card.numCards) {
				LabyrinthTest.winner = LabyrinthTest.playerArr[i];
				return true;
			}

		}

		return false;
	}

	// Adds buttons during setup
	private void addButtons() {

		// Checks valid moves
		validMoves(currentPlayer);

		// Adds current player label and image
		currentPlayerlbl.setBounds(0, 10, 96, 80);
		add(currentPlayerlbl);
		playerLbl.setBounds(10, 0, 80, 20);
		add(playerLbl);

		finishMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check if player finished turn on treasure
				isPlayerTreasure(currentPlayer.getRow(), currentPlayer.getCol());

				// Display score
				LabyrinthGUI.card.redScore = (LabyrinthTest.playerArr[0].getScore());
				LabyrinthGUI.card.lblRedPlayer.setText("<html>Red Player<br>Score: " + LabyrinthGUI.card.redScore);

				LabyrinthGUI.card.blueScore = (LabyrinthTest.playerArr[1].getScore());
				LabyrinthGUI.card.lblBluePlayer.setText("<html>Blue Player<br>Score: " + LabyrinthGUI.card.blueScore);

				LabyrinthGUI.card.greenScore = (LabyrinthTest.playerArr[2].getScore());
				LabyrinthGUI.card.lblGreenPlayer
						.setText("<html>Green Player<br>Score: " + LabyrinthGUI.card.greenScore);

				LabyrinthGUI.card.yellowScore = (LabyrinthTest.playerArr[3].getScore());
				LabyrinthGUI.card.lblYellowPlayer
						.setText("<html>Yellow Player<br>Score: " + LabyrinthGUI.card.yellowScore);

				// Checks if a player has won/game over
				if (isGameOver())
					GAMEOVER();

				// Switches player
				if (currentPlayer.equals(LabyrinthTest.playerArr[0]))
					currentPlayer = LabyrinthTest.playerArr[1];
				else if (currentPlayer.equals(LabyrinthTest.playerArr[1]))
					currentPlayer = LabyrinthTest.playerArr[2];
				else if (currentPlayer.equals(LabyrinthTest.playerArr[2]))
					currentPlayer = LabyrinthTest.playerArr[3];
				else if (currentPlayer.equals(LabyrinthTest.playerArr[3]))
					currentPlayer = LabyrinthTest.playerArr[0];

				// Updates the player lbl to new current player
				currentPlayerlbl.setIcon(currentPlayer.getPlayerImage());

				// Checks valid moves
				validMoves(currentPlayer);

				// Disables opposite insert button based on where the previous player inserted a
				// tile
				ButtonBoard.disableOpposite(LabyrinthGUI.buttonBoard.oppRow, LabyrinthGUI.buttonBoard.oppCol);

				// Updates GUI
				LabyrinthTest.app.revalidate();
				LabyrinthTest.app.repaint();

			}
		});
		// Adds button to GUI
		finishMove.setBounds(298, 136, 80, 20);
		add(finishMove);

		// UP Button
		UP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removes all players from GUI
				LabyrinthGUI.playerBoard.delete();

				// Gets current row and column
				int col = currentPlayer.getCol();
				int previousRow = currentPlayer.getRow();

				// Sets new row
				int newRow = previousRow - 1;

				// Move player up
				PlayerBoard.playerMatrix[newRow][col] = currentPlayer;

				// Updates player row
				currentPlayer.setRow(newRow);

				// Sets previous space to null player
				PlayerBoard.playerMatrix[previousRow][col] = new Player("null", -1, -1, -1);

				// Refresh player board
				LabyrinthGUI.playerBoard.refresh();

				// Checks valid moves
				validMoves(currentPlayer);

				// Updates GUI
				LabyrinthTest.app.revalidate();
				LabyrinthTest.app.repaint();

			}
		});
		// Adds button to GUI
		UP.setBounds(96, 0, 96, 96);
		add(UP);

		// Down Button
		DOWN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removes all players from GUI
				LabyrinthGUI.playerBoard.delete();

				// Gets current row and column
				int col = currentPlayer.getCol();
				int previousRow = currentPlayer.getRow();
				
				// Sets new row
				int newRow = previousRow + 1;

				// Move player down
				PlayerBoard.playerMatrix[newRow][col] = currentPlayer;
				
				// Updates player row
				currentPlayer.setRow(newRow);

				// Sets previous space to null player
				PlayerBoard.playerMatrix[previousRow][col] = new Player("null", -1, -1, -1);
				
				// Refresh player board
				LabyrinthGUI.playerBoard.refresh();

				// Checks valid moves
				validMoves(currentPlayer);

				// Updates GUI
				LabyrinthTest.app.revalidate();
				LabyrinthTest.app.repaint();

			}
		});	
		// Adds button to GUI
		DOWN.setBounds(96, 96, 96, 96);
		add(DOWN);

		// Left button
		LEFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Removes all players from GUI
				LabyrinthGUI.playerBoard.delete();

				// Gets current row and column
				int row = currentPlayer.getRow();
				int previousCol = currentPlayer.getCol();
				
				// Sets new column
				int newCol = previousCol - 1;

				// Move player left
				PlayerBoard.playerMatrix[row][newCol] = currentPlayer;
				
				// Updates player column 
				currentPlayer.setCol(newCol);
				
				// Sets previous space to null player
				PlayerBoard.playerMatrix[row][previousCol] = new Player("null", -1, -1, -1);

				// Refresh player board
				LabyrinthGUI.playerBoard.refresh();

				// Checks valid moves
				validMoves(currentPlayer);

				// Updates GUI
				LabyrinthTest.app.revalidate();
				LabyrinthTest.app.repaint();

			}
		});
		// Add button to GUI
		LEFT.setBounds(0, 96, 96, 96);
		add(LEFT);

		// Right button
		RIGHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Removes all players from GUI
				LabyrinthGUI.playerBoard.delete();

				// Gets current row and column
				int row = currentPlayer.getRow();
				int previousCol = currentPlayer.getCol();
				
				// Sets new column
				int newCol = previousCol + 1;
				
				// Move player right
				PlayerBoard.playerMatrix[row][newCol] = currentPlayer;
				
				// Updates player column 
				currentPlayer.setCol(newCol);
				
				// Sets previous space to null player
				PlayerBoard.playerMatrix[row][previousCol] = new Player("null", -1, -1, -1);

				// Refresh player board
				LabyrinthGUI.playerBoard.refresh();

				// Checks valid moves
				validMoves(currentPlayer);

				// Updates GUI
				LabyrinthTest.app.revalidate();
				LabyrinthTest.app.repaint();

			}
		});

		RIGHT.setBounds(192, 96, 96, 96);
		add(RIGHT);

	}

	// Checks which direction the player can move, disables invalid moves
	public void validMoves(Player currentPlayer) {

		Tile tileAbove, tileBelow, tileRight, tileLeft;
		int row = currentPlayer.getRow();
		int col = currentPlayer.getCol();
		String type = Board.tileMatrix[row][col].getType();
		int orientation = Board.tileMatrix[row][col].getOrientation();

		// Checks if player is against a wall of the board (top row, bottom row, left column, right column)
		// Sets values of tileAbove, tileBelow... correspondingly 
		// Disables buttons correspondingly
		if (currentPlayer.getRow() == 0) {
			tileAbove = null;
			UP.setEnabled(false);
		} else {
			tileAbove = Board.tileMatrix[row - 1][col];
			UP.setEnabled(true);
		}

		if (currentPlayer.getRow() == 6) {
			tileBelow = null;
			DOWN.setEnabled(false);
		} else {
			tileBelow = Board.tileMatrix[row + 1][col];
			DOWN.setEnabled(true);
		}

		if (currentPlayer.getCol() == 0) {
			tileLeft = null;
			LEFT.setEnabled(false);
		} else {
			tileLeft = Board.tileMatrix[row][col - 1];
			LEFT.setEnabled(true);
		}

		if (currentPlayer.getCol() == 6) {
			tileRight = null;
			RIGHT.setEnabled(false);
		} else {
			tileRight = Board.tileMatrix[row][col + 1];
			RIGHT.setEnabled(true);
		}

		// Based on type of tile and orientation, sets values of tileAbove, tileBelow... as null 
		// In this case, setting to null means there is no tile above/below/right/left, even if there is, 
		// but it is set to null so that the player cannot move in that direction
		if (type.equals("L")) {
			if (orientation == 0) {
				tileBelow = null;
				tileLeft = null;

			} else if (orientation == 1) {
				tileAbove = null;
				tileLeft = null;
			} else if (orientation == 2) {
				tileAbove = null;
				tileRight = null;
			} else {
				tileBelow = null;
				tileRight = null;
			}

		} else if (type.equals("I")) {
			if (orientation == 0 || orientation == 2) {
				tileRight = null;
				tileLeft = null;
			} else {
				tileAbove = null;
				tileBelow = null;
			}
		} else if (type.contentEquals("T")) {
			if (orientation == 0)
				tileAbove = null;
			else if (orientation == 1)
				tileRight = null;
			else if (orientation == 2)
				tileBelow = null;
			else
				tileLeft = null;
		}

		// Checks of the orientation of the tile and the tiles around it match up
		// Enables and disables move buttons correspondingly 
		if (tileAbove != null) {
			if (tileAbove.getType().equals("I") && (tileAbove.getOrientation() == 0 || tileAbove.getOrientation() == 2))
				enableButton(UP);
			else if (tileAbove.getType().equals("L")
					&& (tileAbove.getOrientation() == 1 || tileAbove.getOrientation() == 2))
				enableButton(UP);
			else if (tileAbove.getType().equals("T") && tileAbove.getOrientation() != 2)
				enableButton(UP);
			else
				disableButton(UP);
		} else
			disableButton(UP);

		if (tileBelow != null) {
			if ((tileBelow.getType().equals("I")
					&& (tileBelow.getOrientation() == 0 || tileBelow.getOrientation() == 2)))
				enableButton(DOWN);
			else if (tileBelow.getType().equals("T") && tileBelow.getOrientation() != 0)
				enableButton(DOWN);
			else if ((tileBelow.getType().equals("L")
					&& (tileBelow.getOrientation() == 0 || tileBelow.getOrientation() == 3)))
				enableButton(DOWN);
			else
				disableButton(DOWN);
		} else
			disableButton(DOWN);

		if (tileRight != null) {
			if ((tileRight.getType().equals("I")
					&& (tileRight.getOrientation() == 1 || tileRight.getOrientation() == 3)))
				enableButton(RIGHT);
			else if (tileRight.getType().equals("T") && tileRight.getOrientation() != 3)
				enableButton(RIGHT);
			else if ((tileRight.getType().equals("L")
					&& (tileRight.getOrientation() == 2 || tileRight.getOrientation() == 3)))
				enableButton(RIGHT);
			else
				disableButton(RIGHT);
		} else
			disableButton(RIGHT);

		if (tileLeft != null) {
			if ((tileLeft.getType().equals("I") && (tileLeft.getOrientation() == 1 || tileLeft.getOrientation() == 3)))
				enableButton(LEFT);
			else if (tileLeft.getType().equals("T") && tileLeft.getOrientation() != 1)
				enableButton(LEFT);
			else if ((tileLeft.getType().equals("L")
					&& (tileLeft.getOrientation() == 0 || tileLeft.getOrientation() == 1)))
				enableButton(LEFT);
			else
				disableButton(LEFT);
		} else
			disableButton(LEFT);

	}

	// Disables specified button
	public static void disableButton(JButton button) {
		button.setEnabled(false);
	}

	// Enables specified button
	public static void enableButton(JButton button) {
		button.setEnabled(true);
	}

}
