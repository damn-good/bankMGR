/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.service;

import com.neo.bankmgr.domain.CountryName;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mac
 */
@Stateless
public class CountryNameFacade extends AbstractFacade<CountryName> {

    @PersistenceContext(unitName = "com.neo_BankMGR_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountryNameFacade() {
        super(CountryName.class);
    }
    
    
    
}
