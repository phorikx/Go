package go.domain;

import java.util.ArrayList;

public class Intersection {
    public enum Occupation{
        EMPTY,
        BLACK,
        WHITE
    }

    private Occupation occupation;
    private Component component;
    private Board board;

    Intersection(Board containingBoard) {
        this.occupation = Occupation.EMPTY;
        this.component = new Component(this);
        this.board = containingBoard;
    }

    public Occupation getOccupation() {
        return this.occupation;
    }

    public Component getComponent() {
        return this.component;
    }

    public void playMove(Occupation playedColour) {
        if (playedColour != Occupation.EMPTY) {
            this.occupation = playedColour;
        }
        try {
            ArrayList<Intersection> neighbours = this.board.getNeighbours(this);
            for (Intersection neighbour : neighbours) {
                neighbour.mergeComponent(this.component);
            }
        } catch (Exception e) {
            System.out.println("Error: intersectie ligt niet op zijn eigen bord.");
        }
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public void mergeComponent(Component component) {
        this.component.mergeWithComponent(component);
    }
}