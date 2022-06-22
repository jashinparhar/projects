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

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Warehouse wh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyWarehouse.json");
        try {
            Warehouse wh = reader.read();
            assertEquals(0, wh.getSoldList().size());
            assertEquals(0, wh.getImportingList().size());
            assertEquals(0, wh.getInventoryList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralWarehouse.json");
        try {
            Warehouse wh = reader.read();
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
            fail("Couldn't read from file");
        }
    }
}