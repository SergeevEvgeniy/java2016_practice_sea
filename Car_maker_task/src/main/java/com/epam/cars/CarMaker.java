package com.epam.cars;

import com.epam.cars.model.Car;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default main class
 *
 * @author Женя
 */
public class CarMaker {

    public static final Logger LOG = LoggerFactory.getLogger(CarMaker.class);

    /**
     * Default main
     *
     *
     */
    public static void main() {

        DefaultCarMaker dcm = new DefaultCarMaker();
        List<Car> cars = dcm.getDefCars();

        Scanner in = new Scanner(System.in, "UTF-8");
        System.out.print("Введите параметр поиска: ");
        String searchParam;
        searchParam = in.nextLine();

        Output output = new Output();
        try {
            int param = Integer.parseInt(searchParam);
            cars.stream().filter((c) -> ((c.getMaker().getFoundYear() == param)
                    || (c.getYear() == param))).forEach((c) -> {
                        output.write(c);
                    });
        } catch (NumberFormatException ex) {
            LOG.info("Insert non-Integer");
            cars.stream().filter((c) -> (c.getMaker().getAdress().equals(searchParam)
                    || c.getMaker().getName().equals(searchParam)
                    || c.getColor().equals(searchParam)
                    || c.getModel().equals(searchParam))).forEach((c) -> {
                        output.write(c);
                    });

        }
    }
}
