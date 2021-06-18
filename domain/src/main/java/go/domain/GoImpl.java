package go.domain;

import go.domain.Intersection.Occupation;

public class GoImpl {
    private Board gameBoard;
    private Player[] players;
    private GameStatus gameStatus;
    private GameHistory gameHistory;


    public GoImpl(int boardSize){
        this.gameBoard = new Board(boardSize);
        players = new Player[2];

        
        players[0] = new Player(null);
        players[1] = players[0].getOpponent();

        this.gameBoard.assignPlayers(players[0]);

        this.gameStatus = new GameStatus(players[0], gameBoard);
        this.gameHistory = new GameHistory();
    }

    public void playIntersection(int[] coordinates, go.domain.Intersection.Occupation colour){
        System.out.println("Received move on coordinates:");
        if (gameStatus.validMove(coordinates, colour, this.gameHistory)) {
            this.gameBoard.playMove(coordinates, colour);
            players[0].switchTurn();
            gameHistory.addBoardState(gameBoard.getStringEncoding());
        }
    }

    public Player getFirstPlayer() {
        return this.players[0];
    }

    public Player getSecondPlayer() {
        return this.players[1];
    }

    public Board getBoard() {
        return gameBoard;
    }

}
