import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelingSalesmanGUI {

    public TravelingSalesmanGUI() {
        JFrame frame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Visualize");
        JMenuItem menuItem = new JMenuItem("Greedy");

        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        MapPanel map = new MapPanel();
        frame.add(map);

        frame.setSize(610,660);
        frame.setVisible(true);
        menuItem.addActionListener(e -> map.paintGreedy());

        map.repaint();
    }

}
