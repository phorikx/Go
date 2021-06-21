package go.domain;

import java.util.ArrayList;

public class GameHistory {
    ArrayList<String> earlierGameStates;
    int numberOfPasses;
    GameStatus gameStatus;
    
    public GameHistory(GameStatus gameStatus) {
        earlierGameStates = new ArrayList<String>();
        this.gameStatus = gameStatus;
    }

    public void addBoardState(String encodedBoardState) {
        this.earlierGameStates.add(encodedBoardState);
    }

    public boolean contains(String encodedBoardstate) {
        boolean containsBoard = earlierGameStates.contains(encodedBoardstate);
        return containsBoard;
    }

    public void increaseNumberOfPasses() {
        numberOfPasses++;
        System.out.println("Number of passes:" + String.valueOf(numberOfPasses));
        if (numberOfPasses >= 2) {
            gameStatus.endGame();
            System.out.println("We have more than 2 passes in a row.");
        }
    }

    public void resetNumberOfPasses() {
        numberOfPasses = 0;
    }

}
