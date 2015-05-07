/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Entity
@Table(name = "members")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Members.findAll", query = "SELECT m FROM Members m"),
    @NamedQuery(name = "Members.findByMemberId", query = "SELECT m FROM Members m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "Members.findByFirstname", query = "SELECT m FROM Members m WHERE m.firstname = :firstname"),
    @NamedQuery(name = "Members.findByLastname", query = "SELECT m FROM Members m WHERE m.lastname = :lastname"),
    @NamedQuery(name = "Members.findByEmail", query = "SELECT m FROM Members m WHERE m.email = :email"),
    @NamedQuery(name = "Members.findByCellnumber", query = "SELECT m FROM Members m WHERE m.cellnumber = :cellnumber"),
    @NamedQuery(name = "Members.findByRegister", query = "SELECT m FROM Members m WHERE m.register = :register")})
public class Members implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "member_id")
    private Integer memberId;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "cellnumber")
    private String cellnumber;
    @Basic(optional = false)
    @Column(name = "register")
    @Temporal(TemporalType.DATE)
    private Date register;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Address> addressCollection;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Status statusId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "members")
    private Collection<Login> loginCollection;

    public Members() {
    }

    public Members(Integer memberId) {
        this.memberId = memberId;
    }

    public Members(Integer memberId, String firstname, String lastname, String email, Date register) {
        this.memberId = memberId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.register = register;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(String cellnumber) {
        this.cellnumber = cellnumber;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    @XmlTransient
    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    @XmlTransient
    public Collection<Login> getLoginCollection() {
        return loginCollection;
    }

    public void setLoginCollection(Collection<Login> loginCollection) {
        this.loginCollection = loginCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Members)) {
            return false;
        }
        Members other = (Members) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.member.model.Members[ memberId=" + memberId + " ]";
    }
    
}
