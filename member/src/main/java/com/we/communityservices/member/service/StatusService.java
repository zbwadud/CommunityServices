/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.service;

import com.we.communityservices.member.model.Status;
import com.we.communityservices.member.repository.StatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Service
public class StatusService {
    
    @Autowired
    private StatusRepository statuses;
    
    public List <Status> findAll(){
        return statuses.findAll();
    }
    
    public void saveStatus(Status status){
        statuses.save(status);
    }
    
    public Status findByName(String name){
        return statuses.findByName(name);
    }
    
}
