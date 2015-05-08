/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
/*@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
@NamedQuery(name = "Login.findByLoginId", query = "SELECT l FROM Login l WHERE l.loginPK.loginId = :loginId"),
@NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username"),
@NamedQuery(name = "Login.findByMemberId", query = "SELECT l FROM Login l WHERE l.loginPK.memberId = :memberId"),
@NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password")})*/

public class Login {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected LoginPK loginPK;
    
    @Column(name = "username")
    private String username;
    
    @Basic(optional = false)
    @Column(name = "password")
    
    private String password;
    
    /*@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)*/
    @DBRef
    private Members members;

    public Login() {
    }

    public Login(LoginPK loginPK) {
        this.loginPK = loginPK;
    }

    public Login(LoginPK loginPK, String password) {
        this.loginPK = loginPK;
        this.password = password;
    }

    public Login(int loginId, int memberId) {
        this.loginPK = new LoginPK(loginId, memberId);
    }

    public LoginPK getLoginPK() {
        return loginPK;
    }

    public void setLoginPK(LoginPK loginPK) {
        this.loginPK = loginPK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginPK != null ? loginPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.loginPK == null && other.loginPK != null) || (this.loginPK != null && !this.loginPK.equals(other.loginPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.member.model.Login[ loginPK=" + loginPK + " ]";
    }
    
}
