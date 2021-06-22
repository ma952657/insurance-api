package com.example.api.services;

import com.example.api.errors.ApiException;
import com.example.api.errors.ErrorType;
import com.example.api.models.Driver;
import com.example.api.repositories.DriversRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Drivers service implementation.
 */
@Service
public class DriversServiceImpl implements DriversService {

    private static final Logger LOGGER = LogManager.getLogger(DriversServiceImpl.class);

    @Autowired
    private DriversRepository driversRepository;

    @Override
    public Page<Driver> getAllDrivers(Pageable pageable) {
        LOGGER.debug("Getting all drivers for page size: " + pageable.getPageSize() +
                ", page number: " + pageable.getPageNumber()+ ", sort: " + pageable.getSort().toString());
        return driversRepository.findAll(pageable);
    }

    @Override
    public List<Driver> getDriversByDate(LocalDate driverCreationDate) {
        LOGGER.debug("Getting the drivers by Date: " + driverCreationDate);
        List<Driver> driverList = driversRepository.findDriversByCreated(driverCreationDate);
        if(driverList.size()<=0) {
           throw new ApiException(ErrorType.DRIVER_NOT_FOUND, driverCreationDate.toString());
        }
        return driverList;
    }

    @Override
    public Driver createDriver(Driver driver) {
        LOGGER.debug("Creating new driver with id: " + driver.getId());
        return driversRepository.save(driver);
    }

}
