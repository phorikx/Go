package go.domain;

import org.junit.jupiter.api.Test;

import go.domain.Intersection.Occupation;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void boardHasDifferentIntersections() {
        Board testBoard = new Board(19);
        assertNotEquals(testBoard.getIntersection(18,18),testBoard.getIntersection(17,16));
    }

    @Test
    public void newBoardHasOnlyEmptyIntersections() {
        int boardSize = 10;
        Board testBoard = new Board(boardSize);
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize ; j++) {
                assertEquals(testBoard.getIntersection(i,j).getOccupation(), Occupation.EMPTY);
            }
        }
    }
    
}
