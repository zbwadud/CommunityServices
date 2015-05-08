/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.model;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */

/*@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
@NamedQuery(name = "Address.findByAddressId", query = "SELECT a FROM Address a WHERE a.addressId = :addressId"),
@NamedQuery(name = "Address.findByAddressline1", query = "SELECT a FROM Address a WHERE a.addressline1 = :addressline1"),
@NamedQuery(name = "Address.findByAddressline2", query = "SELECT a FROM Address a WHERE a.addressline2 = :addressline2")})*/

@Document
public class Address {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id")    
    private Integer addressId;
    
    @Column(name = "addressline1")
    private String addressline1;
    
    @Column(name = "addressline2")
    private String addressline2;
    
    /*@JoinTable(name = "address_has_type", joinColumns = {
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")}, inverseJoinColumns = {
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")})
    @ManyToMany*/
    @DBRef
    private Collection<Type> typeCollection;
    
    /*@JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)*/
    @DBRef
    private Members member;

    public Address() {
    }

    public Address(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    @XmlTransient
    public Collection<Type> getTypeCollection() {
        return typeCollection;
    }

    public void setTypeCollection(Collection<Type> typeCollection) {
        this.typeCollection = typeCollection;
    }

    public Members getMember() {
        return member;
    }

    public void setMemberId(Members member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.member.model.Address[ addressId=" + addressId + " ]";
    }
    
}
