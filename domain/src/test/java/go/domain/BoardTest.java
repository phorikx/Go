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

    @Test
    public void afterPlayingIntersectionOnBoardItIsWhite() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
        
        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);

        assertEquals(testBoard.getIntersection(0,0).getOccupation(),Occupation.WHITE);
    }

    @Test
    public void twoOrthogonalIntersectionsHaveSameComponentAfterBothPlayedBySamePlayer() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.WHITE);

        System.out.println("Next is the assert.");

        assertEquals(testBoard.getIntersection(0,0).getComponent(), testBoard.getIntersection(1,0).getComponent());
    }

    @Test
    public void twoOrthogonalIntersectionsHaveDifferentComponentAfterPlayedByDifferentPlayer() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);
        System.out.println("Next is the assert.");

        assertNotEquals(testBoard.getIntersection(0,0).getComponent(), testBoard.getIntersection(0,1).getComponent());
    }

    @Test
    public void twodisjointIntersectionsAreConnectedByStoneInbetween() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{2,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.WHITE);

        assertEquals(testBoard.getIntersection(0,0).getComponent(), testBoard.getIntersection(2,0).getComponent());
    }

    @Test
    public void surroundedStoneInCornerDissappears() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);
        testBoard.playMove(new int[]{2,2}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0, 0).getOccupation(), Occupation.EMPTY);
    }

    @Test
    public void intersectionInEmptyCornerHasTwoFreedoms() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,0).getFreedoms(),2);
    }

    @Test
    public void intersectionInMiddleHasFourFreedoms() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{5,5}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(5,5).getFreedoms(),4);
    }

    @Test
    public void intersectionInEdgeHasThreeFreedoms() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
        
        testBoard.playMove(new int[]{0,3}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,3).getFreedoms(),3);
    }

    @Test
    public void surroundedStoneHasNoFreedoms() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,0).getFreedoms(), 0);
    }

    @Test
    public void surroundedStonesComponentHasNoFreedoms() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,0).getComponent().componentHasFreedoms(),false);
    }

    @Test
    public void stoneAboveGetsPlayed() {
    int boardSize = 11;
    Board testBoard = new Board(boardSize);

    testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
    testBoard.playMove(new int[]{1,0}, Occupation.BLACK);

    assertEquals(testBoard.getIntersection(0,0).getFreedoms(),1);
    }

    @Test
    public void stoneLeftGetsPlayed() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
    
        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);
    
        assertEquals(testBoard.getIntersection(0,0).getFreedoms(),1);
        
    }

    @Test
    public void entireComponentGetsRemoved() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        
        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);
        testBoard.playMove(new int[]{1,1}, Occupation.BLACK);
        testBoard.playMove(new int[]{0,2}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,0).getOccupation(), Occupation.EMPTY);
        assertEquals(testBoard.getIntersection(0,1).getOccupation(), Occupation.EMPTY);

    }

    @Test
    public void stoneDoesNotGetRemovedIfAdjacentStoneStillHasFreedoms() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        
        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);
        testBoard.playMove(new int[]{1,1}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,0).getOccupation(), Occupation.WHITE);
    }


    @Test 
    public void playingMoveWithoutFreedomsAutomaticallyRemovesStone() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        
        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,1}, Occupation.WHITE);
        testBoard.playMove(new int[]{2,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(1,0).getOccupation(), Occupation.EMPTY);
    }
    @Test 
    public void canPlayMoveWithNoFreedomsIfItRemovesOtherStones() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        
        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);
        testBoard.playMove(new int[]{0,2}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,1}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(1,0).getOccupation(), Occupation.BLACK);

    }

    @Test 
    public void moveWithNoFreedomsRemovesStoneWhichItTakesLastFreedomFrom() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);

        
        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);
        testBoard.playMove(new int[]{0,2}, Occupation.WHITE);
        testBoard.playMove(new int[]{1,1}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);

        assertEquals(testBoard.getIntersection(0,0).getOccupation(), Occupation.EMPTY);
    }



    @Test 
    public void repeatMoveIsNotAllowed() {
        GoImpl go = new GoImpl(11);

        go.playIntersection(new int[]{0,0}, Occupation.BLACK);
        go.playIntersection(new int[]{1,0}, Occupation.WHITE);
        go.playIntersection(new int[]{1,1}, Occupation.BLACK);
        go.playIntersection(new int[]{3,0}, Occupation.WHITE);
        go.playIntersection(new int[]{4,0}, Occupation.BLACK);
        go.playIntersection(new int[]{2,1}, Occupation.WHITE);
        go.playIntersection(new int[]{2,0}, Occupation.BLACK);
        go.playIntersection(new int[]{1,0}, Occupation.WHITE);

        assertEquals(go.getBoard().getIntersection(1,0).getOccupation(), Occupation.EMPTY);
    }
}
