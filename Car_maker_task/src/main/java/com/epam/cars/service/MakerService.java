package com.epam.cars.service;

import com.epam.cars.MakerRepository;
import com.epam.cars.model.Maker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakerService {
    
    @Autowired
    private MakerRepository repo;
    
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
