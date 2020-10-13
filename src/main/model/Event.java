package model;

// represents an entry for an event/activity with a name, location, time and reminders
public class Event {
    private String name;       // name of the event
    private String location;   // location of the event
    private int time;          // time of event entered as HHMM according to a 24 hour clock
    private String reminders;   // reminders for the event

    /* REQUIRES: name, location, time in the format HHMM according to a 24 clock and reminders
     * EFFECTS: creates an Entry with entryName, location, time and listOfReminders
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
