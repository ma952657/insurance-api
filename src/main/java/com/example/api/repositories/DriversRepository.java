package com.example.api.repositories;

import com.example.api.models.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Drivers repository.
 */
@Repository
public interface DriversRepository extends MongoRepository<Driver, String> {
    @Query("{ 'createdOn' : { $gt: ?0 } }")
    List<Driver> findDriversByCreated(LocalDate searchDate);

}
