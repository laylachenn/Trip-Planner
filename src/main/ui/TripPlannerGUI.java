package ui;

import model.Trip;
import model.TripList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TripPlannerGUI extends JFrame {
    private static TripPlannerConsole tripPlannerConsole;
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
        this.tripPlannerConsole = tripPlannerConsole;
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
        String[] buttonLabels = {"Add Trip", "View Trips", "Add Review", "Save", "Load"};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
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
            case "View Trips":
                viewTripsAction();
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
        JFrame addTripFrame = createAddTripFrame();

        JTextField flightField = new JTextField();
        JTextField tripLengthField = new JTextField();
        JTextField hotelsField = new JTextField();
        JTextField destinationsField = new JTextField();

        addTripFrame.add(new JLabel("Flight Information:"));
        addTripFrame.add(flightField);
        addTripFrame.add(new JLabel("Trip Length (days):"));
        addTripFrame.add(tripLengthField);
        addTripFrame.add(new JLabel("Hotel(s):"));
        addTripFrame.add(hotelsField);
        addTripFrame.add(new JLabel("Destinations:"));
        addTripFrame.add(destinationsField);

        JButton confirmButton = new JButton("Add Trip");
        confirmButton.addActionListener(e -> addTrip(Integer.parseInt(tripLengthField.getText()),
                        flightField.getText(), hotelsField.getText(), destinationsField.getText()));
        addTripFrame.add(confirmButton);
        addTripFrame.setVisible(true);
    }

    private void addTrip(int tripLength, String flights, String hotels, String destinations) {
        Trip newTrip = new Trip(flights, tripLength, hotels, destinations);
        tripList.addTrip(newTrip);
        tripsTextArea.setText("Trip has been added successfully!");
    }

    private JFrame createAddTripFrame() {
        JFrame addTripFrame = new JFrame("Add Trip");
        addTripFrame.setSize(400, 300);
        addTripFrame.setLayout(new GridLayout(5, 2));
        addTripFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return addTripFrame;
    }

    private void viewTripsAction() {
        //stub
    }

    private void addReviewAction() {
        //stub
    }

    private void saveAction() {
        //stub
    }

    private void loadAction() {
        //stub
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TripPlannerConsole tripPlannerConsole = new TripPlannerConsole();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
