package test;

public class Location {
    private long x;
    private float y;
    private Long z; //Поле не может быть null

    public Location(long x, float y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
