package service;


import model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository _advertiseRepository;

    public List<Application> getAll(){
        List<Application> allApps = _advertiseRepository.findAll();
        return allApps;
    }

    public String deleteAd(String id) {
        _advertiseRepository.deleteById(id);
        return "Record was deleted!";
    }
}
