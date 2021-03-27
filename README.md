# AmazingLabyrinth

THis was project one of the ICS4U course. The goal of the project was to digitalize *THE aMAZEing LABYRINTH* boardgame. The goal of the game is to collect all of your treasure(s) before the others. Each player gets the chance to manipulate the board by inserting a piece and shifting a row or column in order to clear a path, block your opponents, or strategically place yourself for the next move. 

The game has four players, and at the start of the game, they can choose to hunt for 1-6 treasures. If a player lands on one of their treasures, its corresponding card turns green. Once one of the player collects all of their treasures, the game ends. 

---

Here are the given instructions from our teacher:

##### Setup:

Randomly distribute the movable tiles on the board
Shuffle and deal the treasure cards

##### On a Turn:

You must first insert the ‘extra’ tile into the maze
Tile movement cannot be “reversed” in successive turns so you can’t insert the extra tile into the opposite side where your opponent just played (i.e. no ‘back and forth’ tile moves allowed)

Then move (or choose to stay still) on each turn
You may land on a treasure (and turn over the matching card in your hand) or else move to a more advantageous position for a future possible move

---

### Group Contributions
See [Test Class](.src/amazingLabyrinth/LabyrinthTest.java) for a more detailed description.

Melissa
- Created all of the new image files that were not provided by our teacher
  -  Arguably what made our game look better than everyone else's
- Helped with troubleshooting errors with generating a randomized board
- Sbuffled and assigned cards for each player
- Created and designed instructions frame
- Worked extensively on GUI to enhance the overall look and functionality

Jacob (Me)
- Essentially all of the game logic:
  - Shifting tiles
  - Shifting players
  - Moving players
  - Validating pathways
  - Enabling and disabling buttons based on the player's position
  - Rotating images
- Connected the logic to the GUI
- Created basic GUI (improved by melissa)

Carlos
- ... lol
