import React, { useState } from "react";
import type { GameState } from "../gameState";
import "./EndOfGame.css";

type EndOfGameProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function EndOfGame({ gameState, setGameState }: EndOfGameProps) {
    const [errorMessage, setErrorMessage] = useState("");
    const [playedMove, setPlayedMove] = useState("");

    return (
    <div>
    {gameState.gameStatus.winner} has won.

    Final score:

    <table>
        <tr>
            <td>
            {gameState.players[0].name} vs
            </td>
            <td> {gameState.players[1].name}</td>
        </tr>
        <tr>
            <td>
                {gameState.gameStatus.score[0]}
            </td>
            <td>{gameState.gameStatus.score[1]}</td>
        </tr>
    </table>
     </div>
    )
}