package com.formulaqueue.server.controller;


import com.formulaqueue.server.dao.BatteryInspectNumber;
import com.formulaqueue.server.service.BatteryInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BatteryInspectController {
    @Autowired
    private BatteryInspectService service;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @GetMapping("/batteryinspection/numbers")
    public ResponseEntity<CollectionModel<EntityModel<BatteryInspectNumber>>> getAllNumbers(){
        List<BatteryInspectNumber> numbers = service.getNumbers();
        List<EntityModel<BatteryInspectNumber>> numberModels = numbers
                .stream()
                .map(number -> EntityModel.of(number,
                        linkTo(methodOn(BatteryInspectController.class)
                                .getAllNumbers()).withSelfRel(),
                        linkTo(methodOn(BatteryInspectController.class)
                                .deleteNumber(number.getId())).withRel("delete")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(numberModels,
                linkTo(methodOn(BatteryInspectController.class).getAllNumbers()).withSelfRel()));
    }


    @PostMapping("/batteryinspection/numbers")
    public ResponseEntity<EntityModel<BatteryInspectNumber>> addNumber(@RequestParam String carName){
        BatteryInspectNumber savedNumber = service.create(new BatteryInspectNumber(carName));
        EntityModel<BatteryInspectNumber> numberModel = EntityModel.of(savedNumber,
                linkTo(methodOn(BatteryInspectController.class).getAllNumbers()).withRel("allNumbers"),
                linkTo(methodOn(BatteryInspectController.class).deleteNumber(savedNumber.getId())).withRel("delete"));

        messagingTemplate.convertAndSend("/topic/newNumbers", numberModel);

        return ResponseEntity.created(linkTo(methodOn(BatteryInspectController.class)
                .getAllNumbers()).toUri()).body(numberModel);


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
