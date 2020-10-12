package model;

import java.util.ArrayList;

// represents a list of entries of events/activities for a day
public class Day {
    private ArrayList<Event> listOfEvents;     //  list of events for a day

    public Day() {
        listOfEvents = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the listOfEvents for the day
    public void add(Event event) {
        listOfEvents.add(event);
    }

    // MODIFIES: this
    // EFFECTS: removes event to the listOfEvents for the day
    public void remove(Event event) {
        listOfEvents.remove(event);
    }

    public int numberOfEvents() {
        if (listOfEvents == null) {
            return 0;
        } else {
            return listOfEvents.size();
        }
    }

    // MODIFIES: this
    // EFFECTS: gets the event at the given index in listOfEvents for the day
    public Event getEvent(int position) {
        return listOfEvents.get(position);
    }

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

    // MODIFIES: this
    // EFFECTS: adds event to the listOfEvents for the day
    public Boolean addEvent(Event event) {
        if (checkDuplicate(event)) {
            return false;
        } else {
            listOfEvents.add(event);
            return true;
        }
    }
}
