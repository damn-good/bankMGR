/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mac
 */
@Entity
@Table(name = "TRANSFERT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfert.findAll", query = "SELECT t FROM Transfert t")
    , @NamedQuery(name = "Transfert.findByTransfertdate", query = "SELECT t FROM Transfert t WHERE t.transfertdate = :transfertdate")
    , @NamedQuery(name = "Transfert.findByFromaccount", query = "SELECT t FROM Transfert t WHERE t.fromaccount = :fromaccount")
    , @NamedQuery(name = "Transfert.findByToaccount", query = "SELECT t FROM Transfert t WHERE t.toaccount = :toaccount")
    , @NamedQuery(name = "Transfert.findByCode", query = "SELECT t FROM Transfert t WHERE t.code = :code")
    , @NamedQuery(name = "Transfert.findByStatus", query = "SELECT t FROM Transfert t WHERE t.status = :status")
    , @NamedQuery(name = "Transfert.findByTransfertid", query = "SELECT t FROM Transfert t WHERE t.transfertid = :transfertid")})
public class Transfert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSFERTDATE")
    @Temporal(TemporalType.DATE)
    private Date transfertdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "FROMACCOUNT")
    private String fromaccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "TOACCOUNT")
    private String toaccount;
    @Size(max = 30)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TRANSFERTID")
    private Integer transfertid;

    public Transfert() {
    }

    public Transfert(Integer transfertid) {
        this.transfertid = transfertid;
    }

    public Transfert(Integer transfertid, Date transfertdate, String fromaccount, String toaccount, int status) {
        this.transfertid = transfertid;
        this.transfertdate = transfertdate;
        this.fromaccount = fromaccount;
        this.toaccount = toaccount;
        this.status = status;
    }

    public Date getTransfertdate() {
        return transfertdate;
    }

    public void setTransfertdate(Date transfertdate) {
        this.transfertdate = transfertdate;
    }

    public String getFromaccount() {
        return fromaccount;
    }

    public void setFromaccount(String fromaccount) {
        this.fromaccount = fromaccount;
    }

    public String getToaccount() {
        return toaccount;
    }

    public void setToaccount(String toaccount) {
        this.toaccount = toaccount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getTransfertid() {
        return transfertid;
    }

    public void setTransfertid(Integer transfertid) {
        this.transfertid = transfertid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transfertid != null ? transfertid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfert)) {
            return false;
        }
        Transfert other = (Transfert) object;
        if ((this.transfertid == null && other.transfertid != null) || (this.transfertid != null && !this.transfertid.equals(other.transfertid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neo.bankmgr.domain.Transfert[ transfertid=" + transfertid + " ]";
    }
    
}
