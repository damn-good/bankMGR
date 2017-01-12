/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mac
 */
@Entity
@Table(name = "REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
    , @NamedQuery(name = "Region.findById", query = "SELECT r FROM Region r WHERE r.id = :id")
    , @NamedQuery(name = "Region.findByName", query = "SELECT r FROM Region r WHERE r.name = :name")
    , @NamedQuery(name = "Region.findByIsUnep", query = "SELECT r FROM Region r WHERE r.isUnep = :isUnep")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 32)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_UNEP")
    private int isUnep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<CountryRegion> countryRegionCollection;

    public Region() {
    }

    public Region(Integer id) {
        this.id = id;
    }

    public Region(Integer id, int isUnep) {
        this.id = id;
        this.isUnep = isUnep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsUnep() {
        return isUnep;
    }

    public void setIsUnep(int isUnep) {
        this.isUnep = isUnep;
    }

    @XmlTransient
    public Collection<CountryRegion> getCountryRegionCollection() {
        return countryRegionCollection;
    }

    public void setCountryRegionCollection(Collection<CountryRegion> countryRegionCollection) {
        this.countryRegionCollection = countryRegionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neo.bankmgr.domain.Region[ id=" + id + " ]";
    }
    
}
