/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.reply;

import com.yit.msg.req.AccountInfo;
import com.yit.msg.req.TranAmount;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anuphame
 */
@XmlRootElement
public class InquiryResp {
    private String transactionId;
    private AccountInfo accountInfo;
    private TranAmount tranAmount;
    private String resultCode;
    private String resultMsg;
    private String msgPrintSlip;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public TranAmount getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(TranAmount tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getMsgPrintSlip() {
        return msgPrintSlip;
    }

    public void setMsgPrintSlip(String msgPrintSlip) {
        this.msgPrintSlip = msgPrintSlip;
    }
    
}
