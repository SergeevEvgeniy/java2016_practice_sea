package com.epam.cars.service;

import com.epam.cars.CarRepository;
import com.epam.cars.h2.H2CarRepository;
import com.epam.cars.model.Car;
import java.util.List;

public class CarService {
    
    private final CarRepository repo = H2CarRepository.getInstance();
    
    public List<Car> getCars() {
        return repo.getCars();
    }
    
    public void saveCar(Car car) {
        repo.saveCar(car);
    }
    
    public Car getCar(Long id) {
        return repo.getCar(id);
    }
    
    public void updateCar(Car car) {
        repo.updateCar(car);
    }
}
