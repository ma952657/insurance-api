package com.example.api.models;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.example.api.constants.ApiConstants.DRIVER_SEQUENCE_NAME;

/**
 * Campaign model collection.
 */
@Document(collection = "campaigns")
public class Driver extends BaseDocument {

    @Transient
    public static final String SEQUENCE_NAME = DRIVER_SEQUENCE_NAME;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @NotBlank(message = "Dob is mandatory")
    private String date_of_birth;

    public Driver() {
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

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }
}
