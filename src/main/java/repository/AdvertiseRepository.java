package repository;

import model.Advertise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvertiseRepository extends MongoRepository<Advertise, String> {
}
