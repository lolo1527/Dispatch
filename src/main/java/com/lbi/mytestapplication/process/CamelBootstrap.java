package com.lbi.mytestapplication.process;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.seda.SedaEndpoint;

@Singleton
@Startup
public class CamelBootstrap {
	
	Logger logger = Logger.getLogger(CamelBootstrap.class.getName());

    @Inject
    CdiCamelContext camelCtx;

    //@Inject
    //RouteBuilder myRouteBuilder;

    @PostConstruct
    public void init() throws Exception {
            logger.info(">> Create CamelContext");
            // Start Camel Context
            camelCtx.start();
            logger.info(">> CamelContext started");
    }

    @PreDestroy
    public void stop() throws Exception {
       camelCtx.stop();
    }
    
    public Endpoint addSEDAEndpoint(String name){
    	SedaEndpoint se = camelCtx.getEndpoint("seda:" + name, SedaEndpoint.class);
        logger.info(">> Endpoint added to context.");
        return se;
    }

	public void addRoute(String source, String destination) throws Exception {
        // Add Camel Route
		final Endpoint sourceEp = getCamelEndpoint(source);
		final Endpoint destinationEp = getCamelEndpoint(destination);
        RouteBuilder builder = new RouteBuilder() {
            public void configure() {
                errorHandler(deadLetterChannel("mock:error"));
                from(sourceEp).to(destinationEp).stop();
            }
        };
        camelCtx.addRoutes(builder);
	}

	public Collection<Endpoint> getCamelEndpoints() {
		return camelCtx.getEndpoints();
	}

	public Endpoint getCamelEndpoint(String name) {
		Map<String, Endpoint> map = camelCtx.getEndpointMap();
		for(String s : map.keySet()){
			logger.info("Map element : Key = " + s + " - value = " + map.get(s));
		}
		return (Endpoint)map.get(name);
		
	}

}
