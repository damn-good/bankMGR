/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.web.action;

import com.neo.bankmgr.domain.Account;
import com.neo.bankmgr.domain.Signon;
import com.neo.bankmgr.service.AccountFacade;
import com.neo.bankmgr.service.SignonFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author mac
 */
@SessionScope
public class LoginActionBean extends AbstractActionBean {

    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private static final String SIGNUP_PAGE = "/WEB-INF/jsp/signup.jsp";

    private String username = "", password = "";

    @DefaultHandler
    public Resolution signonForm() {
        return new ForwardResolution(LOGIN_PAGE);
    }

    public Resolution newAccountForm() {
        return new ForwardResolution(SIGNUP_PAGE);
    }

    public Resolution editAccountForm() {
        return new ForwardResolution("");
    }

    public Resolution editAccount() {
        return new RedirectResolution("");
    }

    @HandlesEvent("connect")
    public Resolution connect() {
//      Logger.getGlobal().log(Level.INFO, "username=" + getUsername() + "\n password= " + getPassword());
        if (signonFacade == null) {
            Logger.getGlobal().log(Level.INFO, "facade nulle");
        }
        signon = signonFacade.passwordAuthentication(getUsername(), getPassword());
        if (signon == null) {
            String value = "Invalid username or password.  Signon failed.";
            setMessage(value);
            clear();
            return new ForwardResolution(LOGIN_PAGE);
        } else {
            account = accountFacade.find(signon);
//            authenticated = true;
//            HttpSession session = context.getRequest().getSession();
//            // this bean is already registered as /actions/Account.action
//            session.setAttribute("accountBean", this);
            return new RedirectResolution(AccountActionBean.class);
        }
    }

    @HandlesEvent("register-form")
    public Resolution registerForm() {
        return new ForwardResolution(SIGNUP_PAGE);
    }

    @HandlesEvent("register")
    @Transactional
    public Resolution registerNewAccount() {
        authenticated = false;
        signon.setUserid(account.getFirstname().charAt(0)+signonFacade.count()+1+""+RandomStringUtils.random(3, 25, 0, false, false, null));
        signonFacade.create(signon);
        account.setUserid(signon.getUserid());
        accountFacade.create(account);
        return new RedirectResolution(AccountActionBean.class);
    }

    public Resolution signoff() {
        context.getRequest().getSession().invalidate();
        clear();
        return new RedirectResolution("");
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
