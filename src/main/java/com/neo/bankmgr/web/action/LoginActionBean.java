/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.web.action;

import com.neo.bankmgr.domain.Country;
import com.neo.bankmgr.service.CountryFacade;
import com.neo.bankmgr.service.SignonFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.transaction.Transactional;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author mac
 */
@SessionScope
public class LoginActionBean extends AbstractActionBean {

    @EJB
    CountryFacade countryFacade;
    @EJB
    SignonFacade signonFacade;
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private static final String SIGNUP_PAGE = "/WEB-INF/jsp/signup.jsp";
    private List<Country> countries;
    private String username = "", password = "",message;
    

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
        if (signonFacade == null) {
            Logger.getGlobal().log(Level.INFO, "facade nulle");
        }
        setSignon(signonFacade.passwordAuthentication(getUsername(), getPassword()));
        if (getSignon() == null) {
            String value = "Invalid username or password.  Signon failed.";
            setMessage(value);
            clear();
            return new ForwardResolution(LOGIN_PAGE);
        } else {
            setAccount(accountFacade.find(getSignon()));
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
        int count = signonFacade.count();
        String random = RandomStringUtils.random(3, false, true);
        if (accountFacade.checkAccount(getAccount())) {
            getSignon().setUserid(count + 1 + "" + random);
            signonFacade.create(getSignon());
            getAccount().setUserid(getSignon().getUserid());
            getAccount().setBalance(0);
            getAccount().setStatus((short) 0);
            getAccount().setIban(RandomStringUtils.random(10, false, true));

            accountFacade.create(getAccount());
            authenticated = true;
            message="Successfully registered. You can now connect using this username: "+" and your password";
            return new RedirectResolution(AccountActionBean.class);
        }else{
            message="Email or phone already used on this platform please chose others.";
            return new RedirectResolution(LoginActionBean.class, "register-form");
        }

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

    /**
     * @return the countries
     */
    public List<Country> getCountries() {
        return countryFacade.findAll();
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
