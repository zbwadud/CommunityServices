/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.we.communityservices.member.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Zaid Wadud @ NZQA 2015
 */
@Embeddable
public class LoginPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "login_id")
    private int loginId;
    @Basic(optional = false)
    @Column(name = "member_id")
    private int memberId;

    public LoginPK() {
    }

    public LoginPK(int loginId, int memberId) {
        this.loginId = loginId;
        this.memberId = memberId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) loginId;
        hash += (int) memberId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginPK)) {
            return false;
        }
        LoginPK other = (LoginPK) object;
        if (this.loginId != other.loginId) {
            return false;
        }
        if (this.memberId != other.memberId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.we.communityservices.member.model.LoginPK[ loginId=" + loginId + ", memberId=" + memberId + " ]";
    }
    
}
