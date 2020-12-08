package amazingLabyrinth;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel { 

	// fields
	private String item;
	private String type; // I, T, L
	private int orientation; // 0 original position, 1 rotate 1 position, 2... - 3
	private boolean movable;
	private int row;
	private int column;

	// constructor
	public Tile(String item, String type, int orientation, boolean movable, int row, int column) {
		super();
		this.item = item;
		this.type = type;

		if (orientation == -1)
			orientation = (int) (Math.random() * 4);

		this.orientation = orientation;

		this.movable = movable;
		this.row = row;
		this.column = column;

		if (item.equals("None"))
			setIcon(new ImageIcon("betterImages/" + type + orientation + ".png"));
		else
			setIcon(new ImageIcon("betterImages/" + item + orientation + ".png"));

	}

	// getters and setters
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean moveable) {
		this.movable = moveable;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {

		return "Tile [item=" + item + ", type=" + type + ", orientation=" + orientation + ", movable=" + movable
				+ ", row=" + row + ", column=" + column + "]";
	}

}
