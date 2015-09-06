package com.lbi.mytestapplication.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.jms.JmsQueueEndpoint;
import org.apache.camel.component.seda.SedaEndpoint;
import org.apache.camel.model.RouteDefinition;

import com.lbi.mytestapplication.common.Constant;
import com.lbi.mytestapplication.domain.entity.Route;

public class CamelManager {
	
	Logger logger = Logger.getLogger(CamelManager.class.getName());

    @Inject
    CdiCamelContext camelCtx;
    
    public void init() throws Exception {
            logger.info(">> Create CamelContext");
            // Start Camel Context
            camelCtx.start();
            logger.info(">> CamelContext started");
    }

	
    public void stop() throws Exception {
       camelCtx.stop();
    }
    
    
    public void initActiveMQ(){
    	//ActiveMQComponent amqc = new ActiveMQComponent(camelCtx);
        logger.info(">> init ActiveMQ component");
		camelCtx.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
    }
    
    
    public List<Endpoint> addJMSEndpoints(String url) throws Exception{
    	ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();

    	String pQueue = url + "." + Constant.PRODUCE;
    	JmsQueueEndpoint produceQueue = new JmsQueueEndpoint(pQueue, pQueue);
    	produceQueue.setConnectionFactory(new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false"));
    	camelCtx.addEndpoint(pQueue, produceQueue);
        logger.info(">> Endpoint : " + pQueue + " added to context.");
        endpoints.add(produceQueue);
        
    	String cQueue = url + "." + Constant.CONSUME;
        JmsQueueEndpoint consumeQueue = new JmsQueueEndpoint(cQueue, cQueue);
    	consumeQueue.setConnectionFactory(new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false"));
    	camelCtx.addEndpoint(cQueue, consumeQueue);
        logger.info(">> Endpoint : " + cQueue + " added to context.");
        endpoints.add(consumeQueue);
        return endpoints;
    }
    
    public List<Endpoint> addSEDAEndpoints(String url){
    	ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();
    	SedaEndpoint produceQueue = camelCtx.getEndpoint(url + Constant.PRODUCE, SedaEndpoint.class);
        logger.info(">> Endpoint : " + url + " added to context.");
        endpoints.add(produceQueue);
    	SedaEndpoint consumeQueue = camelCtx.getEndpoint(url + Constant.CONSUME, SedaEndpoint.class);
        logger.info(">> Endpoint : " + url + " added to context.");
        endpoints.add(consumeQueue);
        return endpoints;
    }

	public void addRoute(Route r) throws Exception {
        // Add Camel Route
		final Endpoint sourceEp = getCamelEndpoint(r.getSource().getUrl() + "." + Constant.PRODUCE);
		logger.info(">> sourceEp : " + sourceEp + " retrieved from context : " + r.getSource().getUrl() + "." + Constant.PRODUCE);
		final Endpoint destinationEp = getCamelEndpoint(r.getDestination().getUrl() + "." + Constant.CONSUME);
		logger.info(">> destinationEp : " + destinationEp + " retrieved from context : " + r.getDestination().getUrl() + "." + Constant.CONSUME);
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
		logger.info("getCamelEndpoint - url : " + url);
		Map<String, Endpoint> map = camelCtx.getEndpointMap();
		/*for(String key : map.keySet()){
			logger.info("key : " + key + " => ep : " + map.get(key));
		}*/
		return (Endpoint)map.get(url);
	}

	public Endpoint getCamelProduceEndpoint(String url) {
		return getCamelEndpoint(url + "." + Constant.PRODUCE);
	}

	public Endpoint getCamelConsumeEndpoint(String url) {
		return getCamelEndpoint(url + "." + Constant.CONSUME);
	}

	
	public CamelContext getCamelContext() {
		return camelCtx;
	}


	public void startRoute(Route r) throws Exception {
		camelCtx.startRoute(r.getRouteId());
	}


	public void stopRoute(Route r) throws Exception {
		camelCtx.stopRoute(r.getRouteId());
	}


	public void pauseRoute(Route r) throws Exception {
		camelCtx.suspendRoute(r.getRouteId());
	}

}
