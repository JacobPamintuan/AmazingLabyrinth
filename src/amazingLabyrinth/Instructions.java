package amazingLabyrinth;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Instructions extends JFrame{
	
	// Opens instructions 
	// Constructor
	public Instructions(){
		
		// Labels frame "Instructions"
		JFrame Instructions = new JFrame("Instructions");
		Instructions.setSize(800,800);
		Instructions.setVisible(true);
		// Adds image to frame
		Instructions.add(new JLabel(new ImageIcon("betterImages/Instructions.png")));
		
	}

}
