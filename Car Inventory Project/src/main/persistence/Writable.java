package persistence;

import org.json.JSONObject;

/***************************************************************************************
 *    Title: JsonSerializationDemo Source Code
 *    Author: Paul Carter
 *    Date: Oct 17, 2020
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}