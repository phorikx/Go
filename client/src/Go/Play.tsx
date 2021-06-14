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

    return (
            /**        <div>
          
             {nameHasTurn} has the turn. Enter a move!     
             <form onSubmit={(e) => tryPlayMove(e)}>
             <input value={playedMove}
             placeholder="Which Pit do you want to select?"
             onChange={(e) => setPlayedMove(e.target.value)}
             />
            
             <p className="errorMessage">{errorMessage}</p>
            
            <button className="submitMoveButton" type="submit">
                 Select Pit!
                         </form>
             </button> */            
    <div>
        TODO
     </div>
    )
}