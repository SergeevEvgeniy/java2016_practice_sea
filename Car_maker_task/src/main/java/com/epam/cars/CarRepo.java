package com.epam.cars;

import com.epam.cars.model.Car;
import com.epam.cars.model.Maker;
import java.util.ArrayList;
import java.util.List;

public class CarRepo {

    private List<Car> cars = new ArrayList<>();
    private List<Maker> makers = new ArrayList<>();

    static CarRepo instance;

    private CarRepo() {
    }

    public static synchronized CarRepo getInstance() {
        if (instance == null) {
            instance = new CarRepo();
        }
        return instance;
    }

    public List<Car> getCars() {
        if (cars.isEmpty()) {
            getDefCars();
        }
        return cars;
    }

    public void SetCar(final String name, final String adress, final int foundYear,
            final String model, final int year, final String color) {
        cars.add(new Car(new Maker(name, adress, foundYear), model, year, color));
    }

    public List<Car> getDefCars() {
        makers = new ArrayList<>();
        cars = new ArrayList<>();

        makers.add(new Maker("Fiat", "Italy", 1899));
        makers.add(new Maker("VW", "Germany", 1900));
        makers.add(new Maker("Ford", "USA", 1925));
        makers.add(new Maker("Honda", "Japan", 1946));
        makers.add(new Maker("Isuzu", "Japan", 1916));

        cars.add(new Car(makers.get(1), "Audi a4", 1995, "red"));
        cars.add(new Car(makers.get(1), "VW Polo", 2005, "green"));
        cars.add(new Car(makers.get(0), "Fiat Panda", 2004, "black"));
        cars.add(new Car(makers.get(0), "Alfa Romeo giulia", 2002, "yelow"));
        cars.add(new Car(makers.get(2), "mustang", 2003, "red"));
        cars.add(new Car(makers.get(2), "escort", 2013, "blue"));
        cars.add(new Car(makers.get(3), "civic", 2001, "pink"));
        cars.add(new Car(makers.get(4), "piazza", 1995, "red"));
        cars.add(new Car(makers.get(3), "joker", 2007, "black"));
        cars.add(new Car(makers.get(4), "trooper", 1990, "black"));
        cars.add(new Car(makers.get(1), "Audi a4", 2005, "green"));

        return cars;
    }
}
