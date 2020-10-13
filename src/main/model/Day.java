package model;

import java.util.ArrayList;

// represents a list of entries of events/activities for a day
public class Day {
    private ArrayList<Event> listOfEvents;     //  list of events for a day

    // EFFECTS: creates a list of Events
    public Day() {
        listOfEvents = new ArrayList<Event>();
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
    public String getEventNames() {
        String eventNames = "";
        for (Event name : listOfEvents) {
            eventNames += name.getName() + "\n";
        }
        return eventNames;
    }
}


