package persistence;

import model.Car;
import model.Warehouse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/***************************************************************************************
 *    Title: JsonSerializationDemo Source Code
 *    Author: Paul Carter
 *    Date: Oct 17, 2020
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Warehouse wh = new Warehouse();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Warehouse wh = new Warehouse();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWarehouse.json");
            writer.open();
            writer.write(wh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWarehouse.json");
            wh = reader.read();
            assertEquals(0, wh.getInventoryList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Warehouse wh = new Warehouse();
            wh.addCarToInventoryList(new Car(2020, "Lexus", "RX350"));
            wh.addCarToInventoryList(new Car(2015, "Lexus", "RX450"));
            wh.addCarToSoldList(new Car(2010, "Honda", "Civic"));
            wh.addCarToSoldList(new Car(2010, "Honda", "Accord"));
            wh.addCarToImportingList(new Car(2020, "Toyota", "Corolla"));
            wh.addCarToImportingList(new Car(2020, "Toyota", "Camry"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWarehouse.json");
            writer.open();
            writer.write(wh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWarehouse.json");
            wh = reader.read();
            List<Car> inventorylist = wh.getInventoryList();
            List<Car> soldlist = wh.getSoldList();
            List<Car> importinglist = wh.getImportingList();
            assertEquals(2, inventorylist.size());
            assertEquals(2, soldlist.size());
            assertEquals(2, importinglist.size());
            checkCar("Lexus", "RX350", wh.getInventoryList().get(0));
            checkCar("Lexus", "RX450", wh.getInventoryList().get(1));
            checkCar("Honda", "Civic", wh.getSoldList().get(0));
            checkCar("Honda", "Accord", wh.getSoldList().get(1));
            checkCar("Toyota", "Corolla", wh.getImportingList().get(0));
            checkCar("Toyota", "Camry", wh.getImportingList().get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}