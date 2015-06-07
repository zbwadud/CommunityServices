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
//@Table(name = "parenttype")
//@XmlRootElement
@Document(collection ="ParentTypes")
public class ParentType {
    //private static final long serialVersionUID = 1L;
    @Id    
    private String parentTypeId;
    //@Basic(optional = false)
    @Field(value = "ParentName")
    private String parentName;
    //@Basic(optional = false)
    @Field(value = "Active")
    private boolean active;
    @DBRef//@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentType")
    private Collection<SubType> subTypeCollection;

    public ParentType() {
    }

    public ParentType(String parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public ParentType(String parentName, boolean active) {
        //this.parentTypeId = parentTypeId;
        this.parentName = parentName;
        this.active = active;
    }

    public String getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(String parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //@XmlTransient
    public Collection<SubType> getSubTypeCollection() {
        return subTypeCollection;
    }

    public void setSubTypeCollection(Collection<SubType> subTypeCollection) {
        this.subTypeCollection = subTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentTypeId != null ? parentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentType)) {
            return false;
        }
        ParentType other = (ParentType) object;
        if ((this.parentTypeId == null && other.parentTypeId != null) || (this.parentTypeId != null && !this.parentTypeId.equals(other.parentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.model.ParentType[ parentTypeId=" + parentTypeId + " ]";
    }
    
}
