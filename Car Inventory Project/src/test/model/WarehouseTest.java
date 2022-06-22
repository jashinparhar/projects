package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {
    private Warehouse testWarehouse;
    private Car testCar;

    @BeforeEach
    void runBefore() {
        testWarehouse = new Warehouse();
        testCar = new Car(2010, "Honda", "Civic");
    }

    @Test
    void testAddCartoInventoryList() {
        testWarehouse.addCarToInventoryList(testCar);
        assertTrue(testWarehouse.inventorylist.contains(testCar));
    }

    @Test
    void testAddCartoSoldList() {
        testWarehouse.addCarToSoldList(testCar);
        assertTrue(testWarehouse.soldlist.contains(testCar));
    }

    @Test
    void testAddCartoImportingList() {
        testWarehouse.addCarToImportingList(testCar);
        assertTrue(testWarehouse.importinglist.contains(testCar));
    }

    @Test
    void testRemoveCarFromInventory() {
        testWarehouse.addCarToInventoryList(testCar);
        assertTrue(testWarehouse.inventorylist.contains(testCar));
        testWarehouse.removeCarFromInventoryList(testCar);
        assertFalse(testWarehouse.inventorylist.contains(testCar));
    }

    @Test
    void testRemoveCarFromSoldList() {
        testWarehouse.addCarToSoldList(testCar);
        assertTrue(testWarehouse.soldlist.contains(testCar));
        testWarehouse.removeCarFromSoldList(testCar);
        assertFalse(testWarehouse.soldlist.contains(testCar));
    }

    @Test
    void testRemoveCarFromImportingList() {
        testWarehouse.addCarToImportingList(testCar);
        assertTrue(testWarehouse.importinglist.contains(testCar));
        testWarehouse.removeCarFromImportingList(testCar);
        assertFalse(testWarehouse.importinglist.contains(testCar));
    }

    @Test
    void testGetInventoryList() {
        testWarehouse.addCarToInventoryList(testCar);
        assertEquals(testWarehouse.inventorylist, testWarehouse.getInventoryList());
    }

    @Test
    void testGetSoldList() {
        testWarehouse.addCarToSoldList(testCar);
        assertEquals(testWarehouse.soldlist, testWarehouse.getSoldList());
    }

    @Test
    void testClear() {
        testWarehouse.addCarToInventoryList(testCar);
        testWarehouse.addCarToSoldList(testCar);
        testWarehouse.addCarToImportingList(testCar);
        assertFalse(testWarehouse.getImportingList().isEmpty());
        assertFalse(testWarehouse.getInventoryList().isEmpty());
        assertFalse(testWarehouse.getSoldList().isEmpty());
        testWarehouse.clear();
        assertTrue(testWarehouse.getImportingList().isEmpty());
        assertTrue(testWarehouse.getInventoryList().isEmpty());
        assertTrue(testWarehouse.getSoldList().isEmpty());

    }

    @Test
    void testGetImportingList() {
        testWarehouse.addCarToImportingList(testCar);
        assertEquals(testWarehouse.importinglist, testWarehouse.getImportingList());
    }

    @Test
    void testListAllCarsTest() {
        testWarehouse.addCarToInventoryList(testCar);
        assertTrue(testWarehouse.listAllCars().contains("INVENTORY: [{Year: 2010, Make: 'Honda', Model: 'Civic'}], \n" +
                "SOLD: [], \n" +
                "IMPORTING: []"));
    }



}
