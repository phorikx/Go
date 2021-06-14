package go.domain;

import org.junit.jupiter.api.Test;

import go.domain.Intersection.Occupation;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    @Test
    public void intersectionStartsEmpty() {
        Intersection intersection = new Intersection();
        assertEquals(intersection.getOccupation(), Occupation.EMPTY);
    }

    @Test
    public void 
}