package go.domain;

import go.domain.Intersection.Occupation;
import java.util.ArrayList;

public class Board {
    Intersection[][] intersections;
    int boardSize;
    Player firstPlayer;
    Player secondPlayer;

    Board(int boardSize) {
        this.boardSize = boardSize;
        this.intersections = new Intersection[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                intersections[i][j] = new Intersection(this);
            }
        }
    }
     
    public Intersection getIntersection(int i, int j) {
        return intersections[i][j];
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void playMove(int[] coordinates, Occupation colourPlayed) {
        System.out.println("Play a move.");
        Intersection playedIntersection = this.getIntersection(coordinates[0],coordinates[1]);
        playedIntersection.playMove(colourPlayed);
    }

    public int[] getCoordinate(Intersection intersection) throws IntersectionNotOnBoardException {
        System.out.println("getting Coordinate of Intersection.");
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.intersections[i][j] == intersection) {
                    System.out.print("Intersection has been found.");
                    return new int[]{i,j};                    
                }
            }
        }
        System.out.println("Intersection Coordinates not found.");
        throw new IntersectionNotOnBoardException("e");
        
    }

    public ArrayList<Intersection> getNeighbours(Intersection intersection) throws IntersectionNotOnBoardException{
        System.out.println("Getting neighbours for intersection.");
        int[] coordinates = this.getCoordinate(intersection);
        ArrayList<Intersection> neighbours = new ArrayList<Intersection>();
        if(coordinates[0] > 0) {
            neighbours.add(this.getIntersection(coordinates[0]-1, coordinates[1]));
        }
        if(coordinates[0] < this.boardSize - 1) {
            neighbours.add(this.getIntersection(coordinates[0]+1, coordinates[1]));
        }   
        if(coordinates[1] > 0) {
            neighbours.add(this.getIntersection(coordinates[0], coordinates[1]-1));
        }
        if(coordinates[1] < this.boardSize - 1) {
            neighbours.add(this.getIntersection(coordinates[0], coordinates[1]+1));
        }   
        return neighbours;

    }

    public Player getFirstPlayer() {
        return this.firstPlayer;
    }
    
    public Player getSecondPlayer() {
        return this.secondPlayer;
    }

    public void assignPlayers(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = firstPlayer.getOpponent();
    }
}
