package com.formulaqueue.server.controller;

import com.formulaqueue.server.dao.HVLVInspectNumber;
import com.formulaqueue.server.dao.MechInspectNumber;
import com.formulaqueue.server.service.HVLVInspectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class HVLVInspectController {
    @Autowired
    private HVLVInspectService service;

    @GetMapping("/hvlvinspection/numbers")
    public List<HVLVInspectNumber> getAllNumbers(){
        return service.getNumbers();
    }

    @PostMapping("/hvlvinspection/numbers")
    public HVLVInspectNumber addNumber(@RequestParam String carName){
        return service.create(new HVLVInspectNumber(carName));
    }

    @DeleteMapping("/hvlvinspection/numbers/{id}")
    public ResponseEntity<Map<String, String>> deleteNumber(@PathVariable Long id){
        HVLVInspectNumber number = service.getNumberById(id);
        if(number == null){
            Map<String,String> json = Map.of("messege", "Number not found");
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        }
        service.delete(number);
        Map<String,String> json = Map.of("messege", "Number deleted");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
