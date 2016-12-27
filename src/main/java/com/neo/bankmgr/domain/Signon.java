/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mac
 */
@Entity
@Table(name = "SIGNON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Signon.findAll", query = "SELECT s FROM Signon s")
    , @NamedQuery(name = "Signon.findByUserid", query = "SELECT s FROM Signon s WHERE s.userid = :userid")
    , @NamedQuery(name = "Signon.findByPassword", query = "SELECT s FROM Signon s WHERE s.password = :password")
    , @NamedQuery(name = "Signon.findByLangpref", query = "SELECT s FROM Signon s WHERE s.langpref = :langpref")
    , @NamedQuery(name = "Signon.findBySalt", query = "SELECT s FROM Signon s WHERE s.salt = :salt")
    , @NamedQuery(name = "Signon.findByCountry", query = "SELECT s FROM Signon s WHERE s.country = :country")
    , @NamedQuery(name = "Signon.findByAdmin", query = "SELECT s FROM Signon s WHERE s.admin = :admin")
    , @NamedQuery(name = "Signon.findByRoot", query = "SELECT s FROM Signon s WHERE s.root = :root")})
public class Signon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 5)
    @Column(name = "LANGPREF")
    private String langpref;
    @Size(max = 512)
    @Column(name = "SALT")
    private String salt;
    @Column(name = "COUNTRY")
    private Integer country;
    @Column(name = "ADMIN")
    private Boolean admin;
    @Column(name = "ROOT")
    private Boolean root;

    public Signon() {
    }

    public Signon(String userid) {
        this.userid = userid;
    }

    public Signon(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLangpref() {
        return langpref;
    }

    public void setLangpref(String langpref) {
        this.langpref = langpref;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Signon)) {
            return false;
        }
        Signon other = (Signon) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neo.bankmgr.domain.Signon[ userid=" + userid + " ]";
    }
    
}
