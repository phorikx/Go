package go.domain;

import org.junit.jupiter.api.Test;

import go.domain.Intersection.Occupation;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test 
    public void opponentOfPlayerIsHimself() {
        Player player = new Player(null);
        assertEquals(player, player.getOpponent().getOpponent());
    }

    @Test
    public void onlyOnePlayerHasTurn() {
        Player player = new Player(null);
        assertEquals(player.getTurn(), !player.getOpponent().getTurn());
    }

    @Test
    public void playerGetsStonesAfterTheyGetRemoved() {
        int boardSize = 11;
        Board testBoard = new Board(boardSize);
        Player firstPlayer = new Player(null);
        testBoard.assignPlayers(firstPlayer);

        testBoard.playMove(new int[]{0,0}, Occupation.WHITE);
        testBoard.playMove(new int[]{0,1}, Occupation.BLACK);
        testBoard.playMove(new int[]{1,0}, Occupation.BLACK);

        assertEquals(firstPlayer.getCapturedStones(), 1);

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

        assertEquals(firstPlayer.getCapturedStones(),2);

    }
}
