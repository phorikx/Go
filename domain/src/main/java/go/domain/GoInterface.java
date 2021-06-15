package go.domain;

public interface GoInterface {
    public static final int NO_PLAYERS = 0;
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;
    public static final int BOTH_PLAYERS = 3;

    void playIntersection(int[] index);
    /*

    boolean isPlayersTurn(int player);

    int getPlayerPoints(int player);

    boolean isEndOfGame();
    
    int getWinner();*/
}
