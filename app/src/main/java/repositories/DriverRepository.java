package repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import entities.Driver;

public interface DriverRepository extends MongoRepository<Driver, String>{

    Driver findByDriverNum(String driverNum);

}
