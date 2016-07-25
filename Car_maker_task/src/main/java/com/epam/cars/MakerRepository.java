package com.epam.cars;

import com.epam.cars.model.Maker;

public interface MakerRepository {

    //public List<Maker> getMakers();

    public void saveMaker(Maker maker);

    public Maker getMaker(Long id);

    public void updateMaker(Long id, Maker maker);
}
