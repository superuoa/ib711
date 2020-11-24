/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.req;

/**
 *
 * @author anuphame
 */
public class MsgSearch {
    private String customerType;
    private String inqType;
    private String citizId;
    private String custNo;
    private String custFname;
    private String custLname;

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getInqType() {
        return inqType;
    }

    public void setInqType(String inqType) {
        this.inqType = inqType;
    }

    public String getCitizId() {
        return citizId;
    }

    public void setCitizId(String citizId) {
        this.citizId = citizId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustFname() {
        return custFname;
    }

    public void setCustFname(String custFname) {
        this.custFname = custFname;
    }

    public String getCustLname() {
        return custLname;
    }

    public void setCustLname(String custLname) {
        this.custLname = custLname;
    }
    
    
}
