/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.msg.reply;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author anuphame
 */
public class HeaderMsg {

    private String tpf;
    private String serviceName;
    private String channel;

    public String getTpf() {
        return tpf;
    }

    public void setTpf(String tpf) {
        this.tpf = tpf;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String gen2profile() throws UnknownHostException {
        String currentDate = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss.SSSZ", Locale.US).format(new Date());
        UUID uuid = UUID.randomUUID();
        Random rand = new Random();
        int intRefId = rand.nextInt() & Integer.MAX_VALUE;
        InetAddress ip = InetAddress.getLocalHost();

        String timeOut = "1";
        String userId = "1";
        String maxOcc = "1";
        return this.tpf + "|" + this.serviceName + "|" + currentDate + "|" + timeOut + "|" + ip.getHostAddress()
                + "|" + ip.getHostName() + "|" + userId + "|" + maxOcc + "|" + uuid + "|" + intRefId + "|" + this.channel;
    }
}
