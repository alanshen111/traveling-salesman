import javax.swing.*;

public class TravelingSalesmanGUI {

    public TravelingSalesmanGUI() {
        JFrame frame = new JFrame();

        MapPanel map = new MapPanel();
        frame.add(map);

        frame.setSize(610,660);
        frame.setVisible(true);

        map.paintGreedy();
        map.repaint();
    }

}
