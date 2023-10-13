package ui;

import model.Trip;
import model.TripList;
import java.util.List;
import java.util.Scanner;

//source scanner: https://www.w3schools.com/java/java_user_input.asp
//source switch: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html

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
        System.out.println("5. Exit the trip planner");
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
                Trip trip = trips.get(i);
                String review = trip.getReview();
                System.out.println((i + 1) + ". " + trips.get(i).toString());
                if (!review.isEmpty()) {
                    System.out.println("Review: " + review);
                }
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

    private void displayPreset(Trip tripPreset) {
        System.out.println("Trip preset information:");
        System.out.println(tripPreset.toString());
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void showPresets() {
        System.out.println("Preset Trips:");
        System.out.println("1. Tropical Hawaii Trip");
        System.out.println("2. Winter New York City Trip");
        System.out.println("3. Adventurous Japan Trip");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Trip tripPreset = null;

        ////CHANGE THE STRINGS
        switch (choice) {
            case 1:
                tripPreset = makePreset("Tropical Hawaii Trip", List.of("Hawaii Flight"), 7,
                        List.of("Hawaii Hotel"), List.of("Hawaii Destination (Maui)"));
                break;
            case 2:
                tripPreset = makePreset("Winter New York City Trip", List.of("City Flight"), 5,
                        List.of("City Hotel"), List.of("City Destination"));
                break;
            case 3:
                tripPreset = makePreset("Adventurous Japan Trip", List.of("Japan Flight"), 10,
                        List.of("Japan Hotel"), List.of("Japan Destination (Tokyo)"));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        displayPreset(tripPreset);
        System.out.println("Do you want to add this preset to your list of trips? (1 for yes, 2 for no): ");
        int createChoice = scanner.nextInt();
        scanner.nextLine();

        if (createChoice == 1) {
            tripList.addTrip((tripPreset));
            System.out.println("The trip preset has been added to your list of trips.");
        } else {
            System.out.println("The trip preset has not been added your list. Returning to menu.");
        }
    }

    private Trip makePreset(String name, List<String> flights, int tripLength, List<String> hotels,
                            List<String> destinations) {
        return new Trip(flights, tripLength, hotels, destinations);
    }

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
                    System.out.println("Closing trip planner. Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
