/**
 *    Copyright 2010-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.neo.bankmgr.web.action;

import com.neo.bankmgr.domain.Account;
import com.neo.bankmgr.domain.Signon;
import com.neo.bankmgr.service.AccountFacade;
import com.neo.bankmgr.service.MessageFacade;
import com.neo.bankmgr.service.SignonFacade;
import com.neo.bankmgr.service.TransfertFacade;
import java.io.Serializable;
import javax.ejb.EJB;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.action.SimpleMessage;

/**
 * @author Eduardo Macarron
 *
 */
@SessionScope
public abstract class AbstractActionBean implements ActionBean, Serializable {

    private static final long serialVersionUID = -1767714708233127983L;

    protected static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    protected transient ActionBeanContext context;

    @EJB
    SignonFacade signonFacade;
    @EJB
    AccountFacade accountFacade;
    @EJB
    MessageFacade messageFacade;
    @EJB
    TransfertFacade transfertFacade;

    protected boolean authenticated;
    protected Account account = new Account();
    protected Signon signon;

    protected void setMessage(String value) {
        context.getMessages().add(new SimpleMessage(value));
    }

    public boolean isAuthenticated() {
        return authenticated && account != null && account.getUserid() != null;
    }

    public void clear() {
        account = new Account();
        authenticated = false;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

}
