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
import com.yit.db.BankAgent;
import com.yit.msg.reply.MsgStatus;
import com.yit.msg.reply.ReplyMsgAgent;
import com.yit.util.DBConnection;
import com.yit.util.MRPC;
import java.io.IOException;
import java.sql.Connection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author anuphame
 */
@Path("txid")
public class TxidApiResource {

    private final Logger logger = Logger.getLogger(AcctApiResource.class);
    private static Connection conn = null;

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @param txid
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("{txid}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ReplyMsgAgent getTxid(@PathParam("txid") String txid) {
        try {
            ReplyMsgAgent reply = new ReplyMsgAgent();
            conn = DBConnection.getInstance().getConnection();
            MRPC mrpc = new MRPC(conn);
            logger.info("txid:" + txid);
            String rtn4profile = mrpc.getTxid(txid);
            str2Resp(rtn4profile, reply);
            return reply;

        } catch (Exception e) {
            logger.error("Exception get txid error![" + ExceptionUtil.getStackTrace(e) + "]");
        }
        return null;
    }

    private void str2Resp(String input, ReplyMsgAgent reply) {
        try {

            String[] inputSplit = input.split("\\|");
            logger.debug("len:" + input.split("\\|").length);
            String statusCode = inputSplit[13];
            String statusText = inputSplit[14];
            MsgStatus status = new MsgStatus();
            if (statusCode.equals("0")) {
                status.setStatusCode("000");
                status.setStatusText("Success");
                reply.setStatus(status);
                String output = inputSplit[15];
                logger.info("Response from profile:" + output);

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    BankAgent agent = objectMapper.readValue(output, BankAgent.class);
                    reply.setAgent(agent);
                } catch (JsonProcessingException e) {
                    logger.error("Exception json format error![" + ExceptionUtil.getStackTrace(e) + "]");
                }

                
            } else {
                status.setStatusCode(statusCode);
                status.setStatusText(statusText);
                reply.setStatus(status);
            }

        } catch (IOException e) {
            logger.error("Exception proces string from profile error![" + ExceptionUtil.getStackTrace(e) + "]");
        }
    }
}
