package gui;

import model.Day;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlannerWindow extends JFrame implements ActionListener {

    public static final Color LIGHT_BLUE = new Color(51,153,255);
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

    public PlannerWindow() throws FileNotFoundException {
        planner = new Day("today");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        eventEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    private void createEventEntry() {
        String name = nameField.getText();
        String location = locationField.getText();
        int time = Integer.parseInt(timeField.getText());
        String reminder = reminderField.getText();
        Event newEvent = new Event(name, location, time, reminder);

        if (planner.checkDuplicate(newEvent)) {
            warningWindow();
        }

        planner.addEvent(newEvent);
        playSound("success_sound.wav");
        eventEntry.dispose();
    }

    //EFFECTS: prints the list of events in the planner
    private void viewEvents() {
        JFrame eventsList = new JFrame("Events List");
        eventsList.setSize(width / 4, height / 2);
        eventsList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventsList.setLocationRelativeTo(null);
        eventsList.setBackground(LIGHT_BLUE);

        if (planner.numberOfEvents() == 0) {
            JLabel noEvents = new JLabel("You do not have any events in your planner.");
            noEvents.setForeground(Color.red);
            eventsList.add(noEvents);
            eventsList.setVisible(true);
        }
        String[] events = {planner.getListOfEventsDetails()};
        eventsList.add(new JList(events));
        eventsList.setVisible(true);
    }

    private void numberOfEvents() {
        JFrame numberEvents = new JFrame("Number Of Events");
        numberEvents.setSize(width / 4, height / 2);
        numberEvents.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        numberEvents.setLocationRelativeTo(null);
        JLabel number = new JLabel(String.valueOf(planner.numberOfEvents()));
        numberEvents.add(number);
        numberEvents.setVisible(true);
    }

    private void warningWindow() {
        warning = new JFrame("Warning");
        JLabel eventExistsWarning = new JLabel("Event already exists at this time!");
        eventExistsWarning.setForeground(Color.red);
        completeBtn = new JButton("complete");
        completeBtn.setBounds(50,100,10,10);
        completeBtn.addActionListener(this);
        warning.add(completeBtn);
        warning.add(eventExistsWarning);
        warning.setLayout(new GridLayout(2, 1));
        warning.setSize(width / 4, height / 4);
        warning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        warning.setVisible(true);
        playSound("warning_sound.wav");
    }

    // EFFECTS: saves the workroom to file
    private void saveDay() {
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
    private void loadDay() {
        try {
            planner = jsonReader.read();
            System.out.println("Loaded " + planner.getDate() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

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
            saveDay();
        }
        if (e.getSource() == loadEventsBtn) {
            loadDay();
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
