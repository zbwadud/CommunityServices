/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.persistance.service;

import com.we.communityservices.persistance.model.SubType;
import com.we.communityservices.persistance.repository.SubTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Service
public class SubTypeService {
    
    @Autowired
    private SubTypeRepository subTypes;
    
    public List<SubType> findAll(){
        return subTypes.findAll();
    }
    
    public void saveParentType(SubType subType){
        subTypes.save(subType);
    }
    
    public void deleteAll(){
        subTypes.deleteAll();
    }
    
}
