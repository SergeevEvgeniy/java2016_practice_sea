/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maker_task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Женя
 */
public class Car_maker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        List<Maker> makers = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

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

        for (Car c : cars) {
            System.out.println("Company " + c._maker._name + " "
                    + c._maker._adress + " " + c._maker._found_year);
            System.out.println("Car " + c._mark + " " + c._year + " " + c._color);
        }
    }

}
