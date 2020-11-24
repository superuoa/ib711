/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit;

import com.yit.util.AESCrypt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 *
 * @author anuphabmeekusol
 */
@Provider
public class CorsFilterResponse implements ContainerResponseFilter {

    private final Logger logger = Logger.getLogger(CorsFilterResponse.class);
//    private final String HEADERS = "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers";

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        logger.debug("start filter response!");
        //
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers", "*");
        //response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE,HEAD,OPTIONS");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        if (response.getEntityStream() != null) {
//            ByteArrayOutputStream bos = (ByteArrayOutputStream) response.getEntityStream();
//            String respStr = new String(bos.toByteArray());
//            logger.info("input data[" + respStr + "]");
//
//        }
    }

}
