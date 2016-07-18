package com.epam.cars;

import com.epam.cars.model.Car;
import com.epam.cars.model.Maker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepo implements CarRepository {

    private Map<String, Car> cars = new HashMap<String, Car>();
    private List<Maker> makers = new ArrayList<>();

    public static CarRepo instance;

    private CarRepo() {
    }

    public static synchronized CarRepo getInstance() {
        if (instance == null) {
            instance = new CarRepo();
        }
        return instance;
    }

    @Override
    public Map<String, Car> getCars() {
        if (cars.isEmpty()) {
            getDefCars();
        }
        return cars;
    }

    @Override
    public void saveCar(final Car car) {
        cars.put(Integer.toString(cars.size() + 1), car);

    }

    public Map<String, Car> getDefCars() {

        makers.add(new Maker("Fiat", "Italy", 1899));
        makers.add(new Maker("VW", "Germany", 1900));
        makers.add(new Maker("Ford", "USA", 1925));
        makers.add(new Maker("Honda", "Japan", 1946));
        makers.add(new Maker("Isuzu", "Japan", 1916));

        cars.put("1", new Car(makers.get(1), "Audi a4", 1995, "red"));
        cars.put("2", new Car(makers.get(1), "VW Polo", 2005, "green"));
        cars.put("3", new Car(makers.get(0), "Fiat Panda", 2004, "black"));
        cars.put("4", new Car(makers.get(0), "Alfa Romeo giulia", 2002, "yelow"));
        cars.put("5", new Car(makers.get(2), "mustang", 2003, "red"));
        cars.put("6", new Car(makers.get(2), "escort", 2013, "blue"));
        cars.put("7", new Car(makers.get(3), "civic", 2001, "pink"));
        cars.put("8", new Car(makers.get(4), "piazza", 1995, "red"));
        cars.put("9", new Car(makers.get(3), "joker", 2007, "black"));
        cars.put("10", new Car(makers.get(4), "trooper", 1990, "black"));
        cars.put("11", new Car(makers.get(1), "Audi a4", 2005, "green"));

        return cars;
    }

    @Override
    public Car getCar(String id) {
        return cars.get(id);
    }

    @Override
    public void updateCar(String id, Car car) {
        cars.put(id, car);
    }
}
