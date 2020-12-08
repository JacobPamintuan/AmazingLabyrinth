package amazingLabyrinth;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel {
	
	// Fields

	private String playerColor;
	private int row;
	private int col;
	private int score;
	private ImageIcon playerImage;

	// Constructor 
	public Player(String playerColor, int row, int col, int score) {

		this.playerColor = playerColor;
		this.row = row;
		this.col = col;
		this.score = score;
		
		playerImage = new ImageIcon("betterImages/" + playerColor + " Token.png");
		
		setIcon(playerImage);
	}

	//Setters, Getters, overrided toString
	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ImageIcon getPlayerImage() {
		return playerImage;
	}

	public void setPlayerImage(ImageIcon playerImage) {
		this.playerImage = playerImage;
	}
	
	@Override
	public String toString() {
		return "Player [playerColor=" + playerColor + ", row=" + row + ", col=" + col + ", score=" + score + "]";
	}

}
