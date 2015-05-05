/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.model;

import org.springframework.data.annotation.Id;
/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */

public class TestModel {
    
    @Id
    private String id;

    private String firstName;
    private String lastName;

    public TestModel() {}

    public TestModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
    
}
