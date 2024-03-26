package com.formulaqueue.server.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class HVLVInspectNumber {
    @Id
    @GeneratedValue
    private Long id;
    private String carName;

    private LocalTime time;

    public HVLVInspectNumber() {}
    public HVLVInspectNumber(String carName){
        this.carName = carName;
        this.time = LocalTime.now();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
