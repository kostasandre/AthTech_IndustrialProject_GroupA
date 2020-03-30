package service;


import model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository _applicationRepository;

    public List<Application> getAll(){
        List<Application> allApps = _applicationRepository.findAll();
        return allApps;
    }

    public String deleteAd(String id) {
        _applicationRepository.deleteById(id);
        return "Record was deleted!";
    }
}
