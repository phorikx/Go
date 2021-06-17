package go.domain;

public class Player {
    private Player opponent;
    private Boolean hasTurn;
    private int capturedStones;

    public Player getOpponent() {
        return this.opponent;
    }

    public Player(Player opponent) {
        this.capturedStones = 0;
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

    public void giveStones(int amountOfStones) {
        this.capturedStones = this.capturedStones + amountOfStones;
    }

    public int getCapturedStones() {
        return this.capturedStones;
    }

    public void switchTurn() {
        this.hasTurn = !this.hasTurn;
        this.getOpponent().hasTurn = !this.hasTurn;
    }
}
