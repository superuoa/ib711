package com.yit;

import com.poona.utils.ChkArgs;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import javax.ws.rs.ext.ContextResolver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

/**
 * Main class.
 *
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static String BASE_URI;
    static Logger logger = Logger.getLogger(Main.class);
    public static String DBURL;
    public static String USER;
    public static String PASSWD;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.yit package
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        final ContextResolver jsonConfigResolver = moxyJsonConfig.resolver();

        final ResourceConfig rc = new ResourceConfig().packages("com.yit")
                .register(MoxyJsonFeature.class)
                .register(new CorsFilterResponse())
                //                .register(new FilterRequest())
                //                .register(JacksonFeature.class)
                .register(com.wordnik.swagger.jersey.listing.ApiListingResourceJSON.class)
                .register(com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider.class)
                .register(com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider.class)
                .register(jsonConfigResolver);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method. //sh 'mvn
     * org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ChkArgs chkargs = new ChkArgs(args);
        PropertyConfigurator.configure(chkargs.getFileConfig());
        Properties prop = chkargs.getProps();
        BASE_URI = prop.getProperty("BASE_URI");
        logger.info("URL endpoint:" + BASE_URI);
        DBURL = (String) prop.get("DBURL");
        USER = (String) prop.getProperty("USER");
        PASSWD = (String) prop.getProperty("PASSWD");
        logger.info("config database dburl[" + DBURL + "] user[" + USER + "] pwd[" + PASSWD + "]");
        final HttpServer server = startServer();
        logger.info(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        server.start();
//        System.in.read();
//        server.shutdown();
    }
}
