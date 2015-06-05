/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.persistence.model;

import java.util.Collection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
//@MappedSuperclass
//@Table(name = "subtype")
//@XmlRootElement
@Document(collection = "SubTypes")
public class SubType {
    //private static final long serialVersionUID = 1L;
    @Id    
    //@Field(value = "SubTypeId")
    private String subTypeId;
    //@Basic(optional = false)
    @Field(value = "SubName")
    private String subName;
    //@ManyToMany(mappedBy = "subTypeCollection")
    @DBRef
    private Collection<Notification> notificationCollection;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "subTypeSubTypeId")
            //@DBRef
            //private Collection<Address> addressCollection;
    //@JoinColumn(name = "ParentType", referencedColumnName = "ParentTypeId")
    //@ManyToOne(optional = false)
    @DBRef
    private ParentType parentType;    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "subTypeId")
            //@DBRef
            //private Collection<Status> statusCollection;

    public SubType() {
    }

    public SubType(String subTypeId) {
        this.subTypeId = subTypeId;
    }

    public SubType(String subTypeId, String subName) {
        this.subTypeId = subTypeId;
        this.subName = subName;
    }

    public String getSubTypeId() {
        return subTypeId;
    }

    public void setSubTypeId(String subTypeId) {
        this.subTypeId = subTypeId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    //@XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    /*//@XmlTransient
    public Collection<Address> getAddressCollection() {
    return addressCollection;
    }
    
    public void setAddressCollection(Collection<Address> addressCollection) {
    this.addressCollection = addressCollection;
    }*/

    public ParentType getParentType() {
        return parentType;
    }

    public void setParentType(ParentType parentType) {
        this.parentType = parentType;
    }

    //@XmlTransient
    /*public Collection<Status> getStatusCollection() {
    return statusCollection;
    }
    
    public void setStatusCollection(Collection<Status> statusCollection) {
    this.statusCollection = statusCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subTypeId != null ? subTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubType)) {
            return false;
        }
        SubType other = (SubType) object;
        if ((this.subTypeId == null && other.subTypeId != null) || (this.subTypeId != null && !this.subTypeId.equals(other.subTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.model.SubType[ subTypeId=" + subTypeId + " ]";
    }
    
}
