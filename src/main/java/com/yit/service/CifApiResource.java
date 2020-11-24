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
import com.yit.msg.req.InquiryMsg;
import com.yit.msg.reply.CifMsg;
import com.yit.msg.reply.MsgStatus;
import com.yit.msg.reply.ReplyMsg;
import com.yit.msg.reply.InquiryResp;
import com.yit.msg.reply.RespMsg;
import com.yit.util.DBConnection;
import com.yit.util.MRPC;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author anuphame
 */
@Path("cif")
public class CifApiResource {

    private final Logger logger = Logger.getLogger(CifApiResource.class);
    private static Connection conn;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public RespMsg getCifByCitizend(InquiryMsg inq) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inq);
        logger.info("json input[" + jsonStr + "]");
        conn = DBConnection.getInstance().getConnection();
        MRPC mrpc = new MRPC(conn);
        logger.info("citizend:" + inq.getDepositor().getIdCard());
        String rtn4profile = mrpc.getCif("CI", inq.getDepositor().getIdCard());
        // String input =
        // "SearchCustList|20190924T16:31:23.163+0700|192.168.99.1|FBS-Anuphab|1|f2e5f7ee-b93d-4ff6-b2e8-b6d1e704c20e|TR1768933073|VirtualBranch|000||2|1|N|1|2|คุณ|นางเลิ้ง|เอทีเอ็ม|นางเลิ้ง
        // เอทีเอ็ม|1119865412338||0|";
        //ReplyMsg replyMsg = str2CifMsg(rtn4profile);
        //return replyMsg.getCif();
        InquiryResp inqResp = new InquiryResp();
        inqResp.setTransactionId(inq.getTransactionId());
        inqResp.setAccountInfo(inq.getAccountInfo());
        inqResp.setTranAmount(inq.getTranAmount());
        inqResp.setResultCode("100");
        inqResp.setResultMsg("Sucess");
//        InquiryResp inqResp2 = new InquiryResp();
//        inqResp2.setTransactionId(inq.getTransactionId());
//        inqResp2.setAccountInfo(inq.getAccountInfo());
//        inqResp2.setTranAmount(inq.getTranAmount());
//        inqResp2.setResultCode("100");
//        inqResp2.setResultMsg("Sucess");        
        List<InquiryResp> items = new ArrayList<>();
        items.add(inqResp);
//        items.add(inqResp2);
        logger.debug("size of item:" + items.size());
        RespMsg respMsg = new RespMsg();
        respMsg.setItems(items);
        return respMsg;
    }
    
    private RespMsg str2Resp(String input){
        try{
            String[] inputSplit = input.split("\\|");
            logger.debug("len:" + input.split("\\|").length);     
            String statusCode = inputSplit[14];
            String statusText = inputSplit[15];
            if (statusCode.equals("0")) {
                
            }
        }catch(Exception e){
            logger.error("Exception process respone error![" + ExceptionUtil.getStackTrace(e) +"]");
        }
        return null;
    }

    private ReplyMsg str2CifMsg(String input) {
//        logger.info("output:" + input);
        CifMsg cifMsg = new CifMsg();
        MsgStatus msgStatus = new MsgStatus();
        ReplyMsg replyMsg = new ReplyMsg();
        replyMsg.setCif(cifMsg);
        replyMsg.setStatus(msgStatus);
        try {
            String[] inputSplit = input.split("\\|");
            logger.debug("len:" + input.split("\\|").length);
            msgStatus.setStatusCode(inputSplit[8]);
            msgStatus.setStatusText(inputSplit[9]);

            if (msgStatus.getStatusCode().equals("000")) {
                msgStatus.setStatusText("success");
                cifMsg.setAcn(inputSplit[14]);
                cifMsg.setTitle(inputSplit[15]);
                cifMsg.setFirstName(inputSplit[16]);
                cifMsg.setLastName(inputSplit[17]);
                cifMsg.setMiddleName(inputSplit[18]);
                cifMsg.setCitizen(inputSplit[19]);
                cifMsg.setTaxid(inputSplit[20]);
                cifMsg.setPersonalFlag(inputSplit[21]);
            }
        } catch (Exception e) {
            msgStatus.setStatusCode("999");
            msgStatus.setStatusText("Error");
            logger.error("Exception reply msg error![" + ExceptionUtil.getStackTrace(e) + "]");
        }
        return replyMsg;
    }
}
