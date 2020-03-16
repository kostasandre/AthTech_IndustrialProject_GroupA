package repository;

import model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByEmail(String email);
    List<Person> findByEmailAndPassword(String email, String password);
}
