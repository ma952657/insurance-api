package com.example.api.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;

/**
 * Base document.
 */
public abstract class BaseDocument {

    @Id
    private String id;

    private LocalDate createdOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "BaseDocument{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
