/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.persistence.repository;

import com.we.communityservices.persistence.model.ParentType;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */

public interface ParentTypeRepository extends MongoRepository<ParentType, String>{
    
    @Override
    public List<ParentType> findAll();   
      
    
    @Override
    public void deleteAll();
}
