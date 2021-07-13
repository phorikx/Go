package go.domain;

public class GoImpl {
    private Board gameBoard;
    private Player[] players;
    private GameStatus gameStatus;
    private GameHistory gameHistory;
    public final static double komi = 5.5;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public GoImpl(int boardSize){
        this.gameBoard = new Board(boardSize);
        players = new Player[2];

        
        players[0] = new Player(null);
        players[1] = players[0].getOpponent();

        this.gameBoard.assignPlayers(players[0]);

        this.gameStatus = new GameStatus(players[0], gameBoard);
        this.gameHistory = new GameHistory(gameStatus);
    }

    public void playIntersection(int[] coordinates, go.domain.Intersection.Occupation colour, boolean didPass){
        System.out.println("Received move on coordinates: [" + String.valueOf(coordinates[0]) + ", " + String.valueOf(coordinates[1]) + "]");
        if(!didPass) {
            this.gameHistory.resetNumberOfPasses();
            System.out.println("Player did not pass");
            if (gameStatus.validMove(coordinates, colour, this.gameHistory)) {
                System.out.println("A valid move was made.");
                this.gameBoard.playMove(coordinates, colour);
                players[0].switchTurn();
                gameHistory.addBoardState(gameBoard.getStringEncoding());
            }
        } else {
            System.out.println("Player did pass.");
            this.gameHistory.increaseNumberOfPasses();
            players[0].switchTurn();
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
