/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.service;

import com.neo.bankmgr.domain.Account;
import com.neo.bankmgr.domain.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mac
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "com.neo_BankMGR_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }

    public List<Message> getMessages(Account from, Account to) {
        return (List<Message>) 
                em.createQuery("SELECT m FROM Message m WHERE m.reader = :reader AND m.sender = :sender ")
                .setParameter("reader", to.getUserid())
                .setParameter("sender", from.getUserid())
                .getResultList();
    }
    public List<Message> getMessages(Account from, Account to, String subject) {
        return (List<Message>) 
                em.createQuery("SELECT m FROM Message m WHERE m.reader = :reader AND m.sender = :sender AND m.subject = :subject")
                .setParameter("reader", to.getUserid())
                .setParameter("sender", from.getUserid())
                .setParameter("subject", subject)
                .getResultList();
    }
    
    
}
