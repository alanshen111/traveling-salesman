import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class MapPanel extends JPanel {

    // approximated from https://www.mobilefish.com/services/record_mouse_coordinates/record_mouse_coordinates.php
    private static final int VICTORIA_X = 139;
    private static final int VICTORIA_Y = 515;
    private static final int EDMONTON_X = 211;
    private static final int EDMONTON_Y = 481;
    private static final int REGINA_X = 262;
    private static final int REGINA_Y = 510;
    private static final int WINNIPEG_X = 305;
    private static final int WINNIPEG_Y = 514;
    private static final int TORONTO_X = 397;
    private static final int TORONTO_Y = 561;
    private static final int QUEBEC_CITY_X = 426;
    private static final int QUEBEC_CITY_Y = 542;
    private static final int FREDERICTON_X = 447;
    private static final int FREDERICTON_Y = 545;
    private static final int CHARLOTTETOWN_X = 463;
    private static final int CHARLOTTETOWN_Y = 547;
    private static final int HALIFAX_X = 469;
    private static final int HALIFAX_Y = 559;
    private static final int SAINT_JOHNS_X = 511;
    private static final int SAINT_JOHNS_Y = 535;
    private static final int WHITEHORSE_X = 135;
    private static final int WHITEHORSE_Y = 417;
    private static final int YELLOWKNIFE_X = 240;
    private static final int YELLOWKNIFE_Y = 400;
    private static final int IQALUIT_X = 443;
    private static final int IQALUIT_Y = 401;
    private final List<City> cityList = new ArrayList<>();
    private final double[][] cityMatrix;
    private Graphics g;

    public MapPanel() {

        cityList.add(new City(new Point(VICTORIA_X,VICTORIA_Y), "Victoria"));
        cityList.add(new City(new Point(EDMONTON_X,EDMONTON_Y), "Edmonton"));
        cityList.add(new City(new Point(REGINA_X,REGINA_Y), "Regina"));
        cityList.add(new City(new Point(WINNIPEG_X,WINNIPEG_Y), "Winnipeg"));
        cityList.add(new City(new Point(TORONTO_X,TORONTO_Y), "Toronto"));
        cityList.add(new City(new Point(QUEBEC_CITY_X,QUEBEC_CITY_Y), "Quebec City"));
        cityList.add(new City(new Point(FREDERICTON_X,FREDERICTON_Y), "Fredericton"));
        cityList.add(new City(new Point(CHARLOTTETOWN_X,CHARLOTTETOWN_Y), "Charlottetown"));
        cityList.add(new City(new Point(HALIFAX_X,HALIFAX_Y), "Halifax"));
        cityList.add(new City(new Point(SAINT_JOHNS_X,SAINT_JOHNS_Y), "Saint John's"));
        cityList.add(new City(new Point(WHITEHORSE_X,WHITEHORSE_Y), "Whitehorse"));
        cityList.add(new City(new Point(YELLOWKNIFE_X,YELLOWKNIFE_Y), "Yellowknife"));
        cityList.add(new City(new Point(IQALUIT_X,IQALUIT_Y), "Iqaluit"));
        cityMatrix = new double[cityList.size()][cityList.size()];

        for (int i = 0; i < cityMatrix.length; i++) {
            for (int j = 0; j < cityMatrix[i].length; j++) {
                cityMatrix[i][j] = getDistance(cityList.get(i).getPoint(),cityList.get(j).getPoint());
            }
        }

        cityList.get(0).setVisited(true);

    }

    private double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x),2) + Math.pow((a.y - b.y),2));
    }

    private int getNearestUnvisited(int i) throws Exception {
        double min = Double.MAX_VALUE;
        int minIndex = -1;
        for (int j = 0; j < cityMatrix[i].length; j++) {
            if (cityMatrix[i][j] < min && !cityList.get(j).isVisited()) {
                min = cityMatrix[i][j];
                minIndex = j;
            }
        }
        if (minIndex == -1) {
            throw new Exception("There is no nearest unvisited City.");
        }
        return minIndex;
    }

    Queue<City> paintQueue = new LinkedList<>();

    public void paintGreedy() {
        int current = 0;
        paintQueue.add(cityList.get(0));
        for (int i = 0; i < cityList.size()-1; i++) {
            try {
                paintQueue.add(cityList.get(getNearestUnvisited(current)));
                current = getNearestUnvisited(current);
                cityList.get(current).setVisited(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void reset() {
        for (City city: cityList) {
            if (!city.getName().equals("Victoria")) city.setVisited(false);
        }
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        this.g = g2d;
        try {
            BufferedImage image = ImageIO.read(new File("src/map.jpg"));
            Image img = image.getScaledInstance(610,610, Image.SCALE_SMOOTH);
            this.g.drawImage(img, 0, 0, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (City city: cityList) {
            Color c = city.getName().equals("Victoria") ? Color.green : Color.red;
            this.g.setColor(c);
            this.g.fillOval(city.getPoint().x, city.getPoint().y, 15, 15);
        }

        if (!paintQueue.isEmpty()) {
            City city = paintQueue.poll();
            System.out.println(city.getName());
            city.setVisited(true);
            this.g.setColor(Color.green);
            this.g.fillOval(city.getPoint().x, city.getPoint().y, 15, 15);
            repaint();
        } else {
            reset();
        }

    }

    public List<City> getCityList() {
        return cityList;
    }

}

