package amazingLabyrinth;

public class RotateButton {
	// 	Fields
	private int orientation;

	//Constructor
	public RotateButton() {

	}

	// Rotates free tile right, adds 1 to orientation
	public int rotateTileRight(Tile currentFreeTile) {
		orientation = currentFreeTile.getOrientation();

		if (orientation != 3)
			orientation++;
		else
			orientation = 0;

		// Updates GUI
		LabyrinthTest.app.revalidate();
		LabyrinthTest.app.repaint();

		return orientation;

	}

	// Rotates free tile left, subtracts 1 from orientation 
	public int rotateTileLeft(Tile currentFreeTile) {
		orientation = currentFreeTile.getOrientation();

		if (orientation != 0)
			orientation--;
		else
			orientation = 3;
		
		// Updates GUI
		LabyrinthTest.app.revalidate();
		LabyrinthTest.app.repaint();

		return orientation;

	}

}
