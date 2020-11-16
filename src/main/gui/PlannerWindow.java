package gui;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.*;

import model.Event;
import model.Day;
import ui.PlanningAssistant;

public class PlannerWindow extends JFrame implements ActionListener {

    private int width;
    private int height;
    protected JButton addEventBtn;
    protected JButton numberEventsBtn;
    protected JButton viewEventsBtn;
    protected JButton saveEventsBtn;
    protected JButton loadEventsBtn;
    protected JButton doneBtn;
    protected JFrame mainFrame;
    protected JFrame eventEntry;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField timeField;
    private JTextField reminderField;
    private Day planner;

    public PlannerWindow() {
        planner = new Day("today");
        getScreenDimensions();
        createMainMenu();
    }

    public void createMainMenu() {
        mainFrame = new JFrame("Planner Main Menu");
        addEventBtn = new JButton("Add Event");
        addEventBtn.addActionListener(this);
        numberEventsBtn = new JButton("Number of Events");
        numberEventsBtn.addActionListener(this);
        viewEventsBtn = new JButton("View Events");
        viewEventsBtn.addActionListener(this);
        saveEventsBtn = new JButton("Save Events");
        saveEventsBtn.addActionListener(this);
        loadEventsBtn = new JButton("Load Events");
        loadEventsBtn.addActionListener(this);
        mainFrame.add(addEventBtn);
        mainFrame.add(numberEventsBtn);
        mainFrame.add(viewEventsBtn);
        mainFrame.add(saveEventsBtn);
        mainFrame.add(loadEventsBtn);
        mainFrame.setLayout(new GridLayout(5, 1));
        mainFrame.setSize(width / 3, height / 2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Day Planner Assistant");
        mainFrame.setVisible(true);
    }

    public void getScreenDimensions() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        width = dimension.width;
        height = dimension.height;
    }

    public void createEventDetailsWindow() {
        eventEntry = new JFrame();
        eventEntry.setSize(width / 4, height / 2);
        eventEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eventEntry.setResizable(false);
        eventEntry.setLocationRelativeTo(null);
        eventEntry.setTitle("New Event");
        eventEntry.setLayout(new FlowLayout());
        eventEntry.setBackground(Color.darkGray);

        // create name field
        JLabel name = new JLabel("Name:");
        eventEntry.add(name);
        nameField = new JTextField(25);
        eventEntry.add(nameField);

        // create location field
        JLabel location = new JLabel("Location:");
        eventEntry.add(location);
        locationField = new JTextField(23);
        eventEntry.add(locationField);

        // create time field
        JLabel time = new JLabel("Time (enter as HHMM using 24 clock):");
        eventEntry.add(time);
        timeField = new JTextField(8);
        eventEntry.add(timeField);

        // create reminder field
        JLabel reminder = new JLabel("Reminder:");
        eventEntry.add(reminder);
        reminderField = new JTextField(23);
        eventEntry.add(reminderField);

        // create done button
        doneBtn = new JButton("done");
        doneBtn.addActionListener(this);
        eventEntry.add(doneBtn);

    }

    private void createEventEntry(JFrame eventEntry) {
        String name = nameField.getText();
        String location = locationField.getText();
        int time = Integer.parseInt(timeField.getText());
        String reminder = reminderField.getText();
        Event newEvent = new Event(name, location, time, reminder);

        if (planner.checkDuplicate(newEvent)) {
            eventEntry.setVisible(true);
            JLabel eventExistsWarning = new JLabel("Warning: An event already exists at this time!");
            eventExistsWarning.setForeground(Color.red);
            eventEntry.add(eventExistsWarning);

        } else {
            planner.addEvent(newEvent);
//            System.out.println("Event Successfully added to planner!");
        }
    }

    private void viewEvents() {
//        if (planner.numberOfEvents() == 0) {
//            System.out.println("You do not have any events in your planner.");
//        }
        JFrame eventsList = new JFrame("Events List");
        String [] events = {planner.getListOfEventsDetails()};
        eventsList.add(new JList(events));
        eventsList.pack();
        eventsList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eventsList.setLocationRelativeTo(null);
        eventsList.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventBtn) {
            createEventDetailsWindow();
            eventEntry.setVisible(true);
        }
        if (e.getSource() == numberEventsBtn) {
            // get number of events
        }
        if (e.getSource() == viewEventsBtn) {
            viewEvents();
        }
        if (e.getSource() == saveEventsBtn) {
            // JSon writer
        }
        if (e.getSource() == loadEventsBtn) {
            // JSon reader
        }
        if (e.getSource() == doneBtn) {
            eventEntry.dispose();
            createEventEntry(eventEntry);
        }
    }
}

//    private void createNameField() {
//        JLabel n = new JLabel("Name:");
//        eventEntry.add(n);
//        JTextField nameField = new JTextField(25);
//        eventEntry.add(nameField);
//        String name = nameField.getText();
//    }
//
//    private void createLocationField() {
//        JLabel l = new JLabel("Location:");
//        eventEntry.add(l);
//        JTextField locationField = new JTextField(23);
//        eventEntry.add(locationField);
//        String location = locationField.getText();
//    }
//
//    private void createTimeField() {
//        JLabel t = new JLabel("Time (enter as HHMM using 24 clock):");
//        eventEntry.add(t);
//        JTextField timeField = new JTextField(8);
//        eventEntry.add(timeField);
//        String timeString = timeField.getText();
//        int time = Integer.parseInt(timeString);
//    }
//
//    private void createReminderField() {
//        JLabel r = new JLabel("Reminder:");
//        eventEntry.add(r);
//        JTextField reminderField = new JTextField(23);
//        eventEntry.add(reminderField);
//        String reminder = reminderField.getText();
//    }
