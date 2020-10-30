package persistence;

import org.json.JSONObject;

// copied from https://github.com/stleary/JSON-java.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}