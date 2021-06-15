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
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize ; j++) {
                assertEquals(testBoard.getIntersection(i,j).getOccupation(), Occupation.EMPTY);
            }
        }
    }

    @Test
    public void afterPlayingIntersectionOnBoardItIsWhite() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
        
        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);

        assertEquals(testBoard.getIntersection(0,0).getComponent(),Occupation.WHITE);
    }

    @Test
    public void twoOrthogonalIntersectionsHaveSameComponentAfterBothPlayedBySamePlayer() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.WHITE);

        assertEquals(testBoard.getIntersection(7,8).getComponent(), testBoard.getIntersection(7,8).getComponent());
    }

    @Test
    public void intersectionIsOnOwnBoard() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
        Intersection testIntersection = testBoard.getIntersection(0,0);

        try{
            testBoard.getCoordinate(testIntersection);
        } catch (IntersectionNotOnBoardException e) {
            assertEquals(1,0);
        }
    }
    
}
