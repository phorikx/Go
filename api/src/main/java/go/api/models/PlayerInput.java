package go.api.models;

public class PlayerInput {
    String nameplayer1;
	String nameplayer2;
	int boardSize;

	public String getNameplayer1() {
		return nameplayer1;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setNameplayer1(String nameplayer1) {
		this.nameplayer1 = nameplayer1;
	}

	public String getNameplayer2() {
		return nameplayer2;
	}

	public void setNameplayer2(String nameplayer2) {
		this.nameplayer2 = nameplayer2;
	}
}
