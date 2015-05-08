/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.service;

import com.we.communityservices.member.model.Address;
import com.we.communityservices.member.model.Members;
import com.we.communityservices.member.repository.AddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addresses;
    
    public List<Address> findByMember(Members memberId){
        return addresses.findByMember(memberId);
    }
    
    public void saveAddress(Address address){
        addresses.save(address);
    }
}
