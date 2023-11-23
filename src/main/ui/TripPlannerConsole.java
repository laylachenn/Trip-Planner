package ui;

import model.Trip;
import model.TripList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

//source scanner: https://www.w3schools.com/java/java_user_input.asp
//source switch: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html

//Represents a trip planner application where the user can interact and input information that is stored
public class TripPlannerConsole {
    private static final String JSON_STORE = "./data/triplist.json";
    private TripList tripList;
    private final Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs a trip list and a scanner that will read input from the user and allow for interaction
    public TripPlannerConsole() throws FileNotFoundException {
        tripList = new TripList();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: prints a trip planner menu with different options for the user, and the last line will allow the user to
    //type their choice
    private void showMenu() {
        System.out.println("Trip Planner Menu: ");
        System.out.println("1: Add a trip");
        System.out.println("2. View trips");
        System.out.println("3. Create a post-travel review");
        System.out.println("4. View trip presets");
        System.out.println("5. Save trips to file");
        System.out.println("6. Load trips from file");
        System.out.println("7. Exit the trip planner");
        System.out.print("Enter your choice: ");
    }

    //MODIFIES: this
    //EFFECTS: asks the user for information on the trip they would like to plan such as trip length, flights, hotels
    //and destinations. then constructs a trip with the given information and adds it to their trip list. prints a
    //message of success at the end.
    private void addTrip() {
        System.out.println("Enter the number of days the trip will be: ");
        int tripLength = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter flight information separated by commas: ");
        String flightInfo = scanner.nextLine();
        String flights = flightInfo;

        System.out.println("Enter the names of your hotel(s) separated by commas: ");
        String hotelInfo = scanner.nextLine();
        String hotels = hotelInfo;

        System.out.println("Enter the names of your destination(s) separated by commas: ");
        String destinationInfo = scanner.nextLine();
        String destinations = destinationInfo;

        Trip newTrip = new Trip(flights, tripLength, hotels, destinations);
        tripList.addTrip(newTrip);
        System.out.println("Trip has been added successfully!");
    }

    //EFFECTS: prints a message to say that there are no trips if the trip list is empty. if the trip list is not empty,
    //then it prints the trip list and all the trip's information including the review if there is one
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

    //EFFECTS: displays the trips so the user can select which one they want to review. print a message to the user to
    //say there are no trips if the trip list is empty. after the user selects a trip then the user will be able to
    //enter their review and if successful, it will print a success message. if not, it will print a failed message.
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
            boolean reviewCreated = tripList.createReview(selectedTrip, review);
            if (reviewCreated) {
                System.out.println("Thank you! The review has been created for the selected trip.");
            } else {
                System.out.println("Failed to create the review. Please try again.");
            }
        }
    }

    //EFFECTS: uses the toString method to display the trip preset information to the user
    private void displayPreset(Trip tripPreset) {
        System.out.println("Trip preset information:");
        System.out.println(tripPreset.toString());
    }

    //MODIFIES: this
    //EFFECTS: shows the user a preset menu where the user can select one of the preset options to view more
    //information on it. then the user will be able to add the preset to their list of trips if they want. if they
    //don't want to, then it will say that it's going back to the menu. it prints success and failure messages depending
    //on if the trip preset has been added to the list of trips
    @SuppressWarnings({"checkstyle:methodlength", "checkstyle:SuppressWarnings"})
    private void showPresets() {
        System.out.println("Preset Trips:");
        System.out.println("1. Tropical Hawaii Trip");
        System.out.println("2. Winter New York City Trip");
        System.out.println("3. Adventurous Japan Trip");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Trip tripPreset = null;

        switch (choice) {
            case 1:
                tripPreset = makePreset("Tropical Hawaii Trip",
                        "YVR-ITO 3/21/24 @9am, ITO-YVR 3/28/24 @1pm", 7,
                        "Hilo Hawaiian Hotel, Paradise Bay Resort", "Hilo, Kailua");
                break;
            case 2:
                tripPreset = makePreset("Winter New York City Trip",
                        "YVR-JFK 12/20/23 @1am, JFK-YVR 12/30/23 @4pm", 10,
                        "Holiday Inn NYC, The Manhattan", "Manhattan, Brooklyn");
                break;
            case 3:
                tripPreset = makePreset("Adventurous Japan Trip",
                        "YVR-ITM 11/12/23 @3pm, KIX-YVR 11/26/23 @9pm", 14,
                        "Plaza Osaka, Tokyo Grand Hotel, Park Hotel Kyoto", "Osaka, Tokyo, Kyoto");
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

    //REQUIRES: the parameters to be non-null
    //EFFECTS: constructs a new trip object with all the details
    private Trip makePreset(String name, String flights, int tripLength, String hotels,
                            String destinations) {
        return new Trip(flights, tripLength, hotels, destinations);
    }

    //EFFECTS: creates a while loop so that the menu is constantly displayed after each of the user's choices, unless
    //the user chooses to exit the trip planner by choosing case 5 which will exit the program. gets the user's input
    //using scanner and based on their choice it will call different methods such as addTrip, viewTrips, etc. however,
    //if the user chooses an invalid option, then it will display an error message
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
                    saveTripList();
                    break;
                case 6:
                    loadTripList();
                    break;
                case 7:
                    System.out.println("Closing trip planner. Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //EFFECTS: saves the trip list to file
    private void saveTripList() {
        try {
            jsonWriter.open();
            jsonWriter.write(tripList);
            jsonWriter.close();
            System.out.println("Saved trip list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads trip list from file
    private void loadTripList() {
        try {
            tripList = jsonReader.read();
            System.out.println("Loaded trip list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
