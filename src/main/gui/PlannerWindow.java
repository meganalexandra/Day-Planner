package gui;

import sun.font.TrueTypeFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// Create planner window
public class PlannerWindow extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private JFrame eventEntry;
    private JFrame eventView;
    private JButton addEvents;
    private JButton loadEvents;
    private JButton done;
    int width;
    int height;
    private JPanel container;

    public PlannerWindow() {
        createWindow();
    }

    private void createWindow() {
        getScreenDimensions();
        createMenuWindow();
        mainFrame.setVisible(true);
    }

    private void createMenuWindow() {
        createMenuFrame();
        createMenuPanels();

    }

    // MODIFIES: this
    // EFFECTS: creates menu JFrame
    private void createMenuFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(width / 4, height / 2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Planner");
        mainFrame.setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates two JPanels side by side
    private void createMenuPanels() {
        container = new JPanel();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();

        panelOne.setBackground(Color.lightGray);
        panelOne.add(new JLabel("View Planner"));
        loadEvents = new JButton("Load Events");
        loadEvents.addActionListener(this);
        panelOne.add(loadEvents);

        panelTwo.setBackground(Color.lightGray);
        panelTwo.add(new JLabel("Edit Planner"));
        addEvents = new JButton("Add Events");
        addEvents.addActionListener(this);
        panelTwo.add(addEvents);

        container.add(panelOne);
        container.add(panelTwo);
        mainFrame.add(container);
    }

    private void getScreenDimensions() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        width = dimension.width;
        height = dimension.height;

    }

    // MODIFIES: this
    // EFFECTS: creates eventEntry window
    private void createEventEntryWindow() {
        createEventFrame();
        createEventFields();
    }

    // MODIFIES: this
    // EFFECTS: creates JFrame for eventEntry
    private void createEventFrame() {
        eventEntry = new JFrame();
        eventEntry.setSize(width / 4, height / 2);
        eventEntry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eventEntry.setResizable(false);
        eventEntry.setLocationRelativeTo(null);
        eventEntry.setTitle("New Event");
        eventEntry.setLayout(new FlowLayout());
        eventEntry.setBackground(Color.darkGray);
    }

    // MODIFIES: this
    // EFFECTS: creates JFrame for eventFields
    private void createEventFields() {
        JLabel name = new JLabel("Name:");
        eventEntry.add(name);
        JTextField nameField = new JTextField(25);
        eventEntry.add(nameField);
        JLabel location = new JLabel("Location:");
        eventEntry.add(location);
        JTextField locationField = new JTextField(23);
        eventEntry.add(locationField);
        JLabel time = new JLabel("Time (enter as HHMM using 24 clock):");
        eventEntry.add(time);
        JTextField timeField = new JTextField(8);
        eventEntry.add(timeField);
        JLabel reminder = new JLabel("Reminder:");
        eventEntry.add(reminder);
        JTextField reminderField = new JTextField(23);
        eventEntry.add(reminderField);
        done = new JButton("done");
        done.addActionListener(this);
        eventEntry.add(done);
    }

    private void createViewEventsWindow() {
        eventView = new JFrame();
        eventView.setSize(width / 4, height / 2);
        eventView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eventView.setResizable(false);
        eventView.setLocationRelativeTo(null);
        eventView.setTitle("Planner View");
        eventView.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEvents) {
            createEventEntryWindow();
            eventEntry.setVisible(true);
        }
        if (e.getSource() == done) {
            eventEntry.dispose();
        }
        if (e.getSource() == loadEvents) {
            createViewEventsWindow();
            eventView.setVisible(true);
        }
    }
}
