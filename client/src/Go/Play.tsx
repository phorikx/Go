import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./Play.css";

import empty from './Images/empty.png';
import white from './Images/white.png';
import black from './Images/black.png';

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

    function createImage(input: String) {
        if (input == "E") {
            return <img src={empty} />;
        }
        if (input == "W") {
            return <img src={white} />;
        }
        if (input == "B") {
            return <img src={black} />;
        }
        return <img src={empty} />;
    }

    function generateTable(size : number){
        let rows = [];
        for (var rowid = 0; rowid < size; rowid++){
          let cell = []
          for (var columnid = 0; columnid < size; columnid++){
            let cellID = `cell${rowid}-${columnid}`;
            let reactString = `[${rowid},${columnid}],false`;
            cell.push(<td key={cellID} id={cellID}> <button onClick = {(e) => tryPlayMove(reactString, e)} class="button">{createImage(gameState.board[rowid][columnid])}</button></td>)
          }
          rows.push(<tr>{cell}</tr>)
        }
        return(
                <table id="go-board">
                   <tbody>
                     {rows}
                   </tbody>
                 </table>
        )
      }
    
    async function tryPlayMove(playedMove: String, e: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        e.preventDefault();
        try {
            console.log(playedMove);
            var firstCoordinateString = (playedMove.match("([^,]*),"))[1];
            firstCoordinateString = firstCoordinateString.substring(1);
            console.log(firstCoordinateString);
            var firstCoordinate = Number(firstCoordinateString)
            var secondCoordinateString = (playedMove.match(",([^,]*)\]"))[1];
            console.log(secondCoordinateString);
            var didPassString = (playedMove.match("[^t]*(true|false)"))[1];
            var didPass;
            if (didPassString == "true") {
                didPass = true;
            } else {
                didPass = false;
            }
            var secondCoordinate = Number(secondCoordinateString);
            var numbersPlayed = [firstCoordinate,secondCoordinate];
            const response = await fetch('go/api/play', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({playerName: nameHasTurn, firstCoordinatePlayed: firstCoordinate, secondCoordinatePlayed: secondCoordinate, didPass: didPass})
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
            {generateTable(gameState.boardSize)} 
             {nameHasTurn} has the turn. Enter a move!
             <p>
             <button id="passbutton" onClick = {(e) => tryPlayMove("[0,0],true", e)} > Pass </button>
             </p>

             <table>
                 <tbody>
                     <tr>
                         <td> {gameState.players[0].name} </td>
                         <td> {gameState.players[1].name} </td>
                     </tr>
                     <tr>
                         <td> {gameState.players[0].score} </td>
                         <td> {gameState.players[1].score} </td>
                     </tr>
                 </tbody>
            </table>     
             </div>       
    )
}