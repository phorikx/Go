package go.domain;

import java.util.ArrayList;

public class Component {
    private ArrayList<Intersection> intersections;
    Component(Intersection buildingIntersection) {
        this.intersections = new ArrayList<Intersection>();
        intersections.add(buildingIntersection);
    }

    public void mergeWithComponent(Component neighbouringComponent) {
        for (Intersection elementOfThisComponent : this.intersections) {
            elementOfThisComponent.setComponent(neighbouringComponent);
            neighbouringComponent.addIntersection(elementOfThisComponent);
        }
    }

    public void addIntersection(Intersection intersection) {
        this.intersections.add(intersection);
    }
}
