package model;

import java.util.ArrayList;

// represents an entry for an event/activity with a name, location, time and reminders
public class Event {
    private String name;       // name of the event
    private String location;   // location of the event
    private int time;          // time of event according to a 24 hour clock
    private String reminders;   // reminders for the event

    /* REQUIRES: name, date in the format MM/DD/YY and time as an integer between 1 and 24
     * EFFECTS: creates an Entry with entryName, date, time and listOfReminders
     */
    public Event(String entryName, String location, int time, String listOfReminders) {
        name = entryName;
        this.location = location;
        this.time = time;
        reminders = listOfReminders;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getTime() {
        return time;
    }

    public String getReminders() {
        return reminders;
    }

}
