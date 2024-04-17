package com.formulaqueue.server.utils;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.formulaqueue.server.utils.JsonLoader.getAllowedCars;

public class DataValidator {

    public static boolean isDataNotValid(String type, String carNumber) {
        List<String> allowedCars = new ArrayList<>();
        switch (type) {
            case "mechanical inspection":
                allowedCars = getAllowedCars("server/src/main/resources/mechanical_inspection.json");
                break;
            case "battery inspection":
                allowedCars = getAllowedCars("server/src/main/resources/battery_inspection.json");
                break;
            case "hvlv inspection":
                allowedCars = getAllowedCars("server/src/main/resources/hvlv_inspection.json");
                break;
            default:
                break;
        }
        for (String car : allowedCars) {
            if (Objects.equals(car, carNumber)) {
                return true;
            }
        }
        return false;
    }


}
