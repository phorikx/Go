package go.domain;

import go.domain.Intersection.Occupation;

public class GameStatus {
    private Player[] players;
    private Board gameBoard;
    private boolean endOfGame;
    private double[] score;

    public boolean getEndOfGame() {
        return endOfGame;
    }

    public GameStatus(Player firstPlayer, Board gameBoard) {
        players = new Player[2];
        players[0] = firstPlayer;
        players[1] = firstPlayer.getOpponent();
        this.gameBoard = gameBoard;
        endOfGame = false;
        this.score = new double[] {0,0};
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
            Board testBoard = new Board(this.gameBoard, true);
            testBoard.playMove(coordinates, colour);
            String encodedTestBoard = testBoard.getStringEncoding();
            if (gameHistory.contains(encodedTestBoard)) {
                validity = false;
                System.out.println("Move would result in earlier occuring state.");
            }
        }
        return validity;        
    }

    public void endGame() {
        this.endOfGame = true;
    }

    public double[] calculatePointsperPlayer() {
        double[] pointsPerPlayer = new double[]{0,0};
        pointsPerPlayer[0] += this.players[0].getCapturedStones();
        pointsPerPlayer[1] += this.players[1].getCapturedStones();
        pointsPerPlayer[0] += this.gameBoard.calculateEndOfGamePoints()[0];
        pointsPerPlayer[1] += this.gameBoard.calculateEndOfGamePoints()[1];
        pointsPerPlayer[1] += GoImpl.komi;
        this.score = pointsPerPlayer;
        return pointsPerPlayer;
    }

    public double[] getScore() {
        return this.score;
    }

    public Player getWinner() {
        double[] pointsPerPlayer = new double[]{0,0};
        if(this.endOfGame) {
            pointsPerPlayer = this.calculatePointsperPlayer();
        }
        if (pointsPerPlayer[0]>pointsPerPlayer[1]) {
            return this.players[0];
        }
        return players[1];
    }

}
