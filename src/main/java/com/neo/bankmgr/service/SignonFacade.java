/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.service;

import com.neo.bankmgr.domain.Signon;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mac
 */
@Stateless
public class SignonFacade extends AbstractFacade<Signon> {

    @PersistenceContext(unitName = "com.neo_BankMGR_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SignonFacade() {
        super(Signon.class);
    }

    public Signon passwordAuthentication(String userId, String password) {
        Signon s = null;
        try{
            s=(Signon) em.createQuery("SELECT s FROM Signon s WHERE s.password = :password AND s.userid = :userId")
                .setParameter("userId", userId)
                .setParameter("password", password)
                .getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    
    
}
