package com.epam.cars.service;

import com.epam.cars.MakerRepository;
import com.epam.cars.h2.H2MakerRepository;
import com.epam.cars.model.Maker;
import java.util.List;

public class MakerService {
    
    private final MakerRepository repo = H2MakerRepository.getInstance();
    
    public List<Maker> getMakers() {
        return repo.getMakers();
    }
    
    public void saveMaker(Maker maker) {
        repo.saveMaker(maker);
    }
    
    public Maker getMaker(Long id) {
        return repo.getMaker(id);
    }
    
    public void updateMaker(Long id, Maker maker) {
        repo.updateMaker(id, maker);
    }
}
