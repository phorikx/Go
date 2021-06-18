package go.domain;

import go.domain.Intersection.Occupation;

public class GameStatus {
    private Player[] players;
    private Board gameBoard;



    public GameStatus(Player firstPlayer, Board gameBoard) {
        players = new Player[2];
        players[0] = firstPlayer;
        players[1] = firstPlayer.getOpponent();
        this.gameBoard = gameBoard;
    }

    public boolean validMove(int[] coordinates, Occupation colour, GameHistory gameHistory) {
        boolean validity = true;

        if(coordinates.length != 2) {
            validity = false;
            System.out.println("geen geldig format van coordinaten binnengekregen.");
        }
        if(coordinates[0] < 0 || coordinates[0] > gameBoard.getBoardSize() - 1|| coordinates[1] < 0 || coordinates[1] > gameBoard.getBoardSize() - 1) {
            validity = false;
            System.out.println("coordinaten zijn out of bounds.");
        } else if(gameBoard.getIntersection(coordinates[0], coordinates[1]).getOccupation() != Occupation.EMPTY) {
            validity = false;
            System.out.println("gekozen intersectie is al bezet.");
        }

        if((colour == Occupation.WHITE && players[0].getTurn()) || (colour == Occupation.BLACK && players[1].getTurn())) {
            validity = false;
            System.out.println("Spelende speler was niet aan zet.");
        }

        if (validity) {
            
            Board testBoard = new Board(this.gameBoard);
            testBoard.playMove(coordinates, colour);
            String encodedTestBoard = testBoard.getStringEncoding();
            if (gameHistory.contains(encodedTestBoard)) {
                validity = false;
                System.out.println("Move would result in earlier occuring state.");
            }
        }
        return validity;        
    }

}
