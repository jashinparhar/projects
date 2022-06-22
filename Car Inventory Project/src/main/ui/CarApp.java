package ui;

import model.Car;
import model.Warehouse;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



// Car Warehouse Application
public class CarApp {
    private Warehouse warehouse;
    private Car hondaCivic;
    private Car toyotaCorolla;
    private Car audiS4;
    private Car customCar;
    private Scanner input;
    private static final String JSON_STORE = "./data/warehouse.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs and runs the car warehouse application in console
    public CarApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        warehouse = new Warehouse();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runInventoryApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and stores it into command variable
    private void runInventoryApp() {
        boolean continuing = true;
        String command = null;
        init();
        while (continuing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                continuing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes users input and returns a certain method.
    private void processCommand(String command) {
        if (command.equals("i")) {
            addToInventory();
        } else if (command.equals("s")) {
            addToSold();
        } else if (command.equals("imp")) {
            addToImport();
        } else if (command.equals("list")) {
            listCars();
        } else if (command.equals("d")) {
            carDetails();
        } else if (command.equals("save")) {
            saveWarehouse();
        } else if (command.equals("load")) {
            loadWarehouse();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes cars and warehouse
    private void init() {
        warehouse = new Warehouse();
        hondaCivic = new Car(2010, "Honda", "Civic");
        toyotaCorolla = new Car(2015, "Toyota", "Corolla");
        audiS4 = new Car(2020, "Audi", "S4");
        customCar = new Car(0, "make", "model");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options and selections to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ti -> add to inventory list");
        System.out.println("\ts -> add to sold list");
        System.out.println("\timp -> add to importing list");
        System.out.println("\td -> details of selected car");
        System.out.println("\tlist -> list all cars in warehouse");
        System.out.println("\tsave -> save this warehouse");
        System.out.println("\tload -> load warehouse");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: adds a certain car to inventory list
    private void addToInventory() {
        Car selected = selectCar();
        warehouse.addCarToInventoryList(selected);
        System.out.println("Added to inventory list!");
    }



    // EFFECTS: saves the warehouse to file
    private void saveWarehouse() {
        try {
            jsonWriter.open();
            jsonWriter.write(warehouse);
            jsonWriter.close();
            System.out.println("Saved warehouse to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads warehouse from file
    private void loadWarehouse() {
        try {
            warehouse = jsonReader.read();
            System.out.println("Loaded warehouse from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a certain car to sold list
    private void addToSold() {
        Car selected = selectCar();
        warehouse.addCarToSoldList(selected);
        System.out.println("Added to sold list!");
    }

    // MODIFIES: this
    // EFFECTS: adds a certain car to import list
    private void addToImport() {
        Car selected = selectCar();
        warehouse.addCarToImportingList(selected);
        System.out.println("Added to import list!");
    }

    // EFFECTS: prompts user to select which car they want to modify/want info from
    private Car selectCar() {
        String selection = "";  // force entry into loop
        while (!(selection.equals("a") || selection.equals("h") || selection.equals("t")
                || selection.equals("custom"))) {
            System.out.println("a for Audi\nh for Honda\nt for Toyota");
            System.out.println("custom for Custom Car");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("a")) {
            return audiS4;
        } else if (selection.equals("t")) {
            return toyotaCorolla;
        } else if (selection.equals("custom")) {
            createCar();
            return customCar;
        } else {
            return hondaCivic;
        }
    }

    // EFFECTS: prints the details of the selected car, along with what list the car is placed in
    private void carDetails() {
        String list;
        Car selected = selectCar();
        if (warehouse.getInventoryList().contains(selected)) {
            list = "inventory";
        } else if (warehouse.getImportingList().contains(selected)) {
            list = "importing";
        } else if (warehouse.getSoldList().contains(selected)) {
            list = "sold";
        } else {
            list = "no list";
        }

        System.out.println("CAR: " + selected.toString() + "\nLIST: " + list);
    }

    // EFFECTS: list of all the cars in the warehouse sorted by list.
    private void listCars() {
        System.out.println("LIST OF CARS: " + warehouse.listAllCars());
    }

    // EFFECTS: creates a custom car
    private void createCar() {
        System.out.println("Enter car year: ");
        int customYear = input.nextInt();
        System.out.println("Enter car make: ");
        String customMake = input.next();
        System.out.println("Enter car model: ");
        String customModel = input.next();
        Car customCar = new Car(customYear, customMake, customModel);
        this.customCar = customCar;
    }
}
