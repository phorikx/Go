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
        System.out.println("Played a move on an Intersection.");
        if (playedColour != Occupation.EMPTY) {
            this.occupation = playedColour;
        }
        try {
            ArrayList<Intersection> neighbours = this.board.getNeighbours(this);
            System.out.println(neighbours.size());
            for (Intersection neighbour : neighbours) {
                System.out.println("merging components with neighbours.");
                if (neighbour.getOccupation() == this.occupation) {
                    neighbour.mergeComponent(this.component);
                }
            }
            ArrayList<Intersection> intersectionsToCheck = neighbours;
            intersectionsToCheck.add(this);
            checkIntersectionComponentFreedoms(intersectionsToCheck);
        } catch (Exception e) {
            System.out.println("Error: intersectie ligt niet op zijn eigen bord.");
        }
    }

    public void checkIntersectionComponentFreedoms (ArrayList<Intersection> intersectionsToCheck) {
        for(Intersection intersection : intersectionsToCheck) {
            if(!intersection.getComponent().componentHasFreedoms()) {
                intersection.getComponent().removeComponent();
            }
        }

    }

    public void setComponent(Component component) {
        System.out.println("Set the component.");
        this.component = component;
    }

    public void mergeComponent(Component component) {
        System.out.println("Merged component with another component.");
        this.component.mergeWithComponent(component);
    }

    public int getFreedoms() {
        int freedoms = 0;
        try{
            ArrayList<Intersection> neighbours = this.board.getNeighbours(this);            
            for (Intersection neighbour : neighbours) {
                if(neighbour.getOccupation() == Occupation.EMPTY) {
                    freedoms++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: intersectie ligt niet op zijn eigen bord.");
        }
        return freedoms;

    }

    public void removeStone() {
        this.occupation = Occupation.EMPTY;
    }

    public Board getBoard() {
        return this.board;
    }
}