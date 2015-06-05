/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.persistence.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
//@MappedSuperclass
//@Table(name = "notification")
@XmlRootElement
public class Notification implements Serializable {/*
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NotificationId")
    private Integer notificationId;
    @Basic(optional = false)
    @Column(name = "Message")
    private String message;
    @JoinTable(name = "notification_has_type", joinColumns = {
    @JoinColumn(name = "notification_id", referencedColumnName = "NotificationId")}, inverseJoinColumns = {
    @JoinColumn(name = "type_id", referencedColumnName = "SubTypeId")})
    @ManyToMany
    private Collection<SubType> subTypeCollection;
    @JoinColumn(name = "StatusId", referencedColumnName = "Statusid")
    @ManyToOne(optional = false)
    private Status statusId;
    
    public Notification() {
    }
    
    public Notification(Integer notificationId) {
    this.notificationId = notificationId;
    }
    
    public Notification(Integer notificationId, String message) {
    this.notificationId = notificationId;
    this.message = message;
    }
    
    public Integer getNotificationId() {
    return notificationId;
    }
    
    public void setNotificationId(Integer notificationId) {
    this.notificationId = notificationId;
    }
    
    public String getMessage() {
    return message;
    }
    
    public void setMessage(String message) {
    this.message = message;
    }
    
    @XmlTransient
    public Collection<SubType> getSubTypeCollection() {
    return subTypeCollection;
    }
    
    public void setSubTypeCollection(Collection<SubType> subTypeCollection) {
    this.subTypeCollection = subTypeCollection;
    }
    
    public Status getStatusId() {
    return statusId;
    }
    
    public void setStatusId(Status statusId) {
    this.statusId = statusId;
    }
    
    @Override
    public int hashCode() {
    int hash = 0;
    hash += (notificationId != null ? notificationId.hashCode() : 0);
    return hash;
    }
    
    @Override
    public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Notification)) {
    return false;
    }
    Notification other = (Notification) object;
    if ((this.notificationId == null && other.notificationId != null) || (this.notificationId != null && !this.notificationId.equals(other.notificationId))) {
    return false;
    }
    return true;
    }
    
    @Override
    public String toString() {
    return "com.we.communityservices.model.Notification[ notificationId=" + notificationId + " ]";
    }*/
    
}
