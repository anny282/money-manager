package persistence;

import model.Earning;
import model.Payment;
import model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Referenced JSONSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonReader {

    // EFFECTS: construct reader to read from source file
    public JsonReader(String source) {

    }


    // EFFECTS: reads program from file and returns it and 
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        return null;

    }

    // EFFECTS: read source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: user
    // EFFECTS: parses earnings from JSON object and adds them to user
    private void addEarnings(User user, JSONObject jsonObject) {

    }

    // MODIFIES: user
    // EFFECTS: parses earning from JSON object and adds it to workroom
    private void addEarning(User user, JSONObject jsonObject) {

    }

    // MODIFIES: user
    // EFFECTS: parses payments from JSON object and adds them to user
    private void addPayments(User user, JSONObject jsonObject) {

    }

    // MODIFIES: user
    // EFFECTS: parses payment from JSON object and adds them to user
    private void addPayment(User user, JSONObject jsonObject) {

    }



}
