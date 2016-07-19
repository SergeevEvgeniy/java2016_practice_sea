package com.epam.cars;

import com.epam.cars.model.Car;
import java.util.Map;

public interface CarRepository {

    public Map<String, Car> getCars();

    public void saveCar(Car car);
    
    public Car getCar(String id);
    
    public void updateCar(String id,Car car);
}
