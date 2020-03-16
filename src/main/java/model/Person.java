package model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Person {
    public String getId() {
        return id;
    }

    @Id
    String id;
    String email;
    String password;
    String firstName;
    String lastName;
    int age;
    @DBRef
    List<Advertise> advertises;


    public Person(String email, String password, String firstName, String lastName, int age, List<Advertise> advertises) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.advertises = advertises;
    }


    public List<Advertise> getAdvertises() {
        return advertises;
    }


    public void setAdvertises(List<Advertise> advertises) {
        this.advertises = advertises;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
