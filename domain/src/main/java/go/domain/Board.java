package go.domain;

public class Board {
    Intersection[][] intersections;

    Board(int boardSize) {
        this.intersections = new Intersection[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                intersections[i][j] = new Intersection();
            }
        }
    }
     
    Intersection getIntersection(int i, int j) {
        return intersections[i][j];
    }
    
}
