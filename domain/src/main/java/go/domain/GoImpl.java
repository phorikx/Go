package go.domain;

public class GoImpl {
    Board gameBoard;
    Player[] players;
    GameStatus gameStatus;
    GameHistory gameHistory;


    public GoImpl(int boardSize){
        this.gameBoard = new Board(boardSize);
        players = new Player[2];
        players[0] = new Player(null);
        players[1] = players[0].getOpponent();
    }

    public void playIntersection(int[] coordinates, go.domain.Intersection.Occupation colour) {
        this.gameBoard.playMove(coordinates, colour);
    }
    
}
