package go.api.models;

public class APIPlayer {
    public APIPlayer(go.domain.Player player, 
            String name, boolean isFirstPlayer) {
		this.name = name;
		type = isFirstPlayer ? "player1" : "player2";
        hasTurn = player.getTurn();
		score = player.getCapturedStones();
    }
    
    String name;
	public String getName() { return name; }
	
	String type;
	public String getType() { return type; }

	boolean hasTurn;
	public boolean getHasTurn() { return hasTurn; }

	int score;
	public int getScore() { return score;}

}