package go.api.models;

public class APIPlayer {
    public APIPlayer(go.domain.Player player, 
            String name, boolean isFirstPlayer) {
		this.name = name;
		type = isFirstPlayer ? "player1" : "player2";
        hasTurn = player.getTurn();
		var firstHole = isFirstPlayer ? 0 : 7;
    }
    
    String name;
	public String getName() { return name; }
	
	String type;
	public String getType() { return type; }

	boolean hasTurn;
	public boolean getHasTurn() { return hasTurn; }

}