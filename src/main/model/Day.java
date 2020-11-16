package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a list of entries of events/activities for a day
public class Day implements Writable {
    private String date;
    private ArrayList<Event> listOfEvents;     //  list of events for a day

    // EFFECTS: creates a list of Events
    public Day(String date) {
        this.date = date;
        listOfEvents = new ArrayList<Event>();
    }

    public String getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the listOfEvents for the day
    public void addEvent(Event event) {
        listOfEvents.add(event);
    }

    // MODIFIES: this
    // EFFECTS: removes event from the listOfEvents
    public void removeEvent(Event event) {
        listOfEvents.remove(event);
    }

    // EFFECTS: returns the number of events in the day
    public int numberOfEvents() {
        return listOfEvents.size();
    }

    // MODIFIES: this
    // EFFECTS: gets the event at the given index in listOfEvents for the day
    public Event getEvent(int position) {
        return listOfEvents.get(position);
    }

    // EFFECTS: returns an unmodifiable list of events in the day planner
    public List<Event> getEvents() {
        return Collections.unmodifiableList(listOfEvents);
    }


    // EFFECTS: returns true if an event already exists at the same time, false otherwise
    public Boolean checkDuplicate(Event inputEvent) {
        Boolean result;
        result = false;
        for (Event event : listOfEvents) {
            if (event.getTime() == inputEvent.getTime()) {
                result = true;
                break;
            }
        }
        return result;
    }

    // REQUIRES: non-empty list
    // EFFECTS: returns the names of the Events in the planner
    public String getListOfEventsDetails() {
        String events = "";
        for (Event event : listOfEvents) {
            events += "\n" + event.getTime() + " : " + event.getName() + " at " + event.getLocation() + " (reminder:"
                    + event.getReminder() + ")" + "\n";
        }
        return events;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("listOfEvents", eventsToJson());
        return json;
    }


    // EFFECTS: returns events in this day as a JSON array
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event e : listOfEvents) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}


