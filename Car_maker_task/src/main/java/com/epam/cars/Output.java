package com.epam.cars;


import com.epam.cars.model.Car;

public class Output {

    public void write(final Car car) {
        System.out.println(car.getMaker().getName() + " "
                + car.getModel() + " "
                + car.getYear() + " " + car.getColor());
    }
}
