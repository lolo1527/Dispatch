package com.lbi.mytestapplication.process;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.seda.SedaEndpoint;
import org.apache.camel.model.RouteDefinition;

import com.lbi.mytestapplication.domain.entity.Route;

public class CamelManager {
	
	Logger logger = Logger.getLogger(CamelManager.class.getName());

    @Inject
    CdiCamelContext camelCtx;
    
    //@Inject
    //RouteBuilder myRouteBuilder;

//    @PostConstruct
    public void init() throws Exception {
            logger.info(">> Create CamelContext");
            // Start Camel Context
            camelCtx.start();
            logger.info(">> CamelContext started");
    }

	
 //   @PreDestroy
    public void stop() throws Exception {
       camelCtx.stop();
    }
    
    public Endpoint addSEDAEndpoint(String url){
    	SedaEndpoint se = camelCtx.getEndpoint(url, SedaEndpoint.class);
        logger.info(">> Endpoint : " + url + " added to context.");
        return se;
    }

	public void addRoute(Route r) throws Exception {
        // Add Camel Route
		final Endpoint sourceEp = getCamelEndpoint(r.getSource().getUrl());
		final Endpoint destinationEp = getCamelEndpoint(r.getDestination().getUrl());
		final String routeId = r.getRouteId();
        RouteBuilder builder = new RouteBuilder() {
            public void configure() {
                errorHandler(deadLetterChannel("mock:error"));
                RouteDefinition def = from(sourceEp).to(destinationEp);
                def.routeId(routeId);
            }
        };
        camelCtx.addRoutes(builder);
        // stop route at creation
        camelCtx.stopRoute(routeId);
	}

	public org.apache.camel.Route getRoute(Route r){
		return camelCtx.getRoute(r.getRouteId());
	}
	
	public Collection<Endpoint> getCamelEndpoints() {
		return camelCtx.getEndpoints();
	}

	public Endpoint getCamelEndpoint(String url) {
		Map<String, Endpoint> map = camelCtx.getEndpointMap();
		for(String s : map.keySet()){
			logger.info("Map element : Key = " + s + " - value = " + map.get(s));
		}
		return (Endpoint)map.get(url);
	}


	public CamelContext getCamelContext() {
		return camelCtx;
	}


	public void startRoute(Route r) throws Exception {
		camelCtx.startRoute(r.getRouteId());
	}

}
