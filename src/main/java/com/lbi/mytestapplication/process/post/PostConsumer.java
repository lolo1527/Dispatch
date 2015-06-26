package com.lbi.mytestapplication.process.post;

import java.util.logging.Logger;


import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultRouteContext;
import org.apache.camel.impl.EventDrivenConsumerRoute;
import org.apache.camel.spi.RouteContext;

import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.process.CamelManager;
import com.lbi.mytestapplication.rest.ConnectorService;

public class PostConsumer {

	Logger logger = Logger.getLogger(ConnectorService.class.getName());

	EventDrivenConsumerRoute consumer;
	
    //@Inject
    //CdiCamelContext camelCtx;

	public PostConsumer(EndPoint endpoint, CamelManager camelMgr) {		
		//CamelManager camelMgr = new CamelManager();
		logger.info("camelCtx = " + camelMgr.getCamelContext());
		RouteContext routeCtx = new DefaultRouteContext(camelMgr.getCamelContext());
		logger.info("routeCtx = " + routeCtx);
		Processor processor = new PostProcessor();
		((PostProcessor)processor).setEndPoint(endpoint);
		logger.info("processor = " + processor);
		Endpoint ep = camelMgr.getCamelEndpoint(endpoint.getUrl());
		logger.info("endPoint = " + ep);
		consumer =  new EventDrivenConsumerRoute(routeCtx, ep, processor);
		logger.info("consumer = " + consumer);
	}
	
	
	public void start() throws Exception{
		consumer.start();
	}

	
	public void stop() throws Exception{
		consumer.stop();
	}

}
