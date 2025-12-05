import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class Driver {

    // simple polyline that does NOT close back to the first point
    public static class MapPolyLine extends MapPolygonImpl {
        public MapPolyLine(List<Coordinate> points) {
            super(null, null, points);
        }

        @Override
        public void paint(java.awt.Graphics g, java.util.List<java.awt.Point> points) {
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
            g2d.setColor(getColor());
            g2d.setStroke(getStroke());

            java.awt.geom.Path2D path = new java.awt.geom.Path2D.Double();
            if (points != null && points.size() > 0) {
                java.awt.Point first = points.get(0);
                path.moveTo(first.getX(), first.getY());
                for (java.awt.Point p : points) {
                    path.lineTo(p.getX(), p.getY());
                }
            }

            g2d.draw(path);
            g2d.dispose();
        }
    }

    // GUI components
    private static JComboBox<Integer> timePicker;
    private static JCheckBox includeStopsBox;
    private static JButton playButton;
    private static JMapViewer mapViewer;

    // Animation data
    private static Timer animationTimer;
    private static int currentIndex;
    private static List<Coordinate> pathCoords = new ArrayList<Coordinate>();
    private static IconMarker raccoonMarker;
    private static Image raccoonImage;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // load data + build movingtrip
        TripPoint.readFile("triplog.csv");
        TripPoint.h1StopDetection();   // fills movingtrip for getMovingTrip()

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Project 5 - Logan Fuller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        // ===== top controls =====
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        controlPanel.add(new JLabel("Animation Time (seconds):"));

        timePicker = new JComboBox<Integer>();
        timePicker.addItem(15);
        timePicker.addItem(30);
        timePicker.addItem(60);
        timePicker.addItem(90);
        timePicker.setSelectedItem(30);
        controlPanel.add(timePicker);

        includeStopsBox = new JCheckBox("Include Stops");
        includeStopsBox.setSelected(false); // default = do NOT include stops
        controlPanel.add(includeStopsBox);

        playButton = new JButton("Play");
        controlPanel.add(playButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        // ===== map viewer =====
        mapViewer = new JMapViewer();
        frame.add(mapViewer, BorderLayout.CENTER);

        // load raccoon image (put raccoon.png next to your .java files)
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon("raccoon.png");
        raccoonImage = icon.getImage();

        // listeners
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });

        includeStopsBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerMap();
            }
        });

        centerMap();
        frame.setVisible(true);
    }

    // choose which list of points to use depending on Include Stops box
    private static ArrayList<TripPoint> getCurrentTrip() {
        if (includeStopsBox.isSelected()) {
            return TripPoint.getTrip();        // all points
        } else {
            return TripPoint.getMovingTrip();  // only moving points
        }
    }

    // center the map over the average of the current points
    private static void centerMap() {
        ArrayList<TripPoint> trip = getCurrentTrip();
        if (trip == null || trip.size() == 0) {
            return;
        }

        double sumLat = 0.0;
        double sumLon = 0.0;

        for (int i = 0; i < trip.size(); i++) {
            TripPoint p = trip.get(i);
            sumLat += p.getLat();
            sumLon += p.getLon();
        }

        double centerLat = sumLat / trip.size();
        double centerLon = sumLon / trip.size();

        Coordinate center = new Coordinate(centerLat, centerLon);

        // zoom 6–7 looks good for this data
        mapViewer.setDisplayPosition(center, 6);
    }

    // start / reset the animation
    private static void startAnimation() {
        ArrayList<TripPoint> trip = getCurrentTrip();
        if (trip == null || trip.size() == 0) {
            return;
        }

        // stop previous animation if running
        if (animationTimer != null) {
            animationTimer.stop();
        }

        // clear previous markers / path
        mapViewer.removeAllMapMarkers();
        pathCoords.clear();
        raccoonMarker = null;


        centerMap();

        int totalSeconds = (Integer) timePicker.getSelectedItem();
        int numPoints = trip.size();

        // how long between frames (ms)
        int delay = (int) ((totalSeconds * 1000.0) / numPoints);
        if (delay < 20) { // don’t go crazy fast
            delay = 20;
        }

        currentIndex = 0;

        animationTimer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex >= trip.size()) {
                    animationTimer.stop();
                    return;
                }

                TripPoint p = trip.get(currentIndex);
                Coordinate coord = new Coordinate(p.getLat(), p.getLon());

                // add point to path
                pathCoords.add(coord);

                // move raccoon marker
                if (raccoonMarker != null) {
                    mapViewer.removeMapMarker(raccoonMarker);
                }
                raccoonMarker = new IconMarker(coord, raccoonImage);
                mapViewer.addMapMarker(raccoonMarker);

                // draw path polyline (open path, no closing edge)
                mapViewer.removeAllMapPolygons();
                if (pathCoords.size() >= 2) {
                    MapPolyLine pathLine = new MapPolyLine(pathCoords);
                    mapViewer.addMapPolygon(pathLine);
                }

                currentIndex++;
            }
        });

        animationTimer.start();
    }
}