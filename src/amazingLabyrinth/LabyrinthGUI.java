package amazingLabyrinth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabyrinthGUI extends JFrame implements ActionListener {
	
	// fields
	public static Board board;
	public static Tile freeTile;
	public static CardLayout card;
	public static ButtonBoard buttonBoard;
	public static MoveButtons moveButtons;
	public static PlayerBoard playerBoard;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu cardMenu = new JMenu("Setup");
	JMenu helpMenu = new JMenu("Help");
	JMenuItem instructions = new JMenuItem("Instructions");
	JMenuItem card1 = new JMenuItem("1 Card");
	JMenuItem card2 = new JMenuItem("2 Cards");
	JMenuItem card3 = new JMenuItem("3 Cards");
	JMenuItem card4 = new JMenuItem("4 Cards");
	JMenuItem card5 = new JMenuItem("5 Cards");
	JMenuItem card6 = new JMenuItem("6 Cards");

	// Initialized GUI
	// Constructor
	public LabyrinthGUI() {
		
		// Sets size and layout, null meaning using coordinates
		setSize(1700, 1000);
		getContentPane().setLayout(null);

		// Selects starting free tile
		freeTile = LabyrinthTest.tileArray[45];
		freeTile.setBounds(0, 0, 96, 96);

		// Initializes player board
		playerBoard = new PlayerBoard();
		playerBoard.setBounds(96, 96, 672, 672);
		getContentPane().add(playerBoard);

		// Initializes game/tile board
		board = new Board();
		board.setBounds(96, 96, 672, 672);
		getContentPane().add(board);

		// Initializes button board
		buttonBoard = new ButtonBoard(freeTile);
		buttonBoard.setBounds(0, 0, 864, 864);
		getContentPane().add(buttonBoard);

		// Initializes move buttons
		moveButtons = new MoveButtons();
		moveButtons.disableAll();
		moveButtons.setBounds(960, 672, 400, 192);
		getContentPane().add(moveButtons);

		//Setup Menu 
		setJMenuBar(menuBar);

		menuBar.add(cardMenu);

		cardMenu.add(card1);
		card1.addActionListener(this);

		cardMenu.add(card2);
		card2.addActionListener(this);

		cardMenu.add(card3);
		card3.addActionListener(this);

		cardMenu.add(card4);
		card4.addActionListener(this);

		cardMenu.add(card5);
		card5.addActionListener(this);

		cardMenu.add(card6);
		card6.addActionListener(this);

		// Help menu 
		menuBar.add(helpMenu);

		helpMenu.add(instructions);
		instructions.addActionListener(this);
		
		instructions.setEnabled(false);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	// Getters and setters for free tile
	public static Tile getFreeTile() {
		return freeTile;
	}

	public static void setFreeTile(Tile freeTile) {
		LabyrinthGUI.freeTile = freeTile;
	}

	// Initialize number of cards based on menu
	// Disables setup menu when # of cards is selected
	@Override
	public void actionPerformed(ActionEvent e) {
		// 1 Card
		if (e.getSource() == card1) {
			card = new CardLayout(1);
			card.setBounds(960, 100, 192, 528);
			getContentPane().add(card);

			card1.setEnabled(false);
			card2.setEnabled(false);
			card3.setEnabled(false);
			card4.setEnabled(false);
			card5.setEnabled(false);
			card6.setEnabled(false);
			
			instructions.setEnabled(true);

			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();

		} else if (e.getSource() == card2) {
			// 2 Cards
			card = new CardLayout(2);
			card.setBounds(960, 100, 288, 528);
			getContentPane().add(card);

			card1.setEnabled(false);
			card2.setEnabled(false);
			card3.setEnabled(false);
			card4.setEnabled(false);
			card5.setEnabled(false);
			card6.setEnabled(false);
			
			instructions.setEnabled(true);

			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();

		} else if (e.getSource() == card3) {
			// 3 Cards
			card = new CardLayout(3);
			card.setBounds(960, 100, 384, 528);
			getContentPane().add(card);

			card1.setEnabled(false);
			card2.setEnabled(false);
			card3.setEnabled(false);
			card4.setEnabled(false);
			card5.setEnabled(false);
			card6.setEnabled(false);
			
			instructions.setEnabled(true);

			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();

		} else if (e.getSource() == card4) {
			// 4 Cards
			card = new CardLayout(4);
			card.setBounds(960, 100, 480, 528);
			getContentPane().add(card);

			card1.setEnabled(false);
			card2.setEnabled(false);
			card3.setEnabled(false);
			card4.setEnabled(false);
			card5.setEnabled(false);
			card6.setEnabled(false);
			
			instructions.setEnabled(true);

			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();

		} else if (e.getSource() == card5) {
			// 5 Cards
			card = new CardLayout(5);
			card.setBounds(960, 100, 576, 528);
			getContentPane().add(card);

			card1.setEnabled(false);
			card2.setEnabled(false);
			card3.setEnabled(false);
			card4.setEnabled(false);
			card5.setEnabled(false);
			card6.setEnabled(false);
			
			instructions.setEnabled(true);

			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();

		} else if (e.getSource() == card6) {
			// 6 Cards
			card = new CardLayout(6);
			card.setBounds(960, 100, 672, 528);
			getContentPane().add(card);

			card1.setEnabled(false);
			card2.setEnabled(false);
			card3.setEnabled(false);
			card4.setEnabled(false);
			card5.setEnabled(false);
			card6.setEnabled(false);
			
			instructions.setEnabled(true);

			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();
		}

		// Opens instructions
		else if (e.getSource() == instructions) {

			Instructions inst = new Instructions();
			inst.setBounds(0,0,800,800);
			LabyrinthTest.app.revalidate();
			LabyrinthTest.app.repaint();

		}	
		
		// Disables all move buttons and enables all insert buttons
		LabyrinthGUI.moveButtons.disableAll();
		ButtonBoard.enableAll();

	}
}
