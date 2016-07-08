package com.epam.cars;


import com.epam.cars.model.Maker;
import com.epam.cars.model.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarMaker {

    public static void main(final String[] args) {

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

        Scanner in = new Scanner(System.in);
        System.out.print("������� �������� ������: ");
        String searchParam;
        searchParam = in.nextLine();

        Output output = new Output();
        try {
            int param = Integer.parseInt(searchParam);
            for (Car c : cars) {
                if ((c.getMaker().getFoundYear() == param)
                        || (c.getYear() == param)) {
                    output.write(c);
                }
            }
        } catch (NumberFormatException ex) {
            for (Car c : cars) {
                if (c.getMaker().getAdress().equals(searchParam)
                        || c.getMaker().getName().equals(searchParam)
                        || c.getColor().equals(searchParam)
                        || c.getModel().equals(searchParam)) {
                    output.write(c);
                }
            }
        }
    }
}