package com.epam.cars;

import com.epam.cars.model.Car;
import java.util.List;

public interface CarRepository {

    public List<Car> getCars();

    public void saveCar(Car car);

    public Car getCar(Long id);

    public void updateCar(Car car);
}
