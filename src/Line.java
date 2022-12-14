import java.awt.*;

public class Line {

    private final Point a;
    private final Point b;
    private boolean traversed = false;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

}
