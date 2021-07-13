# Go

## About
Go is an old board game which has been played for over 3000 years.
The game pits two players against each other on a board which consists of a square lattice. 
The goal of the game is to surround territory on the board by placing stones, and to capture the stones of their opponent. 
Go can be played on a board of varying sizes. The common size for competitive play is 19x19, but beginners often play on boards of size 9x9 or 13x13.
The game ends when both players pass consecutively.

## Rules
Go has two players. One plays as Black, the other plays as white. Go is played on a board consisting of the same number of vertical and horizontal lines. 
The points where the horizontal and vertical lines meet are called intersections, and stones are played on intersection. Two intersections are connected if they are on the same line without another intersection inbetween them.
Players play their stones alternatively on the intersections. Therefore, a intersection can have three states: unoccupied, occupied by black and occupied by white.

A stone has liberties, which are the intersections to which it is connected which are onoccupied. A stone shares its liberties with its neighbours of the same colour. 

The board starts empty. Black plays the first move, after which the players alternate taking turns. During a turn, a player plays exactly one stone or passes, after which their opponent gets the turn.

If after a stone is played, a stone or a connected group of stones is surrounded by stones of the other colour (the stone/group of stones has no liberties left), the stones get removed from the board and they go to the opposing players, who gets a point for each of them at the end of the game. In determining which groups of stones get removed, you first look at groups of stones of opposite colour from the colour which was just played. If after removing stones of the opposite colour, a stone or group of stones of the same colour as the player who just played a move is still surrounded, that stone or group of stones is removed and given to the opponent.
A play is illegal if after completing the previous steps, the board is in the same state as it was in some time previously.

The game ends when two players pass consecutively. Concerning the score, the players add up the stones they captured, the stones they have on the board, and all intersections which are in an area which is surrounded solely by stones of their own colour. The white player, who went second, gets a bonus of 5.5 points. The player with the most points wins.


## Repository structure

- Main folder (this folder): contains the files relevant for the whole project. For example the Gradle-wrapper files, the .gitignore, and this readme.
- api/: contains the files for the API or service layer of your application.
- api/src/main/java/go/api: contains the web endpoints.
- api/src/main/java/go/api/models: contains the web endpoints.
- domain/: contains the files that model the business domain (game rules). 
- client/: contains the client (front-end)

## Install instructions
These installation instructions are for running the application in Windows.

A user has to install Gradle and configure the environmental variable.
This application was made with Gradle 7.0.

Alternatively, the user can use './gradlew' instead of 'gradle' in the following commands, using the gradle wrapper which is provided in the repository.

A user also has to install Node and configure the correct path variable for the webserver to run.

The application has two components. One runs the website, the other runs the game logic and the API which communicates with the website.
To run the website, go to the client folder in a terminal.
There, first run the command 'npm install'
Once this has been done for the first time, the web-side of the application can be started by running 'npm run start' and leaving the terminal running.

In a new terminal window, navigate to the base path in the repository.
There, first run the command 'gradle build' to compile the source code. If this step fails, check if Gradle is configured correctly.
To run the domain logic side of the application, now run 'gradle run' and leave the terminal running.

The website can now be found on http://localhost:3000/

To run unit tests, one can run 'gradle test' in the root folder of the repository.


