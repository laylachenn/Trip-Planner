package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            TripPlannerApp tripPlannerApp = new TripPlannerApp();
            tripPlannerApp.start();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
