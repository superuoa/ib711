/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.util;

import com.poona.utils.ExceptionUtil;
import com.yit.msg.req.InquiryMsg;
import com.yit.msg.reply.HeaderMsg;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author anuphame
 */
public class MRPC {
    
    private final Logger logger = Logger.getLogger(MRPC.class);
    private final Connection conn;
    private Random rand;
    
    public MRPC(Connection conn) {
        try {
            this.rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Exception random error![" + ExceptionUtil.getStackTrace(ex) + "]");
        }
        this.conn = conn;
    }
    
    public String exec777(String input) {
        String response = "";
        try {
            String prepare = "{call mrpc(?, ? ,? )}";
            try (CallableStatement cstmt = conn.prepareCall(prepare)) {
                cstmt.setObject(1, "777");
                cstmt.setString(2, input);
                cstmt.registerOutParameter(3, Types.VARCHAR);
                try (ResultSet rs = cstmt.executeQuery()) {
                    if (rs.next()) {
                        // logger.info((String) rs.getObject(1));
                        response = rs.getString(1);
                        logger.info("response:" + response);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Exception mrpc 777 error![" + ExceptionUtil.getStackTrace(e) + "]");
        } 
        return response;
    }
    
    public String getCif(String cardIDType, String cardIdNum) {
        try {
            
            HeaderMsg headerMsg = new HeaderMsg();
            headerMsg.setChannel("SEVEN");
            headerMsg.setServiceName("AcctInquiry");
            headerMsg.setTpf("BAG001");
            String header = headerMsg.gen2profile();
            // search cif
            String body = "|P|" + cardIDType + "|" + cardIdNum;
            String input = header + body;
            logger.info("input:" + input);
            return exec777(input);
            
        } catch (UnknownHostException e) {
            logger.error("Exception mrpc777 error![" + ExceptionUtil.getStackTrace(e) + "]");
        }
        return "";
    }
    
    public String getCif(InquiryMsg inq) {
        try {
            
            HeaderMsg headerMsg = new HeaderMsg();
            headerMsg.setChannel("SEVEN");
            headerMsg.setServiceName("AcctInquiry");
            headerMsg.setTpf("BAG001");
            String header = headerMsg.gen2profile();
            // search cif
            String body = "|" + inq.getTransactionId() + "|" + inq.getTransactionType() + "|" + inq.getAgent().getTerminalNo()
                    + "|" + inq.getAgent().getBranchNo() + "|" + inq.getAccountInfo().getAccountNo() + "|" + inq.getDepositor().getMobileNo()
                    + "|" + inq.getDepositor().getIdCard() + "|" + inq.getTranAmount().getAmount();
            String input = header + body;
            logger.info("input:" + input);
            return exec777(input);
            
        } catch (UnknownHostException e) {
            logger.error("Exception mrpc777 error![" + ExceptionUtil.getStackTrace(e) + "]");
        }
        return "";
    }
    
    public String getTxid(String txid) {
        try {
            HeaderMsg headerMsg = new HeaderMsg();
            headerMsg.setChannel("SEVEN");
            headerMsg.setServiceName("TxidInquiry");
            headerMsg.setTpf("BAG002");
            String header = headerMsg.gen2profile();
            // search cif
            String body = "|"+txid;
            String input = header + body;
            logger.info("input:" + input);
            return exec777(input);
            
        } catch (UnknownHostException e) {
            logger.error("Exception mrpc777 error![" + ExceptionUtil.getStackTrace(e) + "]");
        }
        return "";
    }
}
