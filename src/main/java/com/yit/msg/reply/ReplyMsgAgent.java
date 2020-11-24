/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.reply;

import com.yit.db.BankAgent;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anuphame
 */
@XmlRootElement
public class ReplyMsgAgent {
    private MsgStatus status;
    private BankAgent agent;

    public MsgStatus getStatus() {
        return status;
    }

    public void setStatus(MsgStatus status) {
        this.status = status;
    }

    public BankAgent getAgent() {
        return agent;
    }

    public void setAgent(BankAgent agent) {
        this.agent = agent;
    }
    
    
}
