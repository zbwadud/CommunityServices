/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.service;

import com.we.communityservices.member.model.Members;
import com.we.communityservices.member.repository.MemberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Service
public class MemberService {
    @Autowired
    private MemberRepository members;
    
    public Members findByFirstName(String firstName){
        return members.findByFirstName(firstName);
    }
    
    public List<Members> findAll(){    
        return members.findAll();
    }
    
    public void saveMember(Members member){
        members.save(member);
    }
    
}
