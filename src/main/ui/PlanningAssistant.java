package ui;

import model.Day;
import model.Event;

import java.util.Scanner;

// Planning application
public class PlanningAssistant {
    private Day planner;
    private Scanner scan;


    // EFFECTS: runs Planning Assistant
    public PlanningAssistant() {
        runAssistant();
    }

    // MODIFIES: this
    // EFFECTS: runs the Planning Assistant Application
    private void runAssistant() {
        boolean keepGoing = true;
        String command = null;

        createDay();

        while (keepGoing) {
            displayMenu();
            command = scan.next();
            command = command.toLowerCase();

            if (command.equals("c")) {
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
        } else {
            System.out.println("Invalid Selection - Please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the day planner
    private void createDay() {
        planner = new Day();
        scan = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the Day Planner Assistant! Please select from:");
        System.out.println("\ta -> add event");
        System.out.println("\tn -> get number of events");
        System.out.println("\tv -> view day planner");
        System.out.println("\tc -> complete");
    }

    // MODIFIES: this
    // EFFECTS: creates an event with the user information
    private void createEvent() {
        //System.out.println("ENTER EVENT DETAILS");
        System.out.println("Event Name:");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("Location:");
        String location = scan.nextLine();
        System.out.println("Time (enter as HHMM using 24 clock):");
        int time = scan.nextInt();
        scan.nextLine();
        System.out.println("Reminders:");
        String reminders = scan.nextLine();

        Event newEntry = new Event(name, location, time, reminders);

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
        System.out.print(planner.getEventNames());
    }
}
