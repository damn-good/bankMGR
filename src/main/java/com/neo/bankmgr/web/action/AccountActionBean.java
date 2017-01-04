/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.bankmgr.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mac
 */
@SessionScope
public class AccountActionBean extends AbstractActionBean {

    private static final String ACCOUNT_PAGE = "/WEB-INF/jsp/account.jsp";

    @DefaultHandler
    public Resolution signonForm() {
        return new ForwardResolution(ACCOUNT_PAGE);
    }


}
