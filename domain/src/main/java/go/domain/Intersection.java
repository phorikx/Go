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

    void setOccupation(Occupation colour) {
        this.occupation = colour;
    }

    public Component getComponent() {
        return this.component;
    }

    public void playMove(Occupation playedColour) {
        if (playedColour != Occupation.EMPTY && this.occupation == Occupation.EMPTY) {
            this.occupation = playedColour;
        }
        try {
            ArrayList<Intersection> neighbours = this.board.getNeighbours(this);
            System.out.println("Size of the neighbours: " + String.valueOf(neighbours.size()));
            ArrayList<Intersection> neighboursWithDifferentColour = new ArrayList<Intersection>();
            for (Intersection neighbour : neighbours) {                
                if (neighbour.getOccupation() == this.occupation) {
                    System.out.println("merging components with neighbours.");
                    neighbour.mergeComponent(this.component);
                } else  {
                    neighboursWithDifferentColour.add(neighbour);
                }
            }
            ArrayList<Intersection> ownIntersection = new ArrayList<Intersection>();
            ownIntersection.add(this);
            //checkIntersectionComponentFreedoms(neighboursWithDifferentColour);
            System.out.println("We gaan de vrijheden checken.");
            checkIntersectionComponentFreedoms(neighbours);
            checkIntersectionComponentFreedoms(ownIntersection);
        } catch (Exception e) {
            System.out.println("Error: intersectie ligt niet op zijn eigen bord.");
        }
    }

    public void checkIntersectionComponentFreedoms (ArrayList<Intersection> intersectionsToCheck) {
        for(Intersection intersection : intersectionsToCheck) {
            System.out.println("We kijken of de componenten nog vrijheden heeft.");
            if ((!intersection.getComponent().componentHasFreedoms()) && (intersection.getOccupation() != Occupation.EMPTY)) {
                System.out.println("Connection has to be removed.");
                intersection.getComponent().removeComponent();
            } else{
                System.out.println("Hij had nog vrijheden.");
            }
        }

    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public void mergeComponent(Component component) {
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
        Component emptyComponent = new Component(this);
        this.setComponent(emptyComponent);
    }

    public Board getBoard() {
        return this.board;
    }
}