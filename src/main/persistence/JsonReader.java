package persistence;

import model.Earning;
import model.Payment;
import model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Referenced JSONSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonReader {

    private String source;

    // EFFECTS: construct reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads program from file and returns it and 
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: read source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double bankAmt = jsonObject.getDouble("bankAmt");
        User user = new User(name, bankAmt);
        user.setBudget(jsonObject.getDouble("budget"));
    
        addEarnings(user, jsonObject);
        addPayments(user, jsonObject);

        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses earnings from JSON object and adds them to user
    private void addEarnings(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("earnings");
        for (Object json : jsonArray) {
            JSONObject nextEarning = (JSONObject) json;
            addEarning(user, nextEarning);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses earning from JSON object and adds it to workroom
    private void addEarning(User user, JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        double amount = jsonObject.getDouble("amount");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        user.addEarning(category, amount, date);

    }

    // MODIFIES: user
    // EFFECTS: parses payments from JSON object and adds them to user
    private void addPayments(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("payments");
        for (Object json : jsonArray) {
            JSONObject nextPayment = (JSONObject) json;
            addPayment(user, nextPayment);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses payment from JSON object and adds them to user
    private void addPayment(User user, JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        double amount = jsonObject.getDouble("amount");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        user.addPayment(category, amount, date);
    }




}
