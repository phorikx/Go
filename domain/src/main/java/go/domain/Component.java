package go.domain;

import java.util.ArrayList;

import go.domain.Intersection.Occupation;

public class Component {
    private ArrayList<Intersection> intersections;
    Component(Intersection buildingIntersection) {
        this.intersections = new ArrayList<Intersection>();
        intersections.add(buildingIntersection);
    }

    public void mergeWithComponent(Component neighbouringComponent) {
        for (Intersection elementOfThisComponent : this.intersections) {
            neighbouringComponent.addIntersection(elementOfThisComponent);
            elementOfThisComponent.setComponent(neighbouringComponent);            
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
            this.intersections.get(0).getBoard().getFirstPlayer().getOpponent().giveStones(componentSize);
        } else if(componentColour == Occupation.WHITE) {
            this.intersections.get(0).getBoard().getFirstPlayer().giveStones(componentSize);
        }
        for (Intersection element : this.intersections) {
            element.removeStone();
        }
    }

    public void addIntersection(Intersection intersection) {
        this.intersections.add(intersection);
    }
}
