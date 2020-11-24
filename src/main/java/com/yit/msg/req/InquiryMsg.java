/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.req;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anuphame
 */

@XmlRootElement
public class InquiryMsg {
    private String transactionId;
    private String transactionType;
    private Agent agent;
    private AccountInfo accountInfo;
    private Depositor depositor;
    private TranAmount tranAmount;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }



    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(Depositor depositor) {
        this.depositor = depositor;
    }

    public TranAmount getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(TranAmount tranAmount) {
        this.tranAmount = tranAmount;
    }
    
    
    
}
