/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import org.apache.log4j.Logger;

/**
 *
 * @author anuphame
 */
public class FilterRequest implements ContainerRequestFilter {

    private final Logger logger = Logger.getLogger(FilterRequest.class);

    @Context
    private javax.inject.Provider<Request> requestProvider;
    
//    public ContainerRequest filter(ContainerRequest request) throws IOException{
//        InputStream is = request.getEntityStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader reader = new BufferedReader(isr);
//        StringBuilder sb = new StringBuilder();
//        String str;
//        while ((str = reader.readLine()) != null) {
//            sb.append(str);
//        }
//        logger.info("input data[" + sb.toString() + "]");
//        
//        return request;
//    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final Request request = requestProvider.get();
        logger.info("Ipadd:" + request.getMethod() );
//        InputStream is = requestContext.getEntityStream();
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader reader = new BufferedReader(isr);
//        StringBuilder sb = new StringBuilder();
//        String str;
//        while ((str = reader.readLine()) != null) {
//            sb.append(str);
//        }
//        logger.info("input data[" + sb.toString() + "]");

        
//        InputStream stream = new ByteArrayInputStream(str.getBytes());
//        requestContext.setEntityStream(stream);
//        byte[] decryptedDate = AESCrypt.decrypt("M33@nuP00", dataEncrypted).getBytes();
//        InputStream stream = new ByteArrayInputStream();
//        request.setEntityStream(stream);

    }

  
}
