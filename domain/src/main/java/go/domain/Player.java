package go.domain;

public class Player {
    Player opponent;
    Boolean hasTurn;

    public Player getOpponent() {
        return this.opponent;
    }

    public Player(Player opponent) {
        if (opponent == null) {
            this.opponent = new Player(this);
            this.hasTurn = true;
        } else {
            this.opponent = opponent;
            this.hasTurn = false;
        }
    }

    public Boolean getTurn() {
        return hasTurn;
    }
}
