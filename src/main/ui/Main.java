package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.io.FileNotFoundException;

//source: https://www.baeldung.com/jvm-shutdown-hooks
//source: https://www.geeksforgeeks.org/jvm-shutdown-hook-java/

public class Main {
    public static void main(String[] args) {
        EventLog eventLog = EventLog.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Event event : eventLog) {
                System.out.println(event.toString());
            }
        }));
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
