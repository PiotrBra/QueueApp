package com.formulaqueue.server.controller;


import com.formulaqueue.server.dao.BatteryInspectNumber;
import com.formulaqueue.server.service.BatteryInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BatteryInspectController {
    @Autowired
    private BatteryInspectService service;

    @GetMapping("/batteryinspection/numbers")
    public List<BatteryInspectNumber> getAllNumbers(){
        return service.getNumbers();
    }

    @PostMapping("/batteryinspection/numbers")
    public BatteryInspectNumber addNumber(@RequestParam String carName){
        return service.create(new BatteryInspectNumber(carName));
    }

    @DeleteMapping("/batteryinspection/numbers/{id}")
    public ResponseEntity<Map<String, String>> deleteNumber(@PathVariable Long id){
        BatteryInspectNumber number = service.getNumberById(id);
        if(number == null){
            Map<String,String> json = Map.of("messege", "Number not found");
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        }
        service.delete(number);
        Map<String,String> json = Map.of("messege", "Number deleted");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
