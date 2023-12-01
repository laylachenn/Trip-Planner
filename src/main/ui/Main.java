package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TripPlannerConsole tripPlannerApp = new TripPlannerConsole();
                TripPlannerGUI tripPlannerGUI = new TripPlannerGUI(tripPlannerApp);
                tripPlannerApp.setTripPlannerGUI(tripPlannerGUI);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to run application: file not found");
            }
        });
    }
}
