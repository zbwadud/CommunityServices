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
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
/*@Entity
@Table(name = "type")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
@NamedQuery(name = "Type.findByTypeId", query = "SELECT t FROM Type t WHERE t.typeId = :typeId"),
@NamedQuery(name = "Type.findByName", query = "SELECT t FROM Type t WHERE t.name = :name")})*/

//@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@Column(name = "type_id")

@Document(collection = "types")
public class Type {
    //private static final long serialVersionUID = 1L;
    @Id    
    private String typeId;
    
    //@Basic(optional = false)
    @Field(value = "name")
    private String name;
    
    //@ManyToMany(mappedBy = "typeCollection")
    @DBRef
    private Collection<Notification> notificationCollection;
    
    //@ManyToMany(mappedBy = "typeCollection")
    @DBRef
    private Collection<Address> addressCollection;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public Type(String typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @XmlTransient
    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.member.model.Type[ typeId=" + typeId + " ]";
    }
    
}
