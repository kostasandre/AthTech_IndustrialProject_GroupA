package service;


import model.Application;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ApplicationRepository;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ApplicationRepository applicationRepository;


    public Person create(Person person) {
        List<Application> ads = person.getApplications();
        if(ads != null && !ads.isEmpty()) {
            ads.forEach(ad -> {
                ad = this.applicationRepository.save(ad);
            });
            person.setApplications(ads);
        }
        return personRepository.save(person);
    }


    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public List<Application> getAllAdvertise(){
        List<Person> persons =  personRepository.findAll();
        List<Application> ads = new ArrayList<>();
        persons.forEach(p -> {
            List<Application> pads = p.getApplications();
            if(pads != null && !pads.isEmpty()) {
                pads.forEach(a ->{
                    ads.add(a);
                });

            }

        });
        return ads;
    }


    public List<Person> getByEmail(String email) {
        return personRepository.findByEmail(email);
    }


    public Person update(Person person) {
        List<Application> ads = person.getApplications();
        if(ads != null && !ads.isEmpty()) {
            ads.forEach(ad -> {
                ad = this.applicationRepository.save(ad);
            });
            person.setApplications(ads);
        }
        return personRepository.save(person);
        //return null;
    }


    public void deleteAll() {
        personRepository.deleteAll();
    }


    public void deleteByEmail(String email) {
        List<Person> persons = personRepository.findByEmail(email);
        if(persons != null && !persons.isEmpty()) {
            personRepository.delete(persons.get(0));
        }
    }

    public List<Person> findByEmailAndPassword(String email, String password){
        return personRepository.findByEmailAndPassword(email, password);
    }

}

