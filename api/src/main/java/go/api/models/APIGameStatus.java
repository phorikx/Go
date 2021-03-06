package go.api.models;

import go.domain.GameStatus;
import go.domain.Player;

public class APIGameStatus {
    public APIGameStatus(go.domain.GoImpl go, String namePlayer1, String namePlayer2) {
        GameStatus gameStatus = go.getGameStatus();
        Player player = go.getFirstPlayer();
        this.endOfGame = gameStatus.getEndOfGame();
        go.domain.Player winner = gameStatus.getWinner();
        if(winner == player) {
            this.winner = namePlayer1;
        } else if (winner == player.getOpponent()) {
            this.winner = namePlayer2;
        } else{
            this.winner = "";
        }
        this.score = go.getGameStatus().getScore();
    }

    boolean endOfGame;
    public boolean getEndOfGame() { return endOfGame; }
    
    String winner;
    public String getWinner() { return winner; }

    double[] score;
    public double[] getScore() {return score;}
}
