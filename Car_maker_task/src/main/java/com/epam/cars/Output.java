package com.epam.cars;

import com.epam.cars.model.Car;

/**
 * Class to output Car information
 *
 * @author Æåíÿ
 */
public class Output {

    /**
     * Prints car information into console
     *
     * @param car given car
     */
    public void write(final Car car) {
        System.out.println(car.getMaker().getName() + " "
                + car.getModel() + " "
                + car.getYear() + " " + car.getColor());
    }
}
