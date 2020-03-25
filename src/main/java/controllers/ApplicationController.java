package controllers;

import model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ApplicationService;

import java.util.List;

@RestController
@CrossOrigin()
public class ApplicationController {

    @Autowired
    private ApplicationService _applicationService;

    @RequestMapping("/getAllAds")
    public List<Application> getAll() {
        return _applicationService.getAll();
    }

    @RequestMapping("/deleteAd")
    public String deleteAd(@RequestParam String id) {
        return _applicationService.deleteAd(id);
    }
}
