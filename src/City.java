import java.awt.*;

public class City {

    private boolean visited = false;
    private final Point point;
    private final String name;

    public City(Point point, String name) {
        this.point = point;
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {
        return name;
    }
}
