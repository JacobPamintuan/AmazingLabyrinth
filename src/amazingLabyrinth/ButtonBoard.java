package amazingLabyrinth;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonBoard extends JLabel {
	// fields

	private static JButton[][] button = new JButton[4][3];

	private JButton rotateRightBtn = new JButton(new ImageIcon("betterImages/Rotate Right.png"));
	private JButton rotateLeftBtn = new JButton(new ImageIcon("betterImages/Rotate Left.png"));
	private RotateButton rotate = new RotateButton();

	public int oppRow, oppCol;
//	0-top
//	1-right
//	2-bottom
//	3-left

	// Constructor
	public ButtonBoard(Tile freeTile) {
		setSize(864, 864);
		setLayout(null);

		addButtons(freeTile);
		disableAll();
		addFreeTile(freeTile);
		setVisible(true);
	}

	// Disables all insert (yellow) buttons
	public static void disableAll() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
				button[i][j].setEnabled(false);

	}

	// Enables all insert (yellow) buttons
	public static void enableAll() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 3; j++)
				button[i][j].setEnabled(true);
	}

	// Enables all, then Disables opposite insert (yellow) button based on the one
	// clicked
	public static void disableOpposite(int row, int col) {
		enableAll();
		button[row][col].setEnabled(false);

	}

	// Adds free tile to the GUI
	private void addFreeTile(Tile freeTile) {
		freeTile.setBounds(0, 0, 96, 96);
		add(freeTile);
	}

	// Adds all insert buttons to GUI
	private void addButtons(Tile freeTile) {

		// For loop for all the top row buttons (Shift down buttons)
		for (int i = 0; i < 3; i++) {
			button[0][i] = new JButton(new ImageIcon("betterImages/btnTopRow.png"));

			// Set "i" value as button column so value can be accessed within actionListener
			int buttonCol = i;

			button[0][i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Corresponding board column based on column of button
					int boardCol = 2 * buttonCol + 1;

					// Shifts column down, and sets new free tile
					LabyrinthGUI.setFreeTile(LabyrinthGUI.board.shiftDown(boardCol, LabyrinthGUI.freeTile));
					addFreeTile(LabyrinthGUI.freeTile);

					// Disable all insert buttons
					disableAll();

					// Enable move buttons
					LabyrinthGUI.moveButtons.enableAll();

					// Set opposite row and column for opposite button to be disabled
					oppRow = 2;
					oppCol = buttonCol;

				}
			});

			// Add buttons to GUI
			button[0][i].setBounds((192 * (i + 1)), 0, 96, 96);
			add(button[0][i]);

		}

		// For loop for all the bottom row buttons (Shift up buttons)
		for (int i = 0; i < 3; i++) {
			button[2][i] = new JButton(new ImageIcon("betterImages/btnBottomRow.png"));
			
			// Set "i" value as button column so value can be accessed within actionListener
			int buttonCol = i;
			
			button[2][i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Corresponding board column based on column of button
					int boardCol = 2 * buttonCol + 1;

					// Shifts column down, and sets new free tile
					LabyrinthGUI.setFreeTile(LabyrinthGUI.board.shiftUp(boardCol, LabyrinthGUI.freeTile));
					addFreeTile(LabyrinthGUI.freeTile);

					// Disable all insert buttons
					disableAll();
					
					// Enable move buttons
					LabyrinthGUI.moveButtons.enableAll();

					// Set opposite row and column for opposite button to be disabled
					oppRow = 0;
					oppCol = buttonCol;
				}
			});

			button[2][i].setBounds((192 * (i + 1)), 768, 96, 96);
			add(button[2][i]);
		}

		// For loop for all the right column buttons (Shift left buttons)
		for (int i = 0; i < 3; i++) {
			button[1][i] = new JButton(new ImageIcon("betterImages/btnRightCol.png"));

			// Set "i" value as button row so value can be accessed within actionListener
			int buttonRow = i;

			button[1][i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Corresponding board row based on row of button
					int boardRow = 2 * buttonRow + 1;

					// Shifts row left, sets new free tile
					LabyrinthGUI.setFreeTile(LabyrinthGUI.board.shiftLeft(boardRow, LabyrinthGUI.freeTile));
					addFreeTile(LabyrinthGUI.freeTile);

					// Disable all insert buttons
					disableAll();

					// Enable move buttons
					LabyrinthGUI.moveButtons.enableAll();

					// Set opposite row and column for opposite button to be disabled
					oppRow = 3;
					oppCol = buttonRow;

				}
			});

			// Add buttons to GUI
			button[1][i].setBounds(768, (192 * (i + 1)), 96, 96);
			add(button[1][i]);
		}

		// For loop for all the left column buttons (Shift right buttons)
		for (int i = 0; i < 3; i++) {
			button[3][i] = new JButton(new ImageIcon("betterImages/btnLeftCol.png"));
			
			// Set "i" value as button row so value can be accessed within actionListener
			int buttonRow = i;
			button[3][i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Corresponding board row based on row of button
					int boardRow = 2 * buttonRow + 1;

					// Shifts row right, sets new free tile
					LabyrinthGUI.setFreeTile(LabyrinthGUI.board.shiftRight(boardRow, LabyrinthGUI.freeTile));
					addFreeTile(LabyrinthGUI.freeTile);

					// Disable all insert buttons
					disableAll();

					// Enable move buttons
					LabyrinthGUI.moveButtons.enableAll();

					// Set opposite row and column for opposite button to be disabled
					oppRow = 1;
					oppCol = buttonRow;

				}
			});

			// Adds buttons to GUI
			button[3][i].setBounds(0, (192 * (i + 1)), 96, 96);
			add(button[3][i]);
		}

		// Rotate right and left buttons
		rotateRightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Removes previous free tile from GUI
				remove(LabyrinthGUI.freeTile);
				
				// Gets new orientation
				int orientation = rotate.rotateTileRight(LabyrinthGUI.freeTile);

				// Sets free tile
				String item = LabyrinthGUI.freeTile.getItem();
				String type = LabyrinthGUI.freeTile.getType();
				boolean movable = LabyrinthGUI.freeTile.isMovable();
				int row = LabyrinthGUI.freeTile.getRow();
				int column = LabyrinthGUI.freeTile.getColumn();
				Tile tile = new Tile(item, type, orientation, movable, row, column);
				LabyrinthGUI.freeTile = tile;
				
				// Adds updated free tile to GUI
				addFreeTile(LabyrinthGUI.freeTile);

			}
		});
		
		// Adds rotate buttons to GUI
		rotateRightBtn.setBounds(100, 0, 96, 96);
		add(rotateRightBtn);

		rotateLeftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Removes previous free tile from GUI
				remove(LabyrinthGUI.freeTile);
				
				// Gets new orientation
				int orientation = rotate.rotateTileLeft(LabyrinthGUI.freeTile);

				// Sets free tile
				String item = LabyrinthGUI.freeTile.getItem();
				String type = LabyrinthGUI.freeTile.getType();
				boolean movable = LabyrinthGUI.freeTile.isMovable();
				int row = LabyrinthGUI.freeTile.getRow();
				int column = LabyrinthGUI.freeTile.getColumn();
				Tile tile = new Tile(item, type, orientation, movable, row, column);
				LabyrinthGUI.freeTile = tile;
				
				// Adds updated free tile to GUI
				addFreeTile(LabyrinthGUI.freeTile);

			}
		});

		// Adds rotate buttons to GUI
		rotateLeftBtn.setBounds(0, 100, 96, 96);
		add(rotateLeftBtn);

	}

}
