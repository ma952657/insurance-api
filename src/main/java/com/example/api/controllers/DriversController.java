package com.example.api.controllers;

import com.example.api.models.Driver;
import com.example.api.services.DriversService;
import com.example.api.services.SequenceGeneratorService;
import com.example.api.constants.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.time.LocalDate;

/**
 * REST controller to campaigns.
 */
@RestController
@Api(value = "Drivers")
@RequestMapping("/v1")
public class DriversController {

    private static final Logger LOGGER = LogManager.getLogger(DriversController.class);

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private DriversService driversService;

    @ApiOperation(value = "Get all drivers")
    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<Driver> getAllDrivers(@RequestParam(value="page", required = false, defaultValue = ApiConstants.DRIVERS_PAGE_DEFAULT) int page,
                                      @RequestParam(value="size", required = false, defaultValue = ApiConstants.DRIVERS_SIZE_DEFAULT) int size) {
        LOGGER.info("Entering in rest endpoint to get all drivers");
        PageRequest pageRequest = PageRequest.of(page, size);
        return driversService.getAllDrivers(pageRequest);
    }

    @ApiOperation(value = "Get a drivers by date")
    @RequestMapping(value = "/drivers/byDate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Driver> getDriversByDate(@ApiParam(
            name =  "createdDate",
            type = "String",
            value = "Created Date",
            format = "YYYY-MM-DD",
            example = "2021-06-20",
            required = true) @RequestParam(value = "date") String createdDate) {

        LOGGER.info("Entering in rest endpoint to get a driver by date");
        return driversService.getDriversByDate(LocalDate.parse(createdDate));
    }



    @ApiOperation(value = "Create a new driver")
    @RequestMapping(value = "/driver/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@Valid @RequestBody Driver driver) {
        LOGGER.info("Entering in rest endpoint to create a new driver");
        driver.setId(sequenceGeneratorService.generateSequence(Driver.SEQUENCE_NAME));
        driver.setCreatedOn(LocalDate.now());
        return driversService.createDriver(driver);
    }


}