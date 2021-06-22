package com.example.api.services;

import com.example.api.models.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

/**
 * Drivers service interface.
 */
public interface DriversService {

   Page<Driver> getAllDrivers(Pageable pageable);

   List<Driver> getDriversByDate(LocalDate createdOn);

   Driver createDriver(Driver campaign);

}
