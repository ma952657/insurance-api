package com.example.api.repositories;

import com.example.api.models.Driver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests to drivers repository.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class DriversRepositoryTest {

    @Autowired
    DriversRepository driversRepository;
    
    @Before
    public void setupBeforeEachTest() throws Exception {
        List<Driver> driverList = new ArrayList<>();
        Driver driver = new Driver();
        driver.setId("1");
        driver.setFirstName("test1");
        driver.setDate_of_birth("1992-06-20");
        driver.setCreatedOn(LocalDate.parse("2021-06-20"));
        driver.setLastName("test2");
        driverList.add(driver);

        Driver driver2 = new Driver();

        driver2.setId("2");
        driver2.setFirstName("test2");
        driver2.setDate_of_birth("1992-06-201");
        driver2.setCreatedOn(LocalDate.parse("2021-06-21"));
        driver2.setLastName("test3");
        driverList.add(driver2);

        driversRepository.saveAll(driverList);
    }

    @Test
    public void shouldFindAll() {
        List<Driver> driverListAll = driversRepository.findAll();
        assertThat(driverListAll).isNotEmpty();
        assertThat(driverListAll.size()).isEqualTo(2);
        Page<Driver> driverPage = driversRepository.findAll(PageRequest.of(0, 1));
        assertThat(driverPage.getContent()).isNotEmpty();
        assertThat(driverPage.getContent().size()).isEqualTo(1);
    }

    @Test
    public void shouldFindByDate() {
        List<Driver> drivers = driversRepository.findDriversByCreated(LocalDate.parse("2021-06-20"));
        assertThat(drivers.size()>0);
        assertThat(drivers.get(0).getId()).isEqualTo("2");
    }

}