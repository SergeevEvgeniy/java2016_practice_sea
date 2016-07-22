package com.epam.cars;

import com.epam.cars.model.Maker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMakerRepository implements MakerRepository {

    private final Map<Long, Maker> makers = new HashMap<>();
    private long lastMakerId = 0;

    public static MapMakerRepository instance;

    public static synchronized MapMakerRepository getInstance() {
        if (instance == null) {
            instance = new MapMakerRepository();
        }
        return instance;
    }

    private MapMakerRepository() {
        this.saveMaker(new Maker("Fiat", "Italy", 1899));
        this.saveMaker(new Maker("VW", "Germany", 1900));
        this.saveMaker(new Maker("Ford", "USA", 1925));
        this.saveMaker(new Maker("Honda", "Japan", 1946));
        this.saveMaker(new Maker("Isuzu", "Japan", 1916));
    }

    @Override
    public List<Maker> getMakers() {
        return new ArrayList(makers.values());
    }

    @Override
    public void saveMaker(Maker maker) {
        maker.setId(++lastMakerId);
        makers.put(lastMakerId, maker);
    }

    @Override
    public Maker getMaker(Long id) {
        return makers.get(id);
    }

    @Override
    public void updateMaker(Long id, Maker maker) {
        makers.put(id, maker);
    }
}
