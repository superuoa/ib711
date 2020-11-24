/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.util;

import com.poona.utils.ExceptionUtil;
import com.yit.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;


/**
 *
 * @author anuphame
 */
public class DBConnection {

    private static final Logger logger = Logger.getLogger(DBConnection.class);
    
    private static DBConnection instance = null;
    private static Connection conn = null;
//    private static final String USER="1";
//    private static final String PWD="xxx";

    private DBConnection(){
        try{
            Class.forName("sanchez.jdbc.driver.ScDriver").newInstance();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException e){
            logger.error("Exception crate instance error![" + ExceptionUtil.getStackTrace(e) +"]");
        }
    }

//    private DBConnection() {
//        try {
////            Map properties = new HashMap();
////            properties.put("javax.persistence.jdbc.url", "protocoll=jdbc:sanchez/database=172.26.56.18:50500:SCA$IBS/locale=TH:th/timeOut=2/transType=MTM/rowPrefetch=30/signOnType=1/poolSize=888/fileEncoding=ISO-8859-11");
////            properties.put("javax.persistence.jdbc.driver", "sanchez.jdbc.driver.ScDriver");
////            properties.put("javax.persistence.jdbc.user", "1");
////            properties.put("javax.persistence.jdbc.password", "xxx");
//            emf = Persistence.createEntityManagerFactory("com.yit_baac-vb_jar_1.0-SNAPSHOTPU");
//            System.out.println("connection:" + emf);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static DBConnection getInstance() {
        if (conn == null) {
            instance = new DBConnection();
        }
        return instance;
    }


    public Connection getConnection(String username, String password) {
        logger.debug("get connection!");
        if(conn == null){
            try {
                conn = DriverManager.getConnection(Main.DBURL, username, password);
                logger.debug("connection :" + conn.toString());
            } catch (SQLException ex) {
                logger.error("Exception connection error![" + ExceptionUtil.getStackTrace(ex) +"]");
            }
        }
        return conn;
    }
    
    public Connection getConnection() {
        logger.debug("get connection!");
        logger.debug("host:" + Main.DBURL);
        logger.debug("user:" + Main.USER);
        logger.debug("passwd:" + Main.PASSWD);
        if(conn == null){
            try {
                conn = DriverManager.getConnection(Main.DBURL, Main.USER, Main.PASSWD);
                logger.debug("connection :" + conn.toString());
            } catch (SQLException ex) {
                logger.error("Exception connection error![" + ExceptionUtil.getStackTrace(ex) +"]");
            }
        }
        return conn;
    }    
}
