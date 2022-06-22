package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car testCar;

    @BeforeEach
    void runBefore() {
        testCar = new Car(2010, "Honda", "Civic");
    }

    @Test
    void testConstructor() {
        assertEquals(2010, testCar.getYear());
        assertEquals("Honda", testCar.getMake());
        assertEquals("Civic", testCar.getModel());
        assertTrue(testCar.getMake().length() > 0);
        assertTrue(testCar.getModel().length() > 0);
    }

    @Test
    void testStringZeroConstructor() {
        testCar = new Car(2010, "", "");
        assertEquals("make", testCar.getMake());
        assertEquals("model", testCar.getModel());
    }

    @Test
    void testInventory() {
        testCar.setInventory(true);
        assertTrue(testCar.isInventory());
        assertFalse(testCar.isImporting());
        assertFalse(testCar.isSold());
    }

    @Test
    void testIsSold() {
        testCar.setSold(true);
        assertFalse(testCar.isInventory());
        assertFalse(testCar.isImporting());
        assertTrue(testCar.isSold());
    }

    @Test
    void testIsImporting() {
        testCar.setImporting(true);
        assertFalse(testCar.isInventory());
        assertTrue(testCar.isImporting());
        assertFalse(testCar.isSold());
    }

    @Test
    void testSetYear() {
        testCar.setYear(2015);
        assertEquals(2015, testCar.getYear());
    }

    @Test
    void testSetMake() {
        testCar.setMake("Audi");
        assertEquals("Audi", testCar.getMake());
    }

    @Test
    void testSetModel() {
        testCar.setModel("S4");
        assertEquals("S4", testCar.getModel());
    }

    @Test
    void testToString() {
        assertTrue(testCar.toString().contains("{Year: 2010, Make: 'Honda', Model: 'Civic'}"));
    }

}