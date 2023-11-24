package ui;

import model.Trip;
import model.TripList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

//source: https://www.guru99.com/java-swing-gui.html
//source: https://tutorials.tinyappco.com/Java/SwingGUI

//Represents a trip planner application GUI where the user can interact and input information that is stored
public class TripPlannerGUI extends JFrame {
    private JTextArea tripsTextArea;
    private TripList tripList;

    private String selectTripForReview(List<Trip> trips) {
        String[] tripOptions = trips.stream().map(Trip::toString).toArray(String[]::new);
        return (String) JOptionPane.showInputDialog(
                this,
                "Select a trip to add a review:",
                "Add Review",
                JOptionPane.PLAIN_MESSAGE,
                null,
                tripOptions,
                tripOptions[0]);
    }

    //EFFECTS: constructs a trip planner gui where it sets up the buttons, variables, frame and action listeners
    public TripPlannerGUI(TripPlannerConsole tripPlannerConsole) throws FileNotFoundException {
        initializeVariables(tripPlannerConsole);
        setupFrame();
        setupButtonPanel();
        setupTripsTextArea();
        addActionListeners();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initializes the variables by getting trip list from the console class
    private void initializeVariables(TripPlannerConsole tripPlannerConsole) {
        this.tripList = tripPlannerConsole.getTripList();
    }

    //EFFECTS: sets up the frame (title, size, closing, layout)
    private void setupFrame() {
        setTitle("Trip Planner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    //MODIFIES: this
    //EFFECTS: sets up the buttons at the top of the GUI
    //and assigns actions listeners to them
    private void setupButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        String[] buttonLabels = {"Add Trip", "Add Review", "Save", "Load"};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> handleButtonClick(label));
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.NORTH);
    }

    //MODIFIES: this
    //EFFECTS: sets up the display area for the list of trips
    private void setupTripsTextArea() {
        tripsTextArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(tripsTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: creates actions listeners to the buttons in the GUI,
    //so they'll do as told
    private void addActionListeners() {
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof  JButton) {
                JButton button = (JButton) component;
                button.addActionListener(e -> handleButtonClick(button.getText()));
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: using switch to call different methods when each button is clicked
    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "Add Trip":
                showAddTripAction();
                break;
            case "Add Review":
                addReviewAction();
                break;
            case "Save":
                saveAction();
                break;
            case "Load":
                loadAction();
                break;
            default:
                break;
        }
    }

    //EFFECTS: creates an add trip panel once the add trip button is clicked
    // with different text boxes for user input, also displays a picture of a nice beach
    private void showAddTripAction() {
        JPanel addTripPanel = new JPanel(new GridLayout(5, 2));

        JTextField flightField = new JTextField();
        JTextField tripLengthField = new JTextField();
        JTextField hotelsField = new JTextField();
        JTextField destinationsField = new JTextField();

        addTripPanel.add(new JLabel("Flight Information:"));
        addTripPanel.add(flightField);
        addTripPanel.add(new JLabel("Trip Length (days):"));
        addTripPanel.add(tripLengthField);
        addTripPanel.add(new JLabel("Hotel(s):"));
        addTripPanel.add(hotelsField);
        addTripPanel.add(new JLabel("Destinations:"));
        addTripPanel.add(destinationsField);

        ImageIcon icon = new ImageIcon("/Users/laylachen/cpsc210/project_d1p4j/data/sun.jpeg");

        int result = JOptionPane.showConfirmDialog(null, addTripPanel,
                "Enter Trip Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (result == JOptionPane.OK_OPTION) {
            addTrip(Integer.parseInt(tripLengthField.getText()),
                    flightField.getText(), hotelsField.getText(), destinationsField.getText());
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a trip to the trip list and displays the updated trip list
    private void addTrip(int tripLength, String flights, String hotels, String destinations) {
        Trip newTrip = new Trip(flights, tripLength, hotels, destinations);
        tripList.addTrip(newTrip);
        viewTripsAction();
    }

    //MODIFIES: this
    //EFFECTS: updates the main frame with the list of trips being displayed
    public void viewTripsAction() {
        List<Trip> trips = tripList.getTrips();
        if (trips.isEmpty()) {
            tripsTextArea.setText("There are no trips to view.");
        } else {
            StringBuilder tripInfo = new StringBuilder("List of trips:\n");
            for (int i = 0; i < trips.size(); i++) {
                Trip trip = trips.get(i);
                tripInfo.append((i + 1)).append(". ").append(trip.toString()).append("\n");
                String review = trip.getReview();
                if (!review.isEmpty()) {
                    tripInfo.append("Review: ").append(review).append("\n");
                }
            }
            tripsTextArea.setText(tripInfo.toString());
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a review to the selected trip in the trip list based on the user input
    private void addReviewToSelectedTrip(String selectedTrip, String review, List<Trip> trips) {
        if (selectedTrip != null && review != null) {
            boolean finished = false;
            for (Trip trip : trips) {
                if (trip.toString().equals(selectedTrip)) {
                    boolean reviewAdded = tripList.createReview(trip, review);
                    String message = reviewAdded ? "Review added successfully."
                            : "Failed to add review. Try again.";
                    tripsTextArea.setText(message);
                    finished = true;
                    break;
                }
            }
            if (!finished) {
                tripsTextArea.setText("Invalid trip selection. Try again");
            } else {
                viewTripsAction();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: gives an option panel for the user to add a review,
    //and then adds the review to the selected trip
    private void addReviewAction() {
        List<Trip> trips = tripList.getTrips();
        if (trips.isEmpty()) {
            tripsTextArea.setText("There are no trips to review.");
            return;
        }
        String selectedTrip = selectTripForReview(trips);
        String review = JOptionPane.showInputDialog(this, "Enter your review: ");
        addReviewToSelectedTrip(selectedTrip, review, trips);
    }

    //EFFECTS: saves the trip list to file
    private void saveAction() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/triplist.json");
            jsonWriter.open();
            jsonWriter.write(tripList);
            jsonWriter.close();
            tripsTextArea.setText("Trip list saved successfully.");
        } catch (FileNotFoundException e) {
            tripsTextArea.setText("Error: Unable to save trip list.");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads trip list from file
    private void loadAction() {
        try {
            JsonReader jsonReader = new JsonReader("./data/triplist.json");
            tripList = jsonReader.read();
            tripsTextArea.setText("Trip list loaded successfully.");
            viewTripsAction();
        } catch (IOException e) {
            tripsTextArea.setText("Error: Unable to load trip list.");
        }
    }

    //EFFECTS: runs the GUI application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TripPlannerConsole tripPlannerConsole = new TripPlannerConsole();
                TripPlannerGUI tripPlannerGUI = new TripPlannerGUI(tripPlannerConsole);
                tripPlannerConsole.setTripPlannerGUI(tripPlannerGUI);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
