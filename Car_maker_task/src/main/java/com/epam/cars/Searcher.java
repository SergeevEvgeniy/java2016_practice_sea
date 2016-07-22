package com.epam.cars;

import com.epam.cars.model.Car;
import java.util.HashMap;
import java.util.Map;

public class Searcher {

    private final MapCarRepository repo = MapCarRepository.getInstance();

    public Map<String, Car> searchCar(String searchParam) {
        Map<String, Car> matchList = new HashMap<>();

       /* try {
            int param = Integer.parseInt(searchParam);
            int idF = 1;
            for (String key : repo.getCars().keySet()) {
                if ((repo.getCar(key).getMaker().getFoundYear() == param)
                        || (repo.getCar(key).getYear() == param)) {
                    matchList.put(Integer.toString(idF), repo.getCar(key));
                    idF++;
                }
            }
        } catch (NumberFormatException ex) {
            int idF = 1;
            for (String key : repo.getCars().keySet()) {
                if ((repo.getCar(key).getMaker().getAdress().equals(searchParam))
                        || (repo.getCar(key).getMaker().getName().equals(searchParam))
                        || (repo.getCar(key).getColor().equals(searchParam))
                        || (repo.getCar(key).getModel().equals(searchParam))) {
                    matchList.put(Integer.toString(idF), repo.getCar(key));
                    idF++;
                }
            }
        }*/
        return matchList;
    }
}
