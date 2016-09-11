package com.epam.cars.service;

import com.epam.cars.CarRepository;
import com.epam.cars.model.Car;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository repo;

    public List<Car> getCars() {
        return repo.getCars();
    }

    public List<Car> getCarsByModel(String search) {
        return repo.getCarsByModel(search);
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
