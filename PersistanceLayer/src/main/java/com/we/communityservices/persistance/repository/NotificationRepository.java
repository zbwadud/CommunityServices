/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.persistance.repository;

import com.we.communityservices.persistance.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
public interface NotificationRepository extends MongoRepository<Notification, String>{
    
}
