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
public class CifMsg  {


    private String acn;
    private String title;
    private String firstName;
    private String lastName;
    private String middleName;
    private String citizen;
    private String taxid;
    private String personalFlag;

    public CifMsg() {
    }

    public void setAcn(String acn) {
        this.acn = acn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setCitizen(String citizen) {
        this.citizen = citizen;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public void setPersonalFlag(String personalFlag) {
        this.personalFlag = personalFlag;
    }



    public String getAcn() {
        return acn;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCitizen() {
        return citizen;
    }

    public String getTaxid() {
        return taxid;
    }

    public String getPersonalFlag() {
        return personalFlag;
    }


}
