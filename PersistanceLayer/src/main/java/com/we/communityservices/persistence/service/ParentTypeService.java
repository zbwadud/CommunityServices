/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.persistence.service;

import com.we.communityservices.persistence.model.ParentType;
import com.we.communityservices.persistence.repository.ParentTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Service
public class ParentTypeService {
    
    @Autowired
    private ParentTypeRepository parentTypes;
    
    public List<ParentType> findAll(){
        return parentTypes.findAll();
    }
    
    public void saveParentType(ParentType parentType){
        parentTypes.save(parentType);
    }
    
    public void deleteAll(){
        parentTypes.deleteAll();
    }
    
}
