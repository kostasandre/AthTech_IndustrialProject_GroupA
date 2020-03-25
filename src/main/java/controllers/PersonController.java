package controllers;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.PersonService;

import java.util.List;

@CrossOrigin() //origins = "http://localhost:4078" <-- Front End URL
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping("/create")
    public ResponseEntity<String> create(@RequestBody Person person){
        List<Person> exists = personService.getByEmail(person.getEmail());
        if(exists != null && !exists.isEmpty()) {
            return new ResponseEntity<String>("Person with email: " + person.getEmail() + " already exists!", HttpStatus.CONFLICT);
        }
        Person p = personService.create(person);
        return new ResponseEntity<>("Person with email: " + p.getEmail() + " was created!", HttpStatus.OK);
    }

    @RequestMapping("/get")
    public ResponseEntity<List<Person>> getPerson(@RequestParam String email) {
        return new ResponseEntity<List<Person>>(personService.getByEmail(email), HttpStatus.OK);
    }

    @RequestMapping("/getAll")
    public List<Person> getPerson() {
        return personService.getAll();
    }



    @RequestMapping("/update")
    public Person update(@RequestBody Person person) {
        List<Person> ps = personService.getByEmail(person.getEmail());
        Person p = ps.get(0);
        p.setApplications(person.getApplications());
        p = personService.update(p);
        return p;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String email) {
        personService.deleteByEmail(email);
        return "Person with email: " + email + " was deleted!";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll() {
        personService.deleteAll();
        return "All records where deleted!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Person findByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        List<Person> persons = personService.findByEmailAndPassword(email, password);
        if(persons.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found!");
        }

        return persons.get(0);
    }

}
