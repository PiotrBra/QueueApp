package com.formulaqueue.server.service;

import com.formulaqueue.server.dao.BatteryInspectNumber;
import com.formulaqueue.server.repository.BatteryInspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryInspectService {
    @Autowired
    private BatteryInspectRepository repository;

    public List<BatteryInspectNumber> getNumbers(){
        return repository.findAll();
    }
    public BatteryInspectNumber create(BatteryInspectNumber number){
        return repository.save(number);
    }
    public BatteryInspectNumber getNumberById(Long id){
        return repository.getReferenceById(id);
    }
    public void delete(BatteryInspectNumber number){
        repository.delete(number);
    }
}
