/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.model;

import java.util.Collection;
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
@Table(name = "status")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
@NamedQuery(name = "Status.findByStatusId", query = "SELECT s FROM Status s WHERE s.statusId = :statusId"),
@NamedQuery(name = "Status.findByName", query = "SELECT s FROM Status s WHERE s.name = :name")})*/
@Document(collection="statuses")
public class Status {
    
    //private static final long serialVersionUID = 1L;
    
    @Id
    private String statusId;
    
    @Field(value = "name")
    private String name;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    @DBRef
    private Collection<Notification> notificationCollection;
    
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    @DBRef
    private Collection<Members> membersCollection;
    

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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
    public Collection<Members> getMembersCollection() {
        return membersCollection;
    }

    public void setMembersCollection(Collection<Members> membersCollection) {
        this.membersCollection = membersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.member.model.Status[ statusId=" + statusId + " ]";
    }
    
}
