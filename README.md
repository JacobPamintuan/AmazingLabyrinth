# AmazingLabyrinth

This was project one of the ICS4U course. The goal of the project was to digitalize *THE aMAZEing LABYRINTH* boardgame. The goal of the game is to collect all of your treasure(s) before the others. Each player gets the chance to manipulate the board by inserting a piece and shifting a row or column in order to clear a path, block your opponents, or strategically place yourself for the next move. 

The game has four players, and at the start of the game, they can choose to hunt for 1-6 treasures. If a player lands on one of their treasures, its corresponding card turns green. Once one of the player collects all of their treasures, the game ends. 

---

Here are the given instructions from our teacher:

#### Setup:

1. Randomly distribute the movable tiles on the board
2. Shuffle and deal the treasure cards

#### On a Turn:

1. You must first insert the ‘extra’ tile into the maze
    - Tile movement cannot be “reversed” in successive turns so you can’t insert the extra tile into the opposite side where your opponent just played (i.e. no ‘back and forth’ tile moves allowed)

2. Then move (or choose to stay still) on each turn
    - You may land on a treasure (and turn over the matching card in your hand) or else move to a more advantageous position for a future possible move

##### Modified Rules (Where our game deviates from the original Amazing Labyrinth):
- Deal out 1 to 6 treasure cards face up to each player (there may be extra cards left over)
- You can recover the treasures in any order (does not have to be the order they are dealt)
- A player ‘wins’ when they reach their last treasure (no need to return ‘home’)

---
### Some Screenshots:

###### of Cards Selection:

![Card Selection](https://user-images.githubusercontent.com/36178603/112704952-fdc69d00-8e72-11eb-8766-b735a59b34cf.png)


<p align="center"> <b>Start of the game, randomized board, each player dealt 3 cards:<b/></p>
![Game Start](https://user-images.githubusercontent.com/36178603/112704942-f0a9ae00-8e72-11eb-9249-9b67f0ffbe01.png)


<p align="center"> <b>Instructions:</b></p>

![Instructions](https://user-images.githubusercontent.com/36178603/112704959-0d45e600-8e73-11eb-8dd3-8e287f924f21.png)

##### Treasure Collected! - Card turns Green, Score Increased
![Card Found](https://user-images.githubusercontent.com/36178603/112705146-da502200-8e73-11eb-8250-ccf939611f1f.png)



---
### Group Contributions
See the [test Class](.src/amazingLabyrinth/LabyrinthTest.java) for a more detailed description.

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
