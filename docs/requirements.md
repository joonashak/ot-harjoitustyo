# Requirements Specification

## Purpose

Shamery is a concentration-type game where the players try to match up two shapes that go inside one another. The shapes are found in cards placed face down on the board. The game can be played as solitaire or duel.

## Users

The game is local and has no authentication. New users can be added to keep track of highscores.

## Main Features

### Menu
- Start a new game. **[DONE]**
	- **Game modes:**
		- Time trial (single/dual) **[DONE]**
		- Points (single/dual) **[DONE]**
- Browse highscores and past games.
- Quit. **[DONE]**

### In Game
- Player(s) can play the game along [the rules](./rules.md). **[DONE]**
- Points visible. **[DONE]**
- When game ends, the result is saved in the game records.

## Future Ideas

- Custom cards.
- More game modes.
- Different rulesets.
- Game can be saved and resumed.
- Multiplayer: tally up scores of consecutive games as a series.