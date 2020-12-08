package amazingLabyrinth;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Board extends JLabel {
	// fields
	public static Tile[][] tileMatrix = new Tile[7][7];
	public static Player currentPlayer = LabyrinthTest.playerArr[0];

	public static int[][] playerXY = new int[4][2];

	// Constructor
	public Board() {
		setSize(672, 672);
		setLayout(new GridLayout(7, 7));
		setIcon(new ImageIcon("betterImages/GameBoard.png"));

		addTiles();
		setVisible(true);

	}

	// Assigns random values for row and column of movable tiles - randomized the
	// board
	// Only called during setup
	private void addTiles() {
		// Places the non-movable tiles in their respective positions
		for (Tile currentTile : LabyrinthTest.tileArray) {
			if (currentTile.isMovable() == false) {
				tileMatrix[currentTile.getRow()][currentTile.getColumn()] = currentTile;
			}
		}

		// Randomizes the placement of the movable tiles
		for (Tile currentTile : LabyrinthTest.tileArray) {
			if (currentTile.isMovable() == true && currentTile != LabyrinthGUI.freeTile) {

				// Infinite loop that only breaks when the random numbers generated assign the
				// tile a row and column value that has not already been taken
				while (true) {
					int randRow = (int) (Math.random() * 7);
					int randCol = (int) (Math.random() * 7);

					if (tileMatrix[randRow][randCol] == null) {
						currentTile.setRow(randRow);
						currentTile.setColumn(randCol);
						tileMatrix[randRow][randCol] = currentTile;
						break;
					}
				}

			}
		}

		// Add tiles to the GUI board
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				add(tileMatrix[i][j]);
			}

	}

	// removes all of the old tiles
	private void delete() {
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				remove(tileMatrix[i][j]);
	}

	// Adds back tiles with updated row/column values (whenever shifting)
	private void refresh() {
		// Places the non-movable tiles in their respective positions
		for (Tile currentTile : LabyrinthTest.tileArray) {
			if (currentTile.isMovable() == false) {
				tileMatrix[currentTile.getRow()][currentTile.getColumn()] = currentTile;
			}
		}
		// Adds tiles to the GUI board
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {

				add(tileMatrix[i][j]);
			}

	}

	// Shifts tiles up based on col argument and inserts current free tile
	// Tile return type, returns new free tile
	public Tile shiftUp(int col, Tile currentFreeTile) {
		// Deletes old tiles from the board
		delete();

		// Assigns the tile being pushed off the board as the new free tile
		Tile newFreeTile = tileMatrix[0][col];

		// Changes the rows and columns of the new free tile to -1
		newFreeTile.setRow(-1);
		newFreeTile.setColumn(-1);

		// Shifts tiles up
		for (int i = 0; i < 6; i++)
			tileMatrix[i][col] = tileMatrix[i + 1][col];
		tileMatrix[6][col] = currentFreeTile;

		// Creates new array for column
		Tile[] tileMatrixCol = new Tile[7];
		// Populates tileMatrixCol array
		for (int i = 0; i < 7; i++)
			tileMatrixCol[i] = tileMatrix[i][col];

		// Updates new row values of the column
		setTileMatrixCol(tileMatrixCol, col, -1);

		// Deletes old players off board
		LabyrinthGUI.playerBoard.delete();

		// Check if players exist on same tile
		String direction = "up";
		checkXY(direction, col);

		// Shifts players up
		Player lastTile = PlayerBoard.playerMatrix[0][col];

		for (int i = 0; i < 6; i++) {
			PlayerBoard.playerMatrix[i][col] = PlayerBoard.playerMatrix[i + 1][col];
			PlayerBoard.playerMatrix[i][col].setRow(PlayerBoard.playerMatrix[i][col].getRow() - 1);

		}

		lastTile.setRow(6);

		PlayerBoard.playerMatrix[6][col] = lastTile;

		// Adds back updated players to GUI
		LabyrinthGUI.playerBoard.refresh();

		// Checks valid moves for player
		LabyrinthGUI.moveButtons.validMoves(MoveButtons.currentPlayer);

		// Refreshes GUI
		LabyrinthTest.app.revalidate();
		LabyrinthTest.app.repaint();

		return newFreeTile;
	}

	// Shifts tiles down based on col argument and inserts current free tile
	// Tile return type, returns new free tile
	public Tile shiftDown(int col, Tile currentFreeTile) {
		// Deletes old tiles from the board
		delete();

		// Assigns the tile being pushed off the board as the new free tile
		Tile newFreeTile = tileMatrix[6][col];

		// Changes the rows and columns of the new free tile to -1
		newFreeTile.setRow(-1);
		newFreeTile.setColumn(-1);

		for (int i = 6; i > 0; i--)
			tileMatrix[i][col] = tileMatrix[i - 1][col];
		tileMatrix[0][col] = currentFreeTile;
		// Creates new array for column
		Tile[] tileMatrixCol = new Tile[7];
		// Populates tileMatrixCol array
		for (int i = 0; i < 7; i++)
			tileMatrixCol[i] = tileMatrix[i][col];
		// Updates new row values of the column
		setTileMatrixCol(tileMatrixCol, col, 1);

		// Deletes old players off board
		LabyrinthGUI.playerBoard.delete();

		// Check if players exist on same tile
		String direction = "down";
		checkXY(direction, col);

		// Shift players down
		Player lastTile = PlayerBoard.playerMatrix[6][col];

		for (int i = 6; i > 0; i--) {
			PlayerBoard.playerMatrix[i][col] = PlayerBoard.playerMatrix[i - 1][col];
			PlayerBoard.playerMatrix[i][col].setRow(PlayerBoard.playerMatrix[i][col].getRow() + 1);

		}

		lastTile.setRow(0);

		PlayerBoard.playerMatrix[0][col] = lastTile;

		// Adds back updated players to GUI
		LabyrinthGUI.playerBoard.refresh();

		// Checks valid moves for player
		LabyrinthGUI.moveButtons.validMoves(MoveButtons.currentPlayer);

		// Refreshes GUI
		LabyrinthTest.app.revalidate();
		LabyrinthTest.app.repaint();

		return newFreeTile;

	}

	// Shifts tiles left based on row argument and inserts current free tile
	// Tile return type, returns new free tile
	public Tile shiftLeft(int row, Tile currentFreeTile) {
		// Deletes old tiles from the board
		delete();

		// Assigns the tile being pushed off the board as the new free tile
		Tile newFreeTile = tileMatrix[row][0];

		// Changes the rows and columns of the new free tile to -1
		newFreeTile.setRow(-1);
		newFreeTile.setColumn(-1);

		// Shifts the tiles left
		for (int i = 0; i < 6; i++)
			tileMatrix[row][i] = tileMatrix[row][i + 1];
		// Places old free tile as part of the row
		tileMatrix[row][6] = currentFreeTile;

		// Updates new column values of the row
		setTileMatrixRow(tileMatrix[row], row, -1);

		// Deletes old players off board
		LabyrinthGUI.playerBoard.delete();

		// Check if players exist on same tile
		String direction = "left";
		checkXY(direction, row);

		Player lastTile = PlayerBoard.playerMatrix[row][0];

		// Shifts player left and updates column value
		for (int i = 0; i < 6; i++) {
			PlayerBoard.playerMatrix[row][i] = PlayerBoard.playerMatrix[row][i + 1];
			PlayerBoard.playerMatrix[row][i].setCol(PlayerBoard.playerMatrix[row][i].getCol() - 1);
		}

		lastTile.setCol(6);

		PlayerBoard.playerMatrix[row][6] = lastTile;

		// Adds back updated players to GUI
		LabyrinthGUI.playerBoard.refresh();

		// Checks valid moves for player
		LabyrinthGUI.moveButtons.validMoves(MoveButtons.currentPlayer);

		// Refreshes GUI
		LabyrinthTest.app.revalidate();
		LabyrinthTest.app.repaint();

		return newFreeTile;
	}

	// Shifts tiles right based on row argument and inserts current free tile
	// Tile return type, returns new free tile
	public Tile shiftRight(int row, Tile currentFreeTile) {
		// Deletes old tiles from the board
		delete();

		// Assigns the tile being pushed off the board as the new free tile
		Tile newFreeTile = tileMatrix[row][6];
		
		// Changes the rows and columns of the new free tile to -1
		newFreeTile.setRow(-1);
		newFreeTile.setColumn(-1);

		// Shifts the tiles right
		for (int i = 6; i > 0; i--) {
			tileMatrix[row][i] = tileMatrix[row][i - 1];
		}

		// Places old free tile as part of the row
		tileMatrix[row][0] = currentFreeTile;

		// Updates new column values of the row
		setTileMatrixRow(tileMatrix[row], row, 1);
		
		// Deletes old players off board
		LabyrinthGUI.playerBoard.delete();

		// Check if players exist on same tile
		String direction = "right";
		checkXY(direction, row);

		Player lastTile = PlayerBoard.playerMatrix[row][6];

		// Shifts player right and updates column value
		for (int i = 6; i > 0; i--) {
			PlayerBoard.playerMatrix[row][i] = PlayerBoard.playerMatrix[row][i - 1];
			PlayerBoard.playerMatrix[row][i].setCol(PlayerBoard.playerMatrix[row][i].getCol() + 1);

		}

		lastTile.setCol(0);

		PlayerBoard.playerMatrix[row][0] = lastTile;

		// Adds back updated players to GUI
		LabyrinthGUI.playerBoard.refresh();

		// Checks valid moves for player
		LabyrinthGUI.moveButtons.validMoves(MoveButtons.currentPlayer);

		// Refreshes GUI
		LabyrinthTest.app.revalidate();
		LabyrinthTest.app.repaint();

		return newFreeTile;
	}

	// Updates columns of tiles based on "shift" argument
	// Calls "refresh()" method
	public void setTileMatrixRow(Tile[] tileRow, int row, int shift) { // 1 - shift right || -1 - shift left

		// Updates columns
		for (int i = 0; i < 7; i++)
			tileRow[i].setColumn(tileRow[i].getColumn() + shift);

		// Setting rows and column of previous free tile
		if (shift == 1) {
			tileRow[0].setColumn(0);
			tileRow[0].setRow(row);
		} else if (shift == -1) {
			tileRow[6].setColumn(6);
			tileRow[6].setRow(row);
		}

		// Re-initialize row with updated values
		tileMatrix[row] = tileRow;

		// Adds updated tiles back to GUI
		refresh();

	}

	// Updates rows of tiles based on "shift" argument
	// Calls "refresh()" method
	public void setTileMatrixCol(Tile[] tileCol, int col, int shift) { // 1 - Shift down || -1 - shift up

		// Updates rows
		for (int i = 0; i < 7; i++)
			tileCol[i].setRow(tileCol[i].getRow() + shift);

		// Setting rows and column of previous free tile
		if (shift == 1) {
			tileCol[0].setRow(0);
			tileCol[0].setColumn(col);
		} else if (shift == -1) {
			tileCol[6].setRow(6);
			tileCol[6].setColumn(col);
		}

		// Re-initialize column with updated values
		for (int i = 0; i < 6; i++)
			tileMatrix[i][col] = tileCol[i];
		
		// Adds updated tiles back to GUI
		refresh();
	}

	// Checks if there is more than one player in a tile and moves accordingly if
	// user shifts row/column where multiple players exist
	public void checkXY(String direction, int ColRow) {
		for (int x = 0; x < 4; x++) {
			currentPlayer = LabyrinthTest.playerArr[x];

			for (int y = 0; y < 2; y++) {
				if (y == 0)
					playerXY[x][y] = currentPlayer.getRow();
				else
					playerXY[x][y] = currentPlayer.getCol();
			}
		}

		int preX = playerXY[0][0];
		int preY = playerXY[0][1];
		int y = 0;

		for (int x = 1; x < 4; x++) {
			if (preX == playerXY[x][y] && preY == playerXY[x][y + 1]) {
				currentPlayer = LabyrinthTest.playerArr[0];
				if (direction == "left" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getCol() == 0) {
						currentPlayer.setCol(6);
					} else {
						currentPlayer.setCol((playerXY[x][y + 1] - 1));
					}
				} else if (direction == "right" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getCol() == 6) {
						currentPlayer.setCol(0);
					} else {
						currentPlayer.setCol(playerXY[x][y + 1] + 1);
					}

				} else if (direction == "up" && playerXY[x][y + 1] == ColRow) {
					if (currentPlayer.getRow() == 0) {
						currentPlayer.setRow(6);
					} else {
						currentPlayer.setRow(playerXY[x][y] - 1);
					}
				} else if (direction == "down" && playerXY[x][y + 1] == ColRow) {
					if (currentPlayer.getRow() == 6) {
						currentPlayer.setRow(0);
					} else {
						currentPlayer.setRow(playerXY[x][y] + 1);
					}
				}
			}
		}

		int preX2 = playerXY[1][0];
		int preY2 = playerXY[1][1];
		for (int x = 2; x < 4; x++) {
			if (preX2 == playerXY[x][y] && preY2 == playerXY[x][y + 1]) {
				currentPlayer = LabyrinthTest.playerArr[1];
				if (direction == "left" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getCol() == 0) {
						currentPlayer.setCol(6);
					} else {
						currentPlayer.setCol((playerXY[x][y + 1] - 1));
					}

				} else if (direction == "right" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getCol() == 6) {
						currentPlayer.setCol(0);
					} else {
						currentPlayer.setCol(playerXY[x][y + 1] + 1);
					}

				} else if (direction == "up" && playerXY[x][y + 1] == ColRow) {
					if (currentPlayer.getRow() == 0) {
						currentPlayer.setRow(6);
					} else {
						currentPlayer.setRow(playerXY[x][y] - 1);
					}
				} else if (direction == "down" && playerXY[x][y + 1] == ColRow) {
					if (currentPlayer.getRow() == 6) {
						currentPlayer.setRow(0);
					} else {
						currentPlayer.setRow(playerXY[x][y] + 1);
					}
				}
			}
		}

		int preX3 = playerXY[2][0];
		int preY3 = playerXY[2][1];
		for (int x = 3; x < 4; x++) {
			if (preX3 == playerXY[x][y] && preY3 == playerXY[x][y + 1]) {
				currentPlayer = LabyrinthTest.playerArr[2];
				if (direction == "left" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getCol() == 0) {
						currentPlayer.setCol(6);
					} else {
						currentPlayer.setCol((playerXY[x][y + 1] - 1));
					}
				} else if (direction == "right" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getCol() == 6) {
						currentPlayer.setCol(0);
					} else {
						currentPlayer.setCol(playerXY[x][y + 1] + 1);
					}
				} else if (direction == "up" && playerXY[x][y + 1] == ColRow) {
					if (currentPlayer.getRow() == 0) {
						currentPlayer.setRow(6);
					} else {
						currentPlayer.setRow(playerXY[x][y] - 1);
					}

				} else if (direction == "down" && playerXY[x][y] == ColRow) {
					if (currentPlayer.getRow() == 6) {
						currentPlayer.setRow(0);
					} else {
						currentPlayer.setRow(playerXY[x][y] + 1);
					}
				}
			}
		}
	}
}
