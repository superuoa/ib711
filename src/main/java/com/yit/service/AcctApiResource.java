/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poona.utils.ExceptionUtil;
import com.yit.db.Acct;
import com.yit.db.BankAgent;
import com.yit.msg.req.InquiryMsg;
import com.yit.msg.reply.InquiryResp;
import com.yit.msg.reply.RespMsg;
import com.yit.msg.req.AccountInfo;
import com.yit.util.DBConnection;
import com.yit.util.MRPC;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author anuphame
 */
@Path("acct")
public class AcctApiResource {

    private final Logger logger = Logger.getLogger(AcctApiResource.class);
    private static Connection conn = null;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public RespMsg getAcctList(InquiryMsg inq) {
        RespMsg respMsg = new RespMsg();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inq);
            logger.info("json input[" + jsonStr + "]");
        } catch (JsonProcessingException e) {
            logger.error("Exception json format error![" + ExceptionUtil.getStackTrace(e) + "]");
            setResponse(inq, respMsg, "001", "Invalid Message format");
            return respMsg;
        }

        // check msg format from 7
        if (isInvalidMsg(inq, respMsg)) {
            return respMsg;
        }

        conn = DBConnection.getInstance().getConnection();
        MRPC mrpc = new MRPC(conn);
        logger.info("citizend:" + inq.getDepositor().getIdCard());
        String rtn4profile = mrpc.getCif(inq);

        str2Resp(rtn4profile, inq, respMsg);
        //return replyMsg.getCif();
        return respMsg;
    }

    private void str2Resp(String input, InquiryMsg inq, RespMsg respMsg) {
        String statusCode = null, statusText = null;
        try {
            String[] inputSplit = input.split("\\|");
            logger.debug("len:" + input.split("\\|").length);
            statusCode = inputSplit[13];
            statusText = inputSplit[14];

            if (statusCode.equals("0")) {
                statusCode = "100";
                statusText = "Success";
                String output = inputSplit[15];
                logger.info("Response from profile:" + output);
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    Acct acct = objectMapper.readValue(output, Acct.class);
                    AccountInfo acctInfo = inq.getAccountInfo();
                    acctInfo.setAccountName(acct.getTitle());
                    acctInfo.setAccountNo(inq.getAccountInfo().getAccountNo());
                    inq.setAccountInfo(acctInfo);
                } catch (JsonProcessingException e) {
                    logger.error("Exception json format error![" + ExceptionUtil.getStackTrace(e) + "]");
                }
                
            }
            setResponse(inq, respMsg, statusCode, statusText);
        } catch (IOException e) {
            logger.error("Exception process respone error![" + ExceptionUtil.getStackTrace(e) + "]");
            setResponse(inq, respMsg, statusCode, statusText);
        }

    }

    private void setResponse(InquiryMsg inq, RespMsg respMsg, String errCode, String error) {
        InquiryResp inqResp = new InquiryResp();
        inqResp.setTransactionId(inq.getTransactionId());
        inqResp.setAccountInfo(inq.getAccountInfo());
        inqResp.setTranAmount(inq.getTranAmount());
        inqResp.setResultCode(errCode);
        List<InquiryResp> items = new ArrayList<>();
        inqResp.setResultMsg(error);
        items.add(inqResp);
        logger.debug("size of item:" + items.size());
        respMsg.setItems(items);
    }

    private boolean isInvalidMsg(InquiryMsg inq, RespMsg respMsg) {
        InquiryResp inqResp = new InquiryResp();
        inqResp.setTransactionId(inq.getTransactionId());
        inqResp.setAccountInfo(inq.getAccountInfo());
        inqResp.setTranAmount(inq.getTranAmount());
        inqResp.setResultCode("001");
        List<InquiryResp> items = new ArrayList<>();

        if (inq.getAccountInfo() == null || inq.getAccountInfo().getAccountNo().equalsIgnoreCase("")) {
            inqResp.setResultMsg("account is emply");
            items.add(inqResp);
            logger.debug("size of item:" + items.size());
            respMsg.setItems(items);
            return true;
        }

        if (inq.getAgent() == null || inq.getAgent().getBranchNo().equalsIgnoreCase("")) {
            inqResp.setResultMsg("branch is emply");
            items.add(inqResp);
            logger.debug("size of item:" + items.size());
            respMsg.setItems(items);
            return true;
        }

        if (inq.getAgent() == null || inq.getAgent().getTerminalNo().equalsIgnoreCase("")) {
            inqResp.setResultMsg("terminal is emply");
            items.add(inqResp);
            logger.debug("size of item:" + items.size());
            respMsg.setItems(items);
            return true;
        }

        if (inq.getDepositor() == null || inq.getDepositor().getIdCard().equalsIgnoreCase("")) {
            inqResp.setResultMsg("CiitzenID is emply");
            items.add(inqResp);
            logger.debug("size of item:" + items.size());
            respMsg.setItems(items);
            return true;
        }

        if (inq.getDepositor() == null || inq.getDepositor().getMobileNo().equalsIgnoreCase("")) {
            inqResp.setResultMsg("Mobile is emply");
            items.add(inqResp);
            logger.debug("size of item:" + items.size());
            respMsg.setItems(items);
            return true;
        }

        if (inq.getTranAmount() == null || inq.getTranAmount().getAmount().equalsIgnoreCase("")) {
            inqResp.setResultMsg("Amount is emply");
            items.add(inqResp);
            logger.debug("size of item:" + items.size());
            respMsg.setItems(items);
            return true;
        }

        return false;
    }
}
