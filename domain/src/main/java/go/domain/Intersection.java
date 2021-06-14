package go.domain;


public class Intersection {
    enum Occupation{
        EMPTY,
        BLACK,
        WHITE
    }

    private Occupation occupation;

    Intersection() {
        this.occupation = Occupation.EMPTY;
    }

    public Occupation getOccupation() {
        return this.occupation;
    }
}