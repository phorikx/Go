package go.domain;

import go.domain.Intersection.Occupation;
import java.util.ArrayList;

public class Board {
    Intersection[][] intersections;
    int boardSize;
    Player firstPlayer;
    Player secondPlayer;
    private boolean testBoard = false;

    public boolean getIsTestBoard() {
        return testBoard;
    }

    Board(int boardSize) {
        this.boardSize = boardSize;
        this.intersections = new Intersection[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                intersections[i][j] = new Intersection(this);
            }
        }
    }

    Board(Board previousBoard, boolean testBoard) {
        this.testBoard = true;
        this.boardSize = previousBoard.boardSize;
        this.firstPlayer = previousBoard.firstPlayer;
        this.secondPlayer = previousBoard.secondPlayer;
        this.intersections = new Intersection[boardSize][boardSize];
        ArrayList<Component> originalComponents = new ArrayList<Component>();
        ArrayList<Component> correspondingNewComponents = new ArrayList<Component>();
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++) {
                this.intersections[i][j] = new Intersection(this);
                int indexOfComponent = originalComponents.indexOf(previousBoard.getIntersection(i,j).getComponent());
                if (indexOfComponent >= 0){
                    intersections[i][j].setComponent(correspondingNewComponents.get(indexOfComponent));
                } else{
                    originalComponents.add(previousBoard.getIntersection(i,j).getComponent());
                    correspondingNewComponents.add(this.intersections[i][j].getComponent());
                }
                intersections[i][j].setOccupation(previousBoard.getIntersection(i,j).getOccupation());
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
        Intersection playedIntersection = this.getIntersection(coordinates[0],coordinates[1]);
        playedIntersection.playMove(colourPlayed);
    }

    public int[] getCoordinate(Intersection intersection) throws IntersectionNotOnBoardException {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.intersections[i][j] == intersection) {
                    return new int[]{i,j};                    
                }
            }
        }
        System.out.println("Intersection Coordinates not found.");
        throw new IntersectionNotOnBoardException("e");
        
    }

    public ArrayList<Intersection> getNeighbours(Intersection intersection) throws IntersectionNotOnBoardException{
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

    public String getStringEncoding() {
        String encodedBoard = "";
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (intersections[i][j].getOccupation() == Occupation.WHITE) {
                    encodedBoard += "W";
                } else if (intersections[i][j].getOccupation() == Occupation.BLACK) {
                    encodedBoard += "B";
                } else {
                    encodedBoard += "E";
                }
            }
        }
        return encodedBoard;
    }

    public void connectEmptyIntersections() {
        for(int i = 0; i < this.boardSize; i++){
            for (int j = 0; j < this.boardSize; j++) {
                if (this.intersections[i][j].getOccupation() == Occupation.EMPTY) {
                    try{
                        ArrayList<Intersection> neighbours = this.getNeighbours(intersections[i][j]);
                        for (Intersection neighbour : neighbours) {
                            if (neighbour.getOccupation() == Occupation.EMPTY) {
                                neighbour.mergeComponent(intersections[i][j].getComponent());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                }
            }
        }
    }

    public int[] countPointsAfterConnection() {
        int[] endOfGamePoints = new int[]{0,0};
        for(int i = 0; i < this.boardSize; i++){
            for (int j = 0; j < this.boardSize; j++) {
                if(this.intersections[i][j].getOccupation() == Occupation.BLACK) {
                    endOfGamePoints[0]++;
                } else if(this.intersections[i][j].getOccupation() == Occupation.WHITE) {
                    endOfGamePoints[1]++;
                } else if(this.intersections[i][j].getOccupation() == Occupation.EMPTY) {
                    if (!this.intersections[i][j].getComponent().getIsCounted()) {
                        int[] areaScorePerPlayer = this.intersections[i][j].getComponent().Count();
                        endOfGamePoints[0] += areaScorePerPlayer[0];
                        endOfGamePoints[1] += areaScorePerPlayer[1];                        
                    }
                }
            }
        }
        return endOfGamePoints;
    }

    public int[] calculateEndOfGamePoints() {
        int[] endOfGamePoints;
        this.connectEmptyIntersections();
        endOfGamePoints = this.countPointsAfterConnection();
        return endOfGamePoints;
    }
}
