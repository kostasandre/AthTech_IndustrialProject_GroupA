package service;


import model.Advertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdvertiseRepository;

import java.util.List;

@Service
public class AdvertiseService {
    @Autowired
    private AdvertiseRepository _advertiseRepository;

    public List<Advertise> getAll(){
        List<Advertise> allAds = _advertiseRepository.findAll();
        return allAds;
    }

    public String deleteAd(String id) {
        _advertiseRepository.deleteById(id);
        return "Record was deleted!";
    }
}
