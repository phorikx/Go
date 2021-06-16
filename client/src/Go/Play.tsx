import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {
    const [errorMessage, setErrorMessage] = useState("");
    const [playedMove, setPlayedMove] = useState("");

    if (gameState.players[0].hasTurn) {
        var nameHasTurn = gameState.players[0].name;
        var player1hasturn=true;
        var player2hasturn=false;
    } else {
        var nameHasTurn = gameState.players[1].name;        
        var player1hasturn=false;
        var player2hasturn=true;
    }
    
    async function tryPlayMove(playedMove: String, e: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        e.preventDefault();
        try {
            console.log(playedMove);
            var firstCoordinateString = (playedMove.match("([^,]*),"))[1];
            firstCoordinateString = firstCoordinateString.substring(1);
            console.log(firstCoordinateString);
            var firstCoordinate = Number(firstCoordinateString)
            var secondCoordinateString = (playedMove.match(",([^\]]*)\]"))[1];
            console.log(secondCoordinateString);
            var secondCoordinate = Number(secondCoordinateString);
            var numbersPlayed = [firstCoordinate,secondCoordinate];
            const response = await fetch('go/api/play', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({playerName: nameHasTurn, firstCoordinatePlayed: firstCoordinate, secondCoordinatePlayed: secondCoordinate})
            });

            if (response.ok) {
                console.log("Do we get here?");
                const updatedGameState = await response.json();                
                setGameState(updatedGameState);
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }

    }

    return (
            
          <div>
            <header> {gameState.players[0].name} vs {gameState.players[1].name} </header>
            <table>
                <tbody>
                    <tr>
                        <td> <button onClick = {(e) => tryPlayMove("[0,0]", e)}>{gameState.board[0][0]} </button></td>
                    </tr>
                </tbody>
            </table>
             {nameHasTurn} has the turn. Enter a move!     
             </div>       
    )
}