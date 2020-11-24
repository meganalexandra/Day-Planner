package ui;

import exceptions.EmptyNameException;
import exceptions.InvalidTimeException;

import java.io.FileNotFoundException;

public class MainUI {
    public static void main(String[] args) throws InvalidTimeException, EmptyNameException {
        try {
            new PlanningAssistant();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

