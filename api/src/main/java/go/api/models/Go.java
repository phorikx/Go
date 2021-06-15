package go.api.models;

import go.domain.*;

public class Go {
    public Go(go.domain.GoImpl Go, String namePlayer1, String namePlayer2) {
    players = new APIPlayer[2];

    }

    APIPlayer[] players;
    public APIPlayer[] getPlayers() { return players; }

    GameStatus gameStatus;
    public GameStatus getGameStatus() { return gameStatus; }
}
