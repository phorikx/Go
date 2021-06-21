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
            <table>
                <tbody>
                    <tr>
                        <td> <button onClick = {(e) => tryPlayMove("[0,0],false", e)}> {createImage(gameState.board[0][0])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[1,0],false", e)}>{createImage(gameState.board[1][0])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[2,0],false", e)}>{createImage(gameState.board[2][0])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[3,0],false", e)}>{createImage(gameState.board[3][0])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[4,0],false", e)}> {createImage(gameState.board[4][0])} </button></td>
                    </tr>
                    <tr>
                        <td> <button onClick = {(e) => tryPlayMove("[0,1],false", e)}>{createImage(gameState.board[0][1])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[1,1],false", e)}>{createImage(gameState.board[1][1])}</button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[2,1],false", e)}>{createImage(gameState.board[2][1])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[3,1],false", e)}>{createImage(gameState.board[3][1])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[4,1],false", e)}>{createImage(gameState.board[4][1])} </button></td>
                    </tr>
                    <tr>
                        <td> <button onClick = {(e) => tryPlayMove("[0,2],false", e)}>{createImage(gameState.board[0][2])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[1,2],false", e)}>{createImage(gameState.board[1][2])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[2,2],false", e)}>{createImage(gameState.board[2][2])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[3,2],false", e)}>{createImage(gameState.board[3][2])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[4,2],false", e)}>{createImage(gameState.board[4][2])} </button></td>
                    </tr>
                    <tr>
                        <td> <button onClick = {(e) => tryPlayMove("[0,3],false", e)}>{createImage(gameState.board[0][3])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[1,3],false", e)}>{createImage(gameState.board[1][3])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[2,3],false", e)}>{createImage(gameState.board[2][3])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[3,3],false", e)}>{createImage(gameState.board[3][3])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[4,3],false", e)}>{createImage(gameState.board[4][3])} </button></td>
                    </tr>
                    <tr>
                        <td> <button onClick = {(e) => tryPlayMove("[0,4],false", e)}>{createImage(gameState.board[0][4])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[1,4],false", e)}>{createImage(gameState.board[1][4])}</button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[2,4],false", e)}>{createImage(gameState.board[2][4])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[3,4],false", e)}>{createImage(gameState.board[3][4])} </button></td>
                        <td> <button onClick = {(e) => tryPlayMove("[4,4],false", e)}>{createImage(gameState.board[4][4])} </button></td>
                    </tr>
                    
                </tbody>
            </table>
             {nameHasTurn} has the turn. Enter a move!

             <button onClick = {(e) => tryPlayMove("[0,0],true", e)}> Pass </button>

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