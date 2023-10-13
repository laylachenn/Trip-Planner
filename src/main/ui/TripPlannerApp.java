package ui;

import model.Trip;
import model.TripList;
import java.util.List;
import java.util.Scanner;

public class TripPlannerApp {
    private final TripList tripList;
    private final Scanner scanner;

    public TripPlannerApp() {
        tripList = new TripList();
        scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("Trip Planner Menu: ");
        System.out.println("1: Add a trip");
        System.out.println("2. View trips");
        System.out.println("3. Create a post-travel review");
        System.out.println("4. View trip presets");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addTrip() {
        System.out.println("Enter the number of days the trip will be: ");
        int tripLength = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter flight information separated by commas: ");
        String flightInfo = scanner.nextLine();
        List<String> flights = List.of(flightInfo.split(","));

        System.out.println("Enter the names of your hotel(s) separated by commas: ");
        String hotelInfo = scanner.nextLine();
        List<String> hotels = List.of(hotelInfo.split(","));

        System.out.println("Enter the names of your destination(s) separated by commas: ");
        String destinationInfo = scanner.nextLine();
        List<String> destinations = List.of(destinationInfo.split(","));

        Trip newTrip = new Trip(flights, tripLength, hotels, destinations);
        tripList.addTrip(newTrip);
        System.out.println("Trip has been added successfully!");
    }

    private void viewTrips() {
        List<Trip> trips = tripList.getTrips();

        if (trips.isEmpty()) {
            System.out.println("There are no trips to view.");
        } else {
            System.out.println("List of trips:");
            for (int i = 0; i < trips.size(); i++) {
                System.out.println((i + 1) + ". " + trips.get(i).toString());
            }
        }
    }

    private void createReview() {
        List<Trip> trips = tripList.getTrips();

        if (trips.isEmpty()) {
            System.out.println("There are no trips to review.");
            return;
        }
        System.out.println("Select a trip to review:");
        for (int i = 0; i < trips.size(); i++) {
            System.out.println((i + 1) + ". " + trips.get(i).toString());
        }

        int tripIndex = scanner.nextInt();
        scanner.nextLine();
        if (tripIndex >= 1 && tripIndex <= trips.size()) {
            System.out.println("How was your trip! Enter your review: ");
            String review = scanner.nextLine();
            Trip selectedTrip = trips.get(tripIndex - 1);
            tripList.createReview(selectedTrip, review);
            System.out.println("Thank you! The review has been created for the selected trip.");
        } else {
            System.out.println("Invalid trip selection. Please try again.");
        }
    }

    private void showPresets() {
        System.out.println("Preset Trips:");
        System.out.println("1. Tropical Hawaii Trip");
        System.out.println("2. Winter New York City Trip");
        System.out.println("3. Amusing Japan Trip");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ////ADD INFO
        switch (choice) {
            case 1:
                makePreset("Tropical Hawaii Trip", List.of("Hawaii Flight"), 7,
                        List.of("Hawaii Hotel"), List.of("Hawaii Destination (Maui)"));
                break;
            case 2:
                makePreset("Winter New York City Trip", List.of("City Flight"), 5,
                        List.of("City Hotel"), List.of("City Destination"));
                break;
            case 3:
                makePreset("Amusing Japan Trip", List.of("Japan Flight"), 10,
                        List.of("Japan Hotel"), List.of("Japan Destination (Tokyo)"));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void makePreset(String name, List<String> flights, int tripLength, List<String> hotels,
                            List<String> destinations) {
        Trip presetTrip = new Trip(flights, tripLength, hotels, destinations);
        System.out.println("Preset Trip: " + name);
        System.out.println(presetTrip.toString());
    }

    //source: -scanner: https://www.w3schools.com/java/java_user_input.asp
    // -switch: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void start() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTrip();
                    break;
                case 2:
                    viewTrips();
                    break;
                case 3:
                    createReview();
                    break;
                case 4:
                    showPresets();
                    break;
                case 5:
                    System.out.println("Closing program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
