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
@Table(name = "COUNTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
    , @NamedQuery(name = "Country.findById", query = "SELECT c FROM Country c WHERE c.id = :id")
    , @NamedQuery(name = "Country.findByCode3l", query = "SELECT c FROM Country c WHERE c.code3l = :code3l")
    , @NamedQuery(name = "Country.findByCode2l", query = "SELECT c FROM Country c WHERE c.code2l = :code2l")
    , @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name")
    , @NamedQuery(name = "Country.findByNameOfficial", query = "SELECT c FROM Country c WHERE c.nameOfficial = :nameOfficial")
    , @NamedQuery(name = "Country.findByFlag32", query = "SELECT c FROM Country c WHERE c.flag32 = :flag32")
    , @NamedQuery(name = "Country.findByFlag128", query = "SELECT c FROM Country c WHERE c.flag128 = :flag128")
    , @NamedQuery(name = "Country.findByLatitude", query = "SELECT c FROM Country c WHERE c.latitude = :latitude")
    , @NamedQuery(name = "Country.findByLongitude", query = "SELECT c FROM Country c WHERE c.longitude = :longitude")
    , @NamedQuery(name = "Country.findByZoom", query = "SELECT c FROM Country c WHERE c.zoom = :zoom")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODE3L")
    private String code3l;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CODE2L")
    private String code2l;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "NAME")
    private String name;
    @Size(max = 128)
    @Column(name = "NAME_OFFICIAL")
    private String nameOfficial;
    @Size(max = 255)
    @Column(name = "FLAG_32")
    private String flag32;
    @Size(max = 255)
    @Column(name = "FLAG_128")
    private String flag128;
    @Column(name = "LATITUDE")
    private Long latitude;
    @Column(name = "LONGITUDE")
    private Long longitude;
    @Column(name = "ZOOM")
    private Integer zoom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private Collection<CountryRegion> countryRegionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private Collection<CountryName> countryNameCollection;

    public Country() {
    }

    public Country(Integer id) {
        this.id = id;
    }

    public Country(Integer id, String code3l, String code2l, String name) {
        this.id = id;
        this.code3l = code3l;
        this.code2l = code2l;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode3l() {
        return code3l;
    }

    public void setCode3l(String code3l) {
        this.code3l = code3l;
    }

    public String getCode2l() {
        return code2l;
    }

    public void setCode2l(String code2l) {
        this.code2l = code2l;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfficial() {
        return nameOfficial;
    }

    public void setNameOfficial(String nameOfficial) {
        this.nameOfficial = nameOfficial;
    }

    public String getFlag32() {
        return flag32;
    }

    public void setFlag32(String flag32) {
        this.flag32 = flag32;
    }

    public String getFlag128() {
        return flag128;
    }

    public void setFlag128(String flag128) {
        this.flag128 = flag128;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    @XmlTransient
    public Collection<CountryRegion> getCountryRegionCollection() {
        return countryRegionCollection;
    }

    public void setCountryRegionCollection(Collection<CountryRegion> countryRegionCollection) {
        this.countryRegionCollection = countryRegionCollection;
    }

    @XmlTransient
    public Collection<CountryName> getCountryNameCollection() {
        return countryNameCollection;
    }

    public void setCountryNameCollection(Collection<CountryName> countryNameCollection) {
        this.countryNameCollection = countryNameCollection;
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
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neo.bankmgr.domain.Country[ id=" + id + " ]";
    }
    
}
