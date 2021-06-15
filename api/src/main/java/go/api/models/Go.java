package go.api.models;

public class Go {
    public Mancala(mancala.domain.GoImpl Go, String namePlayer1, String namePlayer2) {
    players = new APIPlayer[2];

    }

    APIPlayer[] players;
    public APIPlayer[] getPlayers() { return players; }

    GameStatus gameStatus;
    public GameStatus getGameStatus() { return gameStatus; }
}
