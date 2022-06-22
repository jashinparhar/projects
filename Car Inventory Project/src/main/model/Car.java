package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a car with the year, make, model, and whether it is sold or being imported
public class Car implements Writable {
    private int year;               // year of car
    private String make;            // make (brand) of car
    private String model;           // model of car
    private boolean sold;           // if the car is sold
    private boolean importing;      // if the car is being imported
    private boolean inventory;      // if the car is in inventory

    /*
     * REQUIRES: make and model to have a non-zero length
     * EFFECTS: constructs a car with a make, model, and year. if make and model string is 0, set make to "make" and
     *  model to "model"
     */
    public Car(int year,String make,String model) {
        this.year = year;

        if (make.length() > 0) {
            this.make = make;
        } else {
            this.make = "make";
        }

        if (model.length() > 0) {
            this.model = model;
        } else {
            this.model = "model";
        }

    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean isSold() {
        return sold;
    }

    public boolean isImporting() {
        return importing;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setImporting(boolean importing) {
        this.importing = importing;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("make", make);
        json.put("model", model);
        json.put("year", year);
        json.put("inventory", inventory);
        json.put("sold", sold);
        json.put("importing", importing);
        return json;
    }

    /*
     * EFFECTS: returns a string representation of the car
     */
    @Override
    public String toString() {
        return "{"
                + "Year: " + year
                + ", Make: '" + make + '\''
                + ", Model: '" + model + '\''
                + "}";
    }
}
