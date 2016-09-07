package com.epam.cars.service;

import com.epam.cars.model.Car;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

@Ignore
public class CarServiceTest {

    public CarServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCars method, of class CarService.
     */
    @Test
    public void testGetCars() {
        System.out.println("getCars");
        CarService instance = new CarService();
        List<Car> expResult = null;
        List<Car> result = instance.getCars();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCarsByModel method, of class CarService.
     */
    @Test
    public void testGetCarsByModel() {
        System.out.println("getCarsByModel");
        String search = "";
        CarService instance = new CarService();
        List<Car> expResult = null;
        List<Car> result = instance.getCarsByModel(search);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveCar method, of class CarService.
     */
    @Test
    public void testSaveCar() {
        System.out.println("saveCar");
        Car car = null;
        CarService instance = new CarService();
        instance.saveCar(car);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCar method, of class CarService.
     */
    @Test
    public void testGetCar() {
        System.out.println("getCar");
        Long id = null;
        CarService instance = new CarService();
        Car expResult = null;
        Car result = instance.getCar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCar method, of class CarService.
     */
    @Test
    public void testUpdateCar() {
        System.out.println("updateCar");
        Car car = null;
        CarService instance = new CarService();
        instance.updateCar(car);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
