package persistence;

import model.Car;
import model.Warehouse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/***************************************************************************************
 *    Title: JsonSerializationDemo Source Code
 *    Author: Paul Carter
 *    Date: Oct 17, 2020
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/

// Represents a reader that reads warehouse from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads warehouse from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Warehouse read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWarehouse(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses warehouse from JSON object and returns it
    private Warehouse parseWarehouse(JSONObject jsonObject) {
        Warehouse wh = new Warehouse();
        addCars(wh, jsonObject);
        return wh;
    }

    // MODIFIES: wh
    // EFFECTS: parses lists from JSON object and adds them to warehouse
    private void addCars(Warehouse wh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventorylist");
        for (Object json : jsonArray) {
            JSONObject nextCar = (JSONObject) json;
            addCar(wh, nextCar);
        }
        JSONArray jsonArray2 = jsonObject.getJSONArray("importinglist");
        for (Object json : jsonArray2) {
            JSONObject nextCar = (JSONObject) json;
            addCar(wh, nextCar);
        }
        JSONArray jsonArray3 = jsonObject.getJSONArray("soldlist");
        for (Object json : jsonArray3) {
            JSONObject nextCar = (JSONObject) json;
            addCar(wh, nextCar);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses cars from JSON object and adds it to its respective list
    private void addCar(Warehouse wh, JSONObject jsonObject) {
        String make = jsonObject.getString("make");
        String model = jsonObject.getString("model");
        int year = jsonObject.getInt("year");
        boolean sold = jsonObject.getBoolean("sold");
        boolean inventory = jsonObject.getBoolean("inventory");
        boolean importing = jsonObject.getBoolean("importing");
        Car car = new Car(year, make, model);
        if (sold) {
            wh.addCarToSoldList(car);
        } else if (inventory) {
            wh.addCarToInventoryList(car);
        } else if (importing) {
            wh.addCarToImportingList(car);
        }
    }
}