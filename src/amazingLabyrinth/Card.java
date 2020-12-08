package amazingLabyrinth;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card extends JLabel {
	
	// fields

	private String item;
	private int row;
	private int col;
	private boolean isFound;
	
	// Constructor 	
	public Card(String item, int row, int col, boolean isFound) {
		super();
		this.item = item;
		this.row = row;
		this.col = col;
		this.isFound = isFound;

		setIcon(new ImageIcon("betterImages/Card" + item + ".png"));

	}

	// Setters and Getters
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isFound() {
		return isFound;
	}

	public void setFound(boolean isFound) {
		this.isFound = isFound;
	}

	@Override
	public String toString() {
		return "Card [item=" + item + ", row=" + row + ", col=" + col + ", isFound=" + isFound + "]";
	}

}
