package go.api.models;

public class GameStatus {
    public GameStatus(go.domain.Player Player, String namePlayer1, String namePlayer2) {
        this.endOfGame = false;
        this.winner = "";
    }

    boolean endOfGame;
    public boolean getEndOfGame() { return endOfGame; }
    
    String winner;
    public String getWinner() { return winner; }
}
