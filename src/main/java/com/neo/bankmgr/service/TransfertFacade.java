/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.service;

import com.neo.bankmgr.domain.Account;
import com.neo.bankmgr.domain.Transfert;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mac
 */
@Stateless
public class TransfertFacade extends AbstractFacade<Transfert> {

    @PersistenceContext(unitName = "com.neo_BankMGR_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransfertFacade() {
        super(Transfert.class);
    }
    
    public List<Transfert> findByAccount(Account account){
        return (List<Transfert>) em.createQuery("SELECT t FROM Transfert t WHERE t.fromaccount = :acountId OR t.toaccount = :accountId")
                .setParameter("acountId", account.getAccountid())
                .getSingleResult();
    }
    
}
