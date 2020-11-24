/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.reply;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anuphame
 */
@XmlRootElement
public class RespMsg {
    @XmlTransient
    private List<InquiryResp> items;
    
    public List<InquiryResp> getItems() {
        return items;
    }

    public void setItems(List<InquiryResp> items) {
        this.items = items;
    }
    
}
