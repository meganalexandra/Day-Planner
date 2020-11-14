package ui;

import model.Day;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// sourced from https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// Represents the planning application
public class PlanningAssistant {
    private static final String JSON_STORE = "./data/day.json";
    private Day planner;
    private Scanner scan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs Planning Assistant
    public PlanningAssistant() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAssistant();
    }

    // MODIFIES: this
    // EFFECTS: runs the Planning Assistant Application
    private void runAssistant() {
        boolean keepGoing = true;
        String command = null;
        scan = new Scanner(System.in);

        createDay();

        while (keepGoing) {
            displayMenu();
            command = scan.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a good Day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            createEvent();
        } else if (command.equals("n")) {
            getNumberOfEvents();
        } else if (command.equals("v")) {
            viewEvents();
        } else if (command.equals("s")) {
            saveDay();
        } else if (command.equals("l")) {
            loadDay();
        } else {
            System.out.println("Invalid Selection - Please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the day planner
    private void createDay() {
        planner = new Day("October 31");
        scan = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the Day Planner Assistant! Please select from:");
        System.out.println("\ta -> add event");
        System.out.println("\tn -> get number of events");
        System.out.println("\tv -> view day planner");
        System.out.println("\ts -> save day to file");
        System.out.println("\tl -> load day from file");
        System.out.println("\te -> exit");
    }

    // MODIFIES: this
    // EFFECTS: creates an event with the user information
    private void createEvent() {
        System.out.println("ENTER EVENT DETAILS");
        scan.nextLine();
        System.out.println("Event Name:");
        String name = scan.nextLine();
        System.out.println("Location:");
        String location = scan.nextLine();
        System.out.println("Time (enter as HHMM using 24 clock):");
        int time = scan.nextInt();
        scan.nextLine();
        System.out.println("Reminder:");
        String reminder = scan.nextLine();

        Event newEntry = new Event(name, location, time, reminder);

        if (planner.checkDuplicate(newEntry)) {
            System.out.println("Warning: An event already exists at this time!");
        } else {
            planner.addEvent(newEntry);
            System.out.println("Event Successfully added to planner!");
        }
    }

    // EFFECTS: returns the number of events
    private void getNumberOfEvents() {
        System.out.println(planner.numberOfEvents());
    }

    //EFFECTS: prints the list of events in the planner
    private void viewEvents() {
        if (planner.numberOfEvents() == 0) {
            System.out.println("You do not have any events in your planner.");
        }
        System.out.print(planner.getListOfEventsDetails());
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

}
