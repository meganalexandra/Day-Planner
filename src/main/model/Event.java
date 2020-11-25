package model;

import exceptions.EmptyNameException;
import exceptions.InvalidTimeException;
import org.json.JSONObject;
import persistence.Writable;

// represents an entry for an event/activity with a name, location, time and reminders
public class Event implements Writable {
    private String name;       // name of the event
    private String location;   // location of the event
    private int time;          // time of event entered as HHMM according to a 24 hour clock
    private String reminder;   // reminders for the event


    // EFFECTS: creates an Entry with entryName, time and an optional location or reminder
    public Event(String entryName, String location, int time, String reminder)
            throws InvalidTimeException, EmptyNameException {
        if (entryName.isEmpty()) {
            throw new EmptyNameException("name field empty");
        }
        if (time < 0 || time > 2400) {
            throw new InvalidTimeException(this.getTime() + "is an invalid time");
        }
        name = entryName;
        this.location = location;
        this.time = time;
        this.reminder = reminder;
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

    public String getReminder() {
        return reminder;
    }

    public String getEventDetails() {
        reminder = this.getReminder();
        if (reminder.isEmpty()) {
            reminder = "N/A";
        }
        if (location.isEmpty()) {
            return "\n" + this.getTime() + " : " + this.getName() + " (reminder:"
                    + reminder + ")" + "\n";
        } else {
            return "\n" + this.getTime() + " : " + this.getName() + " at " + this.getLocation() + " (reminder:"
                    + reminder + ")" + "\n";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entryName", name);
        json.put("location", location);
        json.put("time", time);
        json.put("reminder", reminder);
        return json;
    }
}
