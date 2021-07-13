import React from "react";

export function About() {
    return <div>
        <h2>Go</h2>
        
        <h3>About</h3> 

            <p>
                Go is an old board game which has been played for over 3000 years.
                The game pits two players against each other on a board which consists of a square lattice. 
                The goal of the game is to surround territory on the board by placing stones, and to capture the stones of their opponent. 
                Go can be played on a board of varying sizes. The common size for competitive play is 19x19, but beginners often play on boards of size 9x9 or 13x13.
                The game ends when both players pass consecutively. 
            </p>

        <h3>Rules</h3>
                <p>
                Go has two players. One plays as Black, the other plays as white. Go is played on a board consisting of the same number of vertical and horizontal lines. 
                The points where the horizontal and vertical lines meet are called intersections, and stones are played on intersection. Two intersections are connected if they are on the same line without another intersection inbetween them.
                Players play their stones alternatively on the intersections. Therefore, a intersection can have three states: unoccupied, occupied by black and occupied by white.
                
                A stone has liberties, which are the intersections to which it is connected which are onoccupied. A stone shares its liberties with its neighbours of the same colour. 

                The board starts empty. Black plays the first move, after which the players alternate taking turns. During a turn, a player plays exactly one stone or passes, after which their opponent gets the turn.

                If after a stone is played, a stone or a connected group of stones is surrounded by stones of the other colour (the stone/group of stones has no liberties left), the stones get removed from the board and they go to the opposing players, who gets a point for each of them at the end of the game. In determining which groups of stones get removed, you first look at groups of stones of opposite colour from the colour which was just played. If after removing stones of the opposite colour, a stone or group of stones of the same colour as the player who just played a move is still surrounded, that stone or group of stones is removed and given to the opponent.
                A play is illegal if after completing the previous steps, the board is in the same state as it was in some time previously.

                The game ends when two players pass consecutively. Concerning the score, the players add up the stones they captured, the stones they have on the board, and all intersections which are in an area which is surrounded solely by stones of their own colour. The white player, who went second, gets a bonus of 5.5 points. The player with the most points wins.

            </p>
    </div>
}