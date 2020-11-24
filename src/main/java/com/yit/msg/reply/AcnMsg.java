/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.reply;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anuphame
 */
@XmlRootElement
public class AcnMsg {
    private String cifNo;
    private String acctNo;
    private String acctClass;
    private String productGroup;
    private String acctType;
    private String acctTitle;
    private String productSubType;
    private String facilityCd;
    private String acctStatus;
    private String ledgerBalAmt;
    private String availBalAmt;
    private String MasterAcc;
    private String outStdBal;

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getAcctClass() {
        return acctClass;
    }

    public void setAcctClass(String acctClass) {
        this.acctClass = acctClass;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getAcctTitle() {
        return acctTitle;
    }

    public void setAcctTitle(String acctTitle) {
        this.acctTitle = acctTitle;
    }

    public String getProductSubType() {
        return productSubType;
    }

    public void setProductSubType(String productSubType) {
        this.productSubType = productSubType;
    }

    public String getFacilityCd() {
        return facilityCd;
    }

    public void setFacilityCd(String facilityCd) {
        this.facilityCd = facilityCd;
    }

    public String getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }

    public String getLedgerBalAmt() {
        return ledgerBalAmt;
    }

    public void setLedgerBalAmt(String ledgerBalAmt) {
        this.ledgerBalAmt = ledgerBalAmt;
    }

    public String getAvailBalAmt() {
        return availBalAmt;
    }

    public void setAvailBalAmt(String availBalAmt) {
        this.availBalAmt = availBalAmt;
    }

    public String getMasterAcc() {
        return MasterAcc;
    }

    public void setMasterAcc(String MasterAcc) {
        this.MasterAcc = MasterAcc;
    }

    public String getOutStdBal() {
        return outStdBal;
    }

    public void setOutStdBal(String outStdBal) {
        this.outStdBal = outStdBal;
    }

}
