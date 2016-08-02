package com.epam.cars.map;

import com.epam.cars.CarRepository;
import com.epam.cars.MakerRepository;
import com.epam.cars.model.Car;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCarRepository implements CarRepository {

    private final Map<Long, Car> cars = new HashMap<>();
    private long lastCarId = 0;
    private final MakerRepository makerRep = MapMakerRepository.getInstance();

    private MapCarRepository() {
        this.saveCar(new Car(makerRep.getMaker(2L), "Audi a4", 1995, "red"));
        this.saveCar(new Car(makerRep.getMaker(2L), "VW Polo", 2005, "green"));
        this.saveCar(new Car(makerRep.getMaker(1L), "Fiat Panda", 2004, "black"));
        this.saveCar(new Car(makerRep.getMaker(1L), "Alfa Romeo giulia", 2002, "yelow"));
        this.saveCar(new Car(makerRep.getMaker(3L), "mustang", 2003, "red"));
        this.saveCar(new Car(makerRep.getMaker(3L), "escort", 2013, "blue"));
        this.saveCar(new Car(makerRep.getMaker(4L), "civic", 2001, "pink"));
        this.saveCar(new Car(makerRep.getMaker(5L), "piazza", 1995, "red"));
        this.saveCar(new Car(makerRep.getMaker(4L), "joker", 2007, "black"));
        this.saveCar(new Car(makerRep.getMaker(5L), "trooper", 1990, "black"));
        this.saveCar(new Car(makerRep.getMaker(2L), "Audi a4", 2005, "green"));
    }

    public static MapCarRepository instance;

    public static synchronized MapCarRepository getInstance() {
        if (instance == null) {
            instance = new MapCarRepository();
        }
        return instance;
    }

    @Override
    public List<Car> getCars(String search) {
        return new ArrayList(cars.values());
    }

    @Override
    public void saveCar(final Car car) {
        lastCarId = cars.size();
        car.setId(++lastCarId);
        cars.put(lastCarId, car);
    }

    @Override
    public Car getCar(Long id) {
        return cars.get(id);
    }

    @Override
    public void updateCar(Car car) {
        cars.put(car.getId(), car);
    }
}
