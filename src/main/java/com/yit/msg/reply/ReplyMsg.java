/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.reply;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author anuphame
 */
@XmlRootElement
public class ReplyMsg {

    private MsgStatus status;
    private CifMsg cif;

    private Collection<AcnMsg> acn;

    public MsgStatus getStatus() {
        return status;
    }

    public void setStatus(MsgStatus status) {
        this.status = status;
    }

    public CifMsg getCif() {
        return cif;
    }

    public void setCif(CifMsg cif) {
        this.cif = cif;
    }

    @XmlTransient
    public Collection<AcnMsg> getAcn() {
        return acn;
    }

    public void setAcn(Collection<AcnMsg> acn) {
        this.acn = acn;
    }

}
