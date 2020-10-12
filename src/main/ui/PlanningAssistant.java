package ui;

import model.Day;
import model.Event;

import java.util.Scanner;

// Planning application
public class PlanningAssistant {
    private Day planner;
    private Scanner input;

    // EFFECTS: runs the Planning Assistant Application
    public PlanningAssistant() {
        boolean keepGoing = true;
        String command = null;

        runAssistant();

        while (keepGoing) {
            displayMenu();
            command = input.next();
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
        } else if (command.equals("d")) {
            deleteEvent();
        } else if (command.equals("v")) {
            viewEvents();
        } else {
            System.out.println("Invalid Selection - Please try again");
        }
    }


    // MODIFIES: this
    // EFFECTS: processes input from user
    private void runAssistant() {
        createDay();
    }

    // MODIFIES: this
    // EFFECTS: creates the day planner
    private void createDay() {
        planner = new Day();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add event");
        System.out.println("\td -> delete event");
        System.out.println("\tv -> view day planner");
        System.out.println("\tc -> complete");
    }

    private void createEvent() {
    }

    private void deleteEvent() {
    }

    private void viewEvents() {
    }
}
