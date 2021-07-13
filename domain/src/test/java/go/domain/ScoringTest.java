package go.domain;

import org.junit.jupiter.api.Test;

import go.domain.Intersection.Occupation;

import static org.junit.jupiter.api.Assertions.*;


public class ScoringTest {

    @Test
    public void afterPlayingOneIntersectionBlackHas100Points() {
        GoImpl go = new GoImpl(10);

        go.playIntersection(new int[]{0,0}, Occupation.BLACK, false);

        assertEquals(go.getGameStatus().calculatePointsperPlayer()[0], 100);
    }

    @Test
    public void afterPlayingTwoIntersectionsBothHave1PointPlusKomi() {
        GoImpl go = new GoImpl(10);

        go.playIntersection(new int[]{0,0}, Occupation.BLACK, false);
        go.playIntersection(new int[]{1,0}, Occupation.WHITE, false);

        assertEquals(go.getGameStatus().calculatePointsperPlayer()[0], 1);
        assertEquals(go.getGameStatus().calculatePointsperPlayer()[1], 6.5);
    }

    @Test
    public void capturedStonesCountInScoring() {
        GoImpl go = new GoImpl(10);

        go.playIntersection(new int[]{0,0}, Occupation.BLACK, false);
        go.playIntersection(new int[]{1,0}, Occupation.WHITE, false);
        go.playIntersection(new int[]{1,1}, Occupation.BLACK, false);
        go.playIntersection(new int[]{5,5}, Occupation.WHITE, false);
        go.playIntersection(new int[]{2,0}, Occupation.BLACK, false);

        assertEquals(go.getGameStatus().calculatePointsperPlayer()[0], 5);
        assertEquals(go.getGameStatus().calculatePointsperPlayer()[1], 6.5);
    }

    @Test
    public void passingDoesNotAffectScoring() {
        GoImpl go = new GoImpl(10);

        go.playIntersection(new int[]{0,0}, Occupation.BLACK, false);
        go.playIntersection(new int[]{0,0}, Occupation.WHITE, true);
        go.playIntersection(new int[]{0,0}, Occupation.BLACK, true);

        assertEquals(go.getGameStatus().calculatePointsperPlayer()[0], 100);

    }
    
}
