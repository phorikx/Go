package go.domain;

import org.junit.jupiter.api.Test;

import go.domain.Intersection.Occupation;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    @Test
    public void intersectionStartsEmpty() {
        Intersection intersection = new Intersection(null);
        assertEquals(intersection.getOccupation(), Occupation.EMPTY);
    }

    @Test
    public void intersectionHasComponent() {
        Intersection intersection = new Intersection(null);
        assertNotNull(intersection.getComponent());
    }

    @Test
    public void intersectionChangesColourWhenPlayed() {
        Intersection intersection = new Intersection(null);
        intersection.playMove(Occupation.WHITE);
        assertEquals(intersection.getOccupation(), Occupation.WHITE);
    }

    @Test
    public void cannotPlayNonEmptyIntersection() {
        Intersection intersection = new Intersection(null);
        intersection.playMove(Occupation.WHITE);
        intersection.playMove(Occupation.BLACK);
        assertEquals(intersection.getOccupation(), Occupation.WHITE);
    }



}