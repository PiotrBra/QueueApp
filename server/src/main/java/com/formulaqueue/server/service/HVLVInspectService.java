package com.formulaqueue.server.service;

import com.formulaqueue.server.dao.HVLVInspectNumber;
import com.formulaqueue.server.repository.HVLVInspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HVLVInspectService {
    @Autowired
    private HVLVInspectRepository repository;

    public List<HVLVInspectNumber> getNumbers(){
        return repository.findAll();
    }
    public HVLVInspectNumber create(HVLVInspectNumber number){
        return repository.save(number);
    }
    public HVLVInspectNumber getNumberById(Long id){
        return repository.getReferenceById(id);
    }
    public void delete(HVLVInspectNumber number){
        repository.delete(number);
    }
}
