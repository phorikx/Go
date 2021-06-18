package go.domain;

import java.util.ArrayList;

public class GameHistory {
    ArrayList<String> earlierGameStates;
    
    public GameHistory() {
        earlierGameStates = new ArrayList<String>();
    }

    public void addBoardState(String encodedBoardState) {
        this.earlierGameStates.add(encodedBoardState);
    }

    public boolean contains(String encodedBoardstate) {
        boolean containsBoard = earlierGameStates.contains(encodedBoardstate);
        return containsBoard;
    }

}
