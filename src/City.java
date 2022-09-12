import java.awt.*;

public class City {

    private boolean visited = false;
    private Point point;

    public City(Point point) {
        this.point = point;
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

}
