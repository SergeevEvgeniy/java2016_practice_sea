package com.epam.cars.service;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
        CarService mock = new CarService();

        ArrayList list = new ArrayList();
        list.add(1);
        when(mock.getCars()).thenReturn(list);

        assertEquals(mock.getCars(), 1);
    }
}
