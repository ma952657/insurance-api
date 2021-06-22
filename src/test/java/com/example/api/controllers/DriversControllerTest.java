package com.example.api.controllers;

import com.example.api.models.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.api.services.DriversServiceImpl;
import com.example.api.services.SequenceGeneratorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests to drivers controller.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DriversController.class)
public class DriversControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DriversServiceImpl driversService;

    @MockBean
    private SequenceGeneratorService sequenceGeneratorService;

    private Page<Driver> driverPage;

    private static final String ENDPOINT_DRIVERS = "/v1";

    @Before
    public void setupBeforeEachTest() {
        List<Driver> driverList = new ArrayList<>();
        Driver driver = new Driver();
        driver.setId("1");
        driver.setFirstName("test1");
        driver.setDate_of_birth("2021-06-20");
        driver.setLastName("test2");
        driver.setCreatedOn(LocalDate.now());
        driverList.add(driver);

        driverPage = new PageImpl<>(driverList, PageRequest.of(0, 2), driverList.size());
    }

    @Test
    public void shouldReturnDriverPage_StatusOK() throws Exception {
        when(driversService.getAllDrivers(any())).thenReturn(driverPage);

        MvcResult mvcResult = mockMvc.perform(get(ENDPOINT_DRIVERS+"/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value("1"))
                .andReturn();

        assertThat(objectMapper.writeValueAsString(driverPage))
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldCreateNewDriver_StatusCREATED() throws Exception {
        Driver driver = driverPage.getContent().get(0);
        when(sequenceGeneratorService.generateSequence(any())).thenReturn(driver.getId());
        when(driversService.createDriver(any())).thenReturn(driver);

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT_DRIVERS+"/driver/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(objectMapper.writeValueAsString(driver))
                .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldNotCreateNewDriver_StatusBADREQUEST() throws Exception {
        Driver driver = driverPage.getContent().get(0);
        driver.setFirstName(null);

        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT_DRIVERS+"/driver/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()
                .contains("First Name is mandatory"));
    }

}