package go.domain;

import java.util.ArrayList;

import go.domain.Intersection.Occupation;

public class Component {
    private ArrayList<Intersection> intersections;
    private boolean isCounted = false;

    Component(Intersection buildingIntersection) {
        this.intersections = new ArrayList<Intersection>();
        intersections.add(buildingIntersection);
    }

    public boolean getIsCounted() {
        return isCounted;
    }

    public int[] Count() {
        int[] pointsPerPlayer = new int[]{0,0};
        this.isCounted = true;
        Occupation lastColour = null;
        boolean allSameColour = true;
        if(this.intersections.get(0).getOccupation() == Occupation.EMPTY) {
            for (Intersection elementOfComponent : intersections) {
                try{
                    Board gameBoard = this.intersections.get(0).getBoard();
                    ArrayList<Intersection> neighbours = gameBoard.getNeighbours(elementOfComponent);
                    for (Intersection neighbour : neighbours) {
                        Occupation neighbourColour = neighbour.getOccupation();
                        if (neighbourColour != Occupation.EMPTY) {
                            if (lastColour == null) {
                                lastColour = neighbourColour;
                            } else if(neighbourColour != lastColour) {
                                allSameColour = false;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong.");
                }
            }
            if (allSameColour) {
                if (lastColour == Occupation.BLACK) {
                    pointsPerPlayer[0] += intersections.size();
                } else if (lastColour == Occupation.WHITE) {
                    pointsPerPlayer[1] += intersections.size();
                }
            }
        }
        this.isCounted = true;
        return pointsPerPlayer;

    }

    public void mergeWithComponent(Component neighbouringComponent) {
        for (Intersection elementOfThisComponent : this.intersections) {
            if(elementOfThisComponent.getComponent() != neighbouringComponent) {
                neighbouringComponent.addIntersection(elementOfThisComponent);
                elementOfThisComponent.setComponent(neighbouringComponent);
            }            
        }
    }

    public boolean componentHasFreedoms() {
        boolean hasFreedoms = false;
        for (Intersection element : this.intersections) {
            if (element.getFreedoms() > 0) {
                hasFreedoms = true;
            }
        }
        return hasFreedoms;
    }

    public void removeComponent() {
        int componentSize = this.intersections.size();
        System.out.println(componentSize);
        Occupation componentColour = this.intersections.get(0).getOccupation();
        if (componentColour == Occupation.BLACK) {
            if (!this.intersections.get(0).getBoard().getIsTestBoard()){
                this.intersections.get(0).getBoard().getFirstPlayer().getOpponent().giveStones(componentSize);
            }
        } else if(componentColour == Occupation.WHITE) {
            if (!this.intersections.get(0).getBoard().getIsTestBoard()){
                this.intersections.get(0).getBoard().getFirstPlayer().giveStones(componentSize);
            }
        }
        for (Intersection element : this.intersections) {
            element.removeStone();
        }
    }

    public void addIntersection(Intersection intersection) {
        this.intersections.add(intersection);
    }
}
