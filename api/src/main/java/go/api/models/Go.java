package go.api.models;

import go.domain.*;
import go.domain.Intersection.Occupation;

public class Go {
    public Go(go.domain.GoImpl go, String namePlayer1, String namePlayer2) {
        players = new APIPlayer[2];
        players[0] = new APIPlayer(go.getFirstPlayer(), namePlayer1, true);
        players[1] = new APIPlayer(go.getSecondPlayer(), namePlayer2, false);
        gameStatus = new APIGameStatus(go, namePlayer1, namePlayer2);

        int boardSize = go.getBoard().getBoardSize();
        this.board = new String[boardSize][boardSize];
        this.boardSize = boardSize;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Occupation colour = go.getBoard().getIntersection(i, j).getOccupation();
                if (colour == Occupation.WHITE) {
                    board[i][j] = "W";
                } else if (colour == Occupation.BLACK) {
                    board[i][j] = "B";
                } else{
                    board[i][j] = "E";
                }
            }
        }

    }

    APIPlayer[] players;
    public APIPlayer[] getPlayers() { return players; }

    String[][] board;
    public String[][] getBoard() {return board; }

    APIGameStatus gameStatus;
    public APIGameStatus getGameStatus() { return gameStatus; }

    int boardSize;
    public int getBoardSize() {return boardSize;}
}
