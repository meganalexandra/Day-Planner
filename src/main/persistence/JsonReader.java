package persistence;

import model.Event;
import model.Day;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// modelled after https://github.com/stleary/JSON-java.git
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads day from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Day read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses day from JSON object and returns it
    private Day parseDay(JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        Day day = new Day(date);
        addEvents(day, jsonObject);
        return day;
    }

    // MODIFIES: day
    // EFFECTS: parses events from JSON object and adds them to day
    private void addEvents(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfEvents");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(day, nextEvent);
        }
    }

    // MODIFIES: day
    // EFFECTS: parses events from JSON object and adds it to workroom
    private void addEvent(Day day, JSONObject jsonObject) {
        String name = jsonObject.getString("entryName");
        String location = jsonObject.getString("location");
        int time = jsonObject.getInt("time");
        String reminder = jsonObject.getString("reminder");
        Event event = new Event(name,location, time, reminder);
        day.addEvent(event);
    }
}
