# War Simulator
A simple Java project that plays a sometimes lifetime-long card game of war. Two virtual players play random cards from their decks until one loses all of them.

## Features
Despite the application is kept simple, we can set up some options before the game. Those include:
* Ability to give some names to our wretches who will play the game for us (instead of Player 1, Player 2)
* Saving games to text files (a new folder will be created at the location of the file)
* Showing game statistics after the game (this includes how much time we would have to play the game with a very optimistic assumptions of 5-second turns)
* Numbering turns

Those settings will be saved to file in the location of the program (using *Properties* class), so the next time we run our program we will start with the same ones we finished the last time.

## Self-criticism
While creating this game, I deliberately dropped some OOP features for the sake of simplicity. I later regretted this decision, for example I would have hard time generalising this game to more players (there is no Player class and they are represented by two *CardCollection* objects, not even lists). Another example is the *Settings* class that mixes logic and printing the menu. I probably could find more defects. I made this project knowing less Java and with my Object-Oriented consciousness in an early stage of development and now, as the time passed, see those issues more clearly.

## Inspiration
I came accross the idea to make this program during 5-hour-long flight when I and my friend decided to play this game. We played it for an hour and I finished the flight more tired because of this game than the flight itself, having in mind how nonsensical is to play a game where you cannot decide about anything. That made me think of this program and I created it two weeks after coming back from the journey.
