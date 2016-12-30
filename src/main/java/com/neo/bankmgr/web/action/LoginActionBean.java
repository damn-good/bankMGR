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
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mac
 */
@SessionScope
public class LoginActionBean extends AbstractActionBean {
    @EJB
    SignonFacade signonFacade;
    @EJB
    AccountFacade accountFacade;
    

    private static final String SIGNUP_PAGE = "/WEB-INF/jsp/.jsp";
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    private static final String ACCOUNT_PAGE = "/WEB-INF/jsp/account.jsp";
    
    private Account account = new Account();
    private String username, password;
    private boolean authenticated;
    
    

    @DefaultHandler
    public Resolution signonForm() {
        return new ForwardResolution(LOGIN_PAGE);
    }

    public Resolution newAccountForm() {
        return new ForwardResolution(SIGNUP_PAGE);
    }

    public Resolution newAccount() {
        authenticated = true;
        return new RedirectResolution("");
    }

    public Resolution editAccountForm() {
        return new ForwardResolution("");
    }

    public Resolution editAccount() {
        return new RedirectResolution("");
    }
    @HandlesEvent("login")
    public Resolution signon() {

        Signon s = signonFacade.passwordAuthentication(username, password);
        if (s == null) {
            String value = "Invalid username or password.  Signon failed.";
            setMessage(value);
            clear();
            return new ForwardResolution(LOGIN_PAGE);
        } else {
            account= accountFacade.find(s);
            authenticated = true;
            HttpSession session = context.getRequest().getSession();
            // this bean is already registered as /actions/Account.action
            session.setAttribute("accountBean", this);
            return new RedirectResolution(ACCOUNT_PAGE);
        }
    }

    public Resolution signoff() {
        context.getRequest().getSession().invalidate();
        clear();
        return new RedirectResolution("");
    }

    public boolean isAuthenticated() {
        return authenticated && account != null && account.getUserid()!= null;
    }

    public void clear() {
        account = new Account();
        authenticated = false;
    }


}
