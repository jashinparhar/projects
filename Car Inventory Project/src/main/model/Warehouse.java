package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Warehouse object, contains all the lists of where the cars will be stored.
public class Warehouse implements Writable {
    ArrayList<Car> inventorylist;               // list of cars in inventory
    ArrayList<Car> soldlist;                    // list of sold cars
    ArrayList<Car> importinglist;               // list of cars being imported

    /*
     * EFFECTS: constructs Warehouse
     */
    public Warehouse() {
        inventorylist = new ArrayList<Car>();
        soldlist = new ArrayList<Car>();
        importinglist = new ArrayList<Car>();
    }

    /*
    * EFFECTS: add car c into inventory list, set inventory true
    * MODIFIES: this, and car
     */
    public void addCarToInventoryList(Car c) {
        inventorylist.add(c);
        c.setSold(false);
        c.setImporting(false);
        c.setInventory(true);
        EventLog.getInstance().logEvent(new Event(c.getModel()  + " Added to Inventory List."));
    }

    /*
     * EFFECTS: add car c into sold list, set sold true
     * MODIFIES: this, and car
     */
    public void addCarToSoldList(Car c) {
        soldlist.add(c);
        c.setInventory(false);
        c.setImporting(false);
        c.setSold(true);
        EventLog.getInstance().logEvent(new Event(c.getModel()  + " Added to Sold List."));
    }

    /*
     * EFFECTS: add car c into importing list, set importing true
     * MODIFIES: this, and car
     */
    public void addCarToImportingList(Car c) {
        importinglist.add(c);
        c.setSold(false);
        c.setInventory(false);
        c.setImporting(true);
        EventLog.getInstance().logEvent(new Event(c.getModel()  + " Added to Importing List."));
    }

    /*
     * EFFECTS: remove car c from inventory list
     * MODIFIES: this, and car
     */
    public void removeCarFromInventoryList(Car c) {
        inventorylist.remove(c);
        c.setInventory(false);
        EventLog.getInstance().logEvent(new Event(c.getModel()  + " Removed from Inventory List."));
    }

    /*
     * EFFECTS: remove car c from sold list
     * MODIFIES: this, and car
     */
    public void removeCarFromSoldList(Car c) {
        soldlist.remove(c);
        c.setSold(false);
        EventLog.getInstance().logEvent(new Event(c.getModel()  + " Removed from Sold List."));
    }

    /*
     * EFFECTS: remove car c from importing list
     * MODIFIES: this, and car
     */
    public void removeCarFromImportingList(Car c) {
        importinglist.remove(c);
        c.setImporting(false);
        EventLog.getInstance().logEvent(new Event(c.getModel()  + " Removed from Importing List."));
    }

    public ArrayList<Car> getInventoryList() {
        return inventorylist;
    }

    public ArrayList<Car> getSoldList() {
        return soldlist;
    }

    public ArrayList<Car> getImportingList() {
        return importinglist;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventorylist", inventoryListToJson());
        json.put("soldlist", soldListToJson());
        json.put("importinglist", importingListToJson());

        return json;
    }

    public void clear() {
        inventorylist.clear();
        importinglist.clear();
        soldlist.clear();
        EventLog.getInstance().logEvent(new Event("Cleared warehouse."));
    }

    // EFFECTS: returns inventory list in this warehouse as a JSON array
    private JSONArray inventoryListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Car c : inventorylist) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns importing list in this warehouse as a JSON array
    private JSONArray importingListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Car c : importinglist) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns sold list in this warehouse as a JSON array
    private JSONArray soldListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Car c : soldlist) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    /*
     * EFFECTS: list of all cars sorted by their own list
     */
    public String listAllCars() {
        return "\nINVENTORY: " + inventorylist.toString()
                + ", \nSOLD: " + soldlist.toString()
                + ", \nIMPORTING: " + importinglist.toString();
    }
}
