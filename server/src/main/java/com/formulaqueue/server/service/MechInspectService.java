package com.formulaqueue.server.service;


import com.formulaqueue.server.dao.MechInspectNumber;
import com.formulaqueue.server.repository.MechInspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechInspectService {
    @Autowired
    private MechInspectRepository repository;

    public List<MechInspectNumber> getNumbers(){
        return repository.findAll();
    }
    public MechInspectNumber create(MechInspectNumber number){
        return repository.save(number);
    }
    public MechInspectNumber getNumberById(Long id){
        return repository.getReferenceById(id);
    }
    public void delete(MechInspectNumber number){
        repository.delete(number);
    }

}
