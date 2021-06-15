package go.domain;

public class Player {
    Player opponent;
    Boolean hasTurn;

    public Player getOpponent() {
        return this.opponent;
    }

    public Player(Player opponent) {
        if (opponent == null) {
            opponent = new Player(this);
        }
    }

    public Boolean getTurn() {
        return hasTurn;
    }
}
