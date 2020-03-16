package controllers;

import model.Advertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.AdvertiseService;

import java.util.List;

@RestController
@CrossOrigin()
public class AdvertiseController {
    @Autowired
    private AdvertiseService _advertiseService;

    @RequestMapping("/getAllAds")
    public List<Advertise> getAll() {
        return _advertiseService.getAll();
    }

    @RequestMapping("/deleteAd")
    public String deleteAd(@RequestParam String id) {
        return _advertiseService.deleteAd(id);
    }
}
