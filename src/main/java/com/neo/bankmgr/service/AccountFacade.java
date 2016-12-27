/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.service;

import com.fasterxml.uuid.Generators;
import com.neo.bankmgr.domain.Account;
import com.neo.bankmgr.domain.Signon;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author mac
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @EJB
    private SignonFacade signonFacade;

    @PersistenceContext(unitName = "com.neo_BankMGR_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    /**
     *
     * @param signOn the username password pair activated.
     * @return the corresponding account or null.
     */
    public Account find(Signon signOn) {
        return (Account) 
                em.createQuery("SELECT a FROM Account a WHERE a.userid = :userid")
                .setParameter("userid", signOn.getUserid())
                .getSingleResult();
    }

    /**
     *
     * @param begin the new account to be created no id needed.
     * @param password the new account ' s password
     * @return
     *
     */
    @Transactional
    public Signon signUp(Account begin, String password) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        begin.setUserid(RandomStringUtils.random(3, true, true)+uuid.clockSequence());
        begin.setIban(begin.getUserid());
        Signon s= new Signon(begin.getUserid(), password);

        signonFacade.create(s);
        this.create(begin);
        return s;
    }

    /**
     *
     * @param user the user whose penpals you want to get
     * @return a list of accounts linked to this user with at least one message 
     */
    public List<Account> getPenPals(Account user) {
        return (List<Account>) 
                em.createQuery("SELECT a FROM Account a, Message m WHERE a.userid = m.sender OR a.userid = m.reader AND a.userid !=:userid ")
                .setParameter("userid", user.getUserid())
                .getResultList();
    }
    
    

}
