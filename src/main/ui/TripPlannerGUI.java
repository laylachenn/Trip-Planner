package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TripPlannerGUI extends JFrame {
    private TripPlannerConsole tripPlannerConsole;
    private JTextArea tripsTextArea;

    public TripPlannerGUI() throws FileNotFoundException {
        tripPlannerConsole = new TripPlannerConsole();

        setTitle("Trip Planner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addTripButton = new JButton("Add Trip");
        JButton viewTripsButton = new JButton("View Trips");
        JButton addReviewButton = new JButton("Add Review");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        tripsTextArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(tripsTextArea);

        addTripButton.addActionListener(e -> addTripAction());
        viewTripsButton.addActionListener(e -> viewTripsAction());
        addReviewButton.addActionListener(e -> addReviewAction());
        saveButton.addActionListener(e -> saveAction());
        addTripButton.addActionListener(e -> loadAction());

        buttonPanel.add(addTripButton);
        buttonPanel.add(viewTripsButton);
        buttonPanel.add(addReviewButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
