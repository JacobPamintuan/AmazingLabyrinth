/**
 * Jacob Pamintuan (33 ⅓%): Shifted tiles, shifted tokens, validate token moves, validate winner,
 * troubleshooted refresh GUI, disabled and enabled proper buttons
 * Melissa Ramdial (33 ⅓%): Created image file and text files, troubleshooted errors with generating random board,
 * shuffled and assigned cards, created and designed instructions frame, enhanced overall look and functionality of GUI, created JMenu
 * Carlos Wong (33 ⅓%): Rotated free tile, troubleshooted two token shifting errors, created move buttons, created popup for end game, helped shuffle cards
 * 
 * Date: Sunday, November 29, 2020
 * 
 * Course code: ICS 4U1-01
 * Instructor: Mr. Fernandes
 * 
 * Project: Software Project #1
 * Product: Amazing Labyrinth
 * 
 * Description:
 * This product digitalizes the classic family board game “Amazing Labyrinth.” This exciting game is full with twists and turns (literally)
 * as players navigate their way through a moving labyrinth. Challenge three other players, in a logic and luck-filled race to collect all your treasures.
 * 
 * Features:
 * 	- Pop up panels
 * 	- JMenus
 * 	- Card colour change when found
 * 	- Visually pleasing and user friendly interface
 * 
 * Major Skills:
 * 	- Object Oriented Programming
 * 	- Polymorphism
 * 	- Java Swing
 * 	- Java Graphical User interface
 * 	- Generation and manipulation of images using Java GUI
 * 	- Actionlistener
 * 	- File reading
 * 
 * Areas of Concern:
 * 	- Hardware Specifications:
 * 		- 1700x1000 pixel screen (21-inch monitor)
 * 	- Concerns:
 * 		- Shifting does not work in some scenarios that involves three or more players on the same tile
 * 		- When there is more than one player on a tile, only one player can be seen by the user however the player(s) still exist on the tile.
 * 		- On some computers when the program is ran the instruction frame that appears in the beginning sometimes appears behind the main game frame.
 * 		It is supposed to show up in front of the main game frame. It shows up on Melissa’s but not on Jacob’s.
 * 		This may be a consequence of older versions of eclipse or different operating systems (macOS Catalina, macOS Big Sur, Windows 10)
 * 			- Check video attached to assignment
 * 		- Multiple instruction panels can appear if the user clicks “Help”>”Instructions” if a instruction panel already exists
 **/

package amazingLabyrinth;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JPanel;

public class LabyrinthTest extends JPanel {

	public static Tile[] tileArray = new Tile[50];
	public static ArrayList<Card> cardArray = new ArrayList<Card>();
	public static LabyrinthGUI app;
	public static Player[] playerArr = new Player[4];
	public static Player winner;

	// Setup Game
	private static void setupGame() {

		Scanner input;

		try {
			// Read in treasure .txt file
			input = new Scanner(new File("Treasures"));
			input.useDelimiter(",");
			
			for (int i = 0; i < 24; i++) {
				tileArray[i] = new Tile(input.next().replaceAll("\n", "").replaceAll("\r", ""), input.next(),
						input.nextInt(), input.nextBoolean(), input.nextInt(), input.nextInt());

			}

			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Error");
		}

		// add the movable tiles L tiles
		for (int index = 24; index < 33; index++) {
			tileArray[index] = new Tile("None", "L", -1, true, -1, -1);
		}
		// add the movable tiles I tiles
		for (int index = 33; index < 46; index++)
			tileArray[index] = new Tile("None", "I", -1, true, -1, -1);

		// add home tiles
		tileArray[46] = new Tile("Red", "L", 1, false, 0, 0);
		tileArray[47] = new Tile("Blue", "L", 2, false, 0, 6);
		tileArray[48] = new Tile("Green", "L", 3, false, 6, 6);
		tileArray[49] = new Tile("Yellow", "L", 0, false, 6, 0);

		try {
			// Read in Cards .txt file
			input = new Scanner(new File("Cards"));
			input.useDelimiter(",");

			for (int i = 0; i < 24; i++) {
				cardArray.add(new Card(input.next().replaceAll("\n", "").replaceAll("\r", ""), input.nextInt(),
						input.nextInt(), input.nextBoolean()));

				// Shuffle the cards within the ArrayList
				Collections.shuffle(cardArray);

			}

			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Error");
		}

		// Initialized players starting positions
		playerArr[0] = new Player("Red", 0, 0, 0);
		playerArr[1] = new Player("Blue", 0, 6, 0);
		playerArr[2] = new Player("Green", 6, 6, 0);
		playerArr[3] = new Player("Yellow", 6, 0, 0);

	}

	// Main class
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					setupGame();
					app = new LabyrinthGUI();
					app.setVisible(true);

					Instructions inst = new Instructions();
					inst.setBounds(0, 0, 800, 800);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

	}
}
