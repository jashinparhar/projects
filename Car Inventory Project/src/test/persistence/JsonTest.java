package persistence;

import model.Car;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***************************************************************************************
 *    Title: JsonSerializationDemo Source Code
 *    Author: Paul Carter
 *    Date: Oct 17, 2020
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/

public class JsonTest {
    protected void checkCar(String make, String model , Car car) {
        assertEquals(make, car.getMake());
        assertEquals(model, car.getModel());
    }
}