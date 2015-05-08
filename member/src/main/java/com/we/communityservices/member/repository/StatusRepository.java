/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.repository;

import com.we.communityservices.member.model.Status;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
public interface StatusRepository extends MongoRepository<Status, String>{
    
    public Status findByName(String name);
    
    @Override
    public List<Status> findAll();
}
