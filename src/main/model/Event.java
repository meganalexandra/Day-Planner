package model;

import org.json.JSONObject;
import persistence.Writable;

// represents an entry for an event/activity with a name, location, time and reminders
public class Event implements Writable {
    private String name;       // name of the event
    private String location;   // location of the event
    private int time;          // time of event entered as HHMM according to a 24 hour clock
    private String reminder;   // reminders for the event

    /* REQUIRES: name, location, time in the format HHMM as an integer <= 2400 and reminder
     * EFFECTS: creates an Entry with entryName, location, time and reminder
     */
    public Event(String entryName, String location, int time, String reminder) {
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
        return "\n" + this.getTime() + " : " + this.getName() + " at " + this.getLocation() + " (reminder:"
                + this.getReminder() + ")" + "\n";
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
