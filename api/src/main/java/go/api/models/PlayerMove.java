package go.api.models;

import java.util.ArrayList;

public class PlayerMove {
    String playerName;
    int firstCoordinatePlayed;
    int secondCoordinatePlayed;

    public String getPlayerName() {
        return playerName;
    }

    public Integer getFirstCoordinatePlayed() {
        return firstCoordinatePlayed;
    }

    public Integer getSecondCoordinatePlayed() {
        return secondCoordinatePlayed;
    }

    public ArrayList<Integer> getPlayIntersection() {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(this.firstCoordinatePlayed);
        coordinates.add(this.secondCoordinatePlayed);
        return coordinates;
    }

}
