package ui;

import model.Trip;
import model.TripList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class TripPlannerGUI extends JFrame {
    private JTextArea tripsTextArea;
    private TripList tripList;

    public TripPlannerGUI(TripPlannerConsole tripPlannerConsole) throws FileNotFoundException {
        initializeVariables(tripPlannerConsole);
        setupFrame();
        setupButtonPanel();
        setupTripsTextArea();
        addActionListeners();
        setVisible(true);
    }

    private void initializeVariables(TripPlannerConsole tripPlannerConsole) {
        this.tripList = tripPlannerConsole.getTripList();
    }

    private void setupFrame() {
        setTitle("Trip Planner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

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

    private void setupTripsTextArea() {
        tripsTextArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(tripsTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof  JButton) {
                JButton button = (JButton) component;
                button.addActionListener(e -> handleButtonClick(button.getText()));
            }
        }
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "Add Trip":
                addTripAction();
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

    private void addTripAction() {
        showAddTripAction();
    }

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

    private void addTrip(int tripLength, String flights, String hotels, String destinations) {
        Trip newTrip = new Trip(flights, tripLength, hotels, destinations);
        tripList.addTrip(newTrip);
        viewTripsAction();
    }

    private void viewTripsAction() {
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

    public void updateTripList() {
        viewTripsAction();
    }

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
