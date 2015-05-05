/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.repository;

import com.we.communityservices.member.model.TestModel;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
public interface TestRepository extends MongoRepository<TestModel, String>{
    
    public TestModel findByFirstName(String firstName);
    public List<TestModel> findByLastName(String lastName);

    
}
