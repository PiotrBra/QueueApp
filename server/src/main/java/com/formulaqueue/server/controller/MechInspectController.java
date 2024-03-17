package com.formulaqueue.server.controller;


import com.formulaqueue.server.dao.MechInspectNumber;
import com.formulaqueue.server.service.MechInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MechInspectController {
    @Autowired
    private MechInspectService service;

    @GetMapping("/mechanicalinspection/numbers")
    public List<MechInspectNumber> getAllNumbers(){
        return service.getNumbers();
    }

    @PostMapping("/mechanicalinspection/numbers")
    public MechInspectNumber addNumber(@RequestParam String carName){
        return service.create(new MechInspectNumber(carName));
    }

    @DeleteMapping("/mechanicalinspection/numbers/{id}")
    public ResponseEntity<Map<String, String>> deleteNumber(@PathVariable Long id){
        MechInspectNumber number = service.getNumberById(id);
        if(number == null){
            Map<String,String> json = Map.of("messege", "Number not found");
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        }
        service.delete(number);
        Map<String,String> json = Map.of("messege", "Number deleted");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}