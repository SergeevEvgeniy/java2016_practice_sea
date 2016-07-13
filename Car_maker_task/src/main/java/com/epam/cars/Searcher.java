package com.epam.cars;

import com.epam.cars.model.Car;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private final CarRepo repo = CarRepo.getInstance();

    public List<Car> SearchCar(String searchParam) {
        List<Car> matchList = new ArrayList<>();
        
        try {
            int param = Integer.parseInt(searchParam);
            repo.getCars().stream().filter((c) -> ((c.getMaker().getFoundYear() == param)
                    || (c.getYear() == param))).forEach((c) -> {
                        matchList.add(c);
                    });
        } catch (NumberFormatException ex) {
            repo.getCars().stream().filter((c) -> (c.getMaker().getAdress().equals(searchParam)
                    || c.getMaker().getName().equals(searchParam)
                    || c.getColor().equals(searchParam)
                    || c.getModel().equals(searchParam))).forEach((c) -> {
                        matchList.add(c);
                    });
        }
        return matchList;
    }
}
