package gui;

import model.Day;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlannerWindow extends JFrame implements ActionListener {

    public static final Color LIGHT_BLUE = new Color(100, 140, 250);
    private int width;
    private int height;
    protected JButton addEventBtn;
    protected JButton numberEventsBtn;
    protected JButton viewEventsBtn;
    protected JButton saveEventsBtn;
    protected JButton loadEventsBtn;
    protected JButton doneBtn;
    protected JButton completeBtn;
    protected JFrame mainFrame;
    protected JFrame eventEntry;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField timeField;
    private JTextField reminderField;
    private Day planner;
    private JFrame warning;
    private static final String JSON_STORE = "./data/day.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // set up day planner assistant
    public PlannerWindow() throws FileNotFoundException {
        planner = new Day("today");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        getScreenDimensions();
        createMainMenu();
    }

    // EFFECTS: creates main menu
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
        mainFrame.getContentPane().setBackground(LIGHT_BLUE);
        mainFrame.add(addEventBtn);
        mainFrame.add(numberEventsBtn);
        mainFrame.add(viewEventsBtn);
        mainFrame.add(saveEventsBtn);
        mainFrame.add(loadEventsBtn);
        mainFrame.setLayout(new GridLayout(10, 1));
        mainFrame.setSize(width / 3, height / 2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Day Planner Assistant");
        mainFrame.setVisible(true);
    }

    // EFFECTS: gets screen dimensions
    public void getScreenDimensions() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        width = dimension.width;
        height = dimension.height;
    }

    // EFFECTS: creates event details window
    public void createEventDetailsWindow() {
        eventEntry = new JFrame();
        eventEntry.setSize(400,500);
        eventEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventEntry.setResizable(false);
        eventEntry.setLocationRelativeTo(null);
        eventEntry.setTitle("New Event");
        eventEntry.setLayout(new FlowLayout());
        eventEntry.getContentPane().setBackground(LIGHT_BLUE);

        createFields();

        // create done button
        doneBtn = new JButton("done");
        doneBtn.addActionListener(this);
        eventEntry.add(doneBtn);
    }

    // EFFECTS: creates event details fields
    private void createFields() {
        // create name field
        JLabel name = new JLabel("Name:");
        eventEntry.add(name);
        nameField = new JTextField(25);
        eventEntry.add(nameField);

        // create location field
        JLabel location = new JLabel("Location:");
        eventEntry.add(location);
        locationField = new JTextField(24);
        eventEntry.add(locationField);

        // create time field
        JLabel time = new JLabel("Time (enter as 24 hours (H)HMM):");
        eventEntry.add(time);
        timeField = new JTextField(11);
        eventEntry.add(timeField);

        // create reminder field
        JLabel reminder = new JLabel("Reminder:");
        eventEntry.add(reminder);
        reminderField = new JTextField(23);
        eventEntry.add(reminderField);

    }

    // EFFECTS: saves event entry locally
    private void createEventEntry() {
        String name = nameField.getText();
        String location = locationField.getText();
        int time = Integer.parseInt(timeField.getText());
        String reminder = reminderField.getText();
        Event newEvent = new Event(name, location, time, reminder);

        if (planner.checkDuplicate(newEvent)) {
            warningWindow();
        } else {
            playSound("success_sound.wav");
            eventEntry.dispose();
        }
        planner.addEvent(newEvent);
    }

    // EFFECTS: displays the list of events in the planner
    private void viewEvents() {
        JFrame eventsList = new JFrame("Events List");
        eventsList.setSize(width / 4, height / 2);
        eventsList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventsList.setLocationRelativeTo(null);
        eventsList.getContentPane().setBackground(LIGHT_BLUE);
        eventsList.setLayout(new GridLayout(10, 1));

        if (planner.numberOfEvents() == 0) {
            JLabel noEvents = new JLabel("You do not have any events in your planner.");
            noEvents.setBackground(Color.white);
            noEvents.setOpaque(true);
            eventsList.add(noEvents);
            eventsList.pack();
            eventsList.setVisible(true);
        }

        for (Event e: planner.getEvents()) {
            JLabel label = new JLabel(e.getEventDetails());
            eventsList.add(label);
        }

        eventsList.pack();
        eventsList.setVisible(true);
    }

    // EFFECTS: gets the number of events
    private void numberOfEvents() {
        JFrame numberEvents = new JFrame("Number Of Events");
        JLabel number = new JLabel("You currently have " + planner.numberOfEvents() + " event(s) in your planner!");
        number.setBackground(Color.white);
        number.setOpaque(true);
        numberEvents.setLayout(new GridLayout(10, 1));
        numberEvents.setSize(width / 4, height / 2);
        numberEvents.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        numberEvents.getContentPane().setBackground(LIGHT_BLUE);
        numberEvents.add(number);
        numberEvents.setLocationRelativeTo(null);
        numberEvents.setVisible(true);
    }

    // EFFECTS: create the warning window
    private void warningWindow() {
        warning = new JFrame("Warning");
        JLabel eventExistsWarning = new JLabel("Event already exists at this time!");
        eventExistsWarning.setForeground(Color.red);
        completeBtn = new JButton("complete");
        completeBtn.addActionListener(this);
        warning.add(eventExistsWarning);
        warning.add(completeBtn);
        warning.getContentPane().setBackground(Color.LIGHT_GRAY);
        warning.setLayout(new GridLayout(15, 1));
        warning.setSize(width / 4, height / 2);
        warning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        warning.setLocationRelativeTo(null);
        warning.setVisible(true);
        playSound("warning_sound.wav");
    }

    // MODIFIES: this
    // EFFECTS: saves the workroom to file
    private void savePlanner() {
        try {
            jsonWriter.open();
            jsonWriter.write(planner);
            jsonWriter.close();
            System.out.println("Saved " + planner.getDate() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPlanner() {
        try {
            planner = jsonReader.read();
            System.out.println("Loaded " + planner.getDate() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: creates and plays notification sound
    public void playSound(String soundName) {
        try {
            AudioInputStream warningSound = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip warningClip = AudioSystem.getClip();
            warningClip.open(warningSound);
            warningClip.start();

            AudioInputStream successSound = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip successClip = AudioSystem.getClip();
            successClip.open(successSound);
            successClip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: performs actions from action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventBtn) {
            createEventDetailsWindow();
            eventEntry.setVisible(true);
        }
        if (e.getSource() == numberEventsBtn) {
            numberOfEvents();
        }
        if (e.getSource() == viewEventsBtn) {
            viewEvents();
        }
        if (e.getSource() == saveEventsBtn) {
            savePlanner();
        }
        if (e.getSource() == loadEventsBtn) {
            loadPlanner();
        }
        if (e.getSource() == doneBtn) {
            createEventEntry();
        }
        if (e.getSource() == completeBtn) {
            warning.dispose();
            eventEntry.dispose();
        }
    }
}
