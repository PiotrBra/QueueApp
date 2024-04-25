package com.formulaqueue.server.controller;

import com.formulaqueue.server.dao.HVLVInspectNumber;
import com.formulaqueue.server.dao.MechInspectNumber;
import com.formulaqueue.server.service.HVLVInspectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.formulaqueue.server.utils.DataValidator.isDataNotValid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class HVLVInspectController {
    @Autowired
    private HVLVInspectService service;

    @GetMapping("/hvlvinspection/numbers")
    public ResponseEntity<CollectionModel<EntityModel<HVLVInspectNumber>>> getAllNumbers(){
        List<HVLVInspectNumber> numbers = service.getNumbers();
        List<EntityModel<HVLVInspectNumber>> numberModels = numbers
                .stream()
                .map(number -> EntityModel.of(number,
                        linkTo(methodOn(HVLVInspectController.class)
                                .getAllNumbers()).withSelfRel(),
                        linkTo(methodOn(HVLVInspectController.class)
                                .deleteNumber(number.getId())).withRel("delete")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(numberModels,
                linkTo(methodOn(HVLVInspectController.class).getAllNumbers()).withSelfRel()));
    }


    @PostMapping("/hvlvinspection/numbers")
    public ResponseEntity<EntityModel<HVLVInspectNumber>> addNumber(@RequestParam String carName){
        try {
            if(isDataNotValid("hvlv inspection", carName)) {
                HVLVInspectNumber savedNumber = service.create(new HVLVInspectNumber(carName));
                EntityModel<HVLVInspectNumber> numberModel = EntityModel.of(savedNumber,
                        linkTo(methodOn(HVLVInspectController.class).getAllNumbers()).withRel("allNumbers"),
                        linkTo(methodOn(HVLVInspectController.class).deleteNumber(savedNumber.getId())).withRel("delete"));

                return ResponseEntity.created(linkTo(methodOn(HVLVInspectController.class)
                        .addNumber(carName)).toUri()).body(numberModel);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/hvlvinspection/numbers")
    public ResponseEntity<Map<String, String>> deleteNumber(@RequestParam Long id){
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
