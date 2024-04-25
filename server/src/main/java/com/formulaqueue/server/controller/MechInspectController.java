package com.formulaqueue.server.controller;


import com.formulaqueue.server.dao.MechInspectNumber;
import com.formulaqueue.server.service.MechInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.formulaqueue.server.utils.DataValidator.isDataNotValid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MechInspectController {
    @Autowired
    private MechInspectService service;

    @GetMapping("/mechanicalinspection/numbers")
    public ResponseEntity<CollectionModel<EntityModel<MechInspectNumber>>> getAllNumbers(){

        List<MechInspectNumber> numbers = service.getNumbers();
        List<EntityModel<MechInspectNumber>> numberModels = numbers.stream()
                .map(number -> EntityModel.of(number,
                        linkTo(methodOn(MechInspectController.class)
                                .getAllNumbers()).withSelfRel(),
                        linkTo(methodOn(MechInspectController.class)
                                .deleteNumber(number.getId())).withRel("delete")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(numberModels,
                linkTo(methodOn(MechInspectController.class).getAllNumbers()).withSelfRel()));
    }

    @PostMapping("/mechanicalinspection/numbers")
    public ResponseEntity<EntityModel<MechInspectNumber>> addNumber(@RequestParam String carName){
        try {
            if(isDataNotValid("mechanical inspection", carName)) {
                MechInspectNumber savedNumber = service.create(new MechInspectNumber(carName));
                EntityModel<MechInspectNumber> numberModel = EntityModel.of(savedNumber,
                        linkTo(methodOn(MechInspectController.class).getAllNumbers()).withRel("allNumbers"),
                        linkTo(methodOn(MechInspectController.class).deleteNumber(savedNumber.getId())).withRel("delete"));
                return ResponseEntity.created(linkTo(methodOn(MechInspectController.class)
                        .addNumber(carName)).toUri()).body(numberModel);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/mechanicalinspection/numbers")
    public ResponseEntity<Map<String, String>> deleteNumber(@RequestParam Long id){
        MechInspectNumber number = service.getNumberById(id);
        if(number == null){
            Map<String,String> json = Map.of("message", "Number not found");
            return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
        }
        service.delete(number);
        Map<String,String> json = Map.of("message", "Number deleted");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}