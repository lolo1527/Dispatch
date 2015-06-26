package com.lbi.mytestapplication.process.post;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.component.seda.SedaConsumer;
import org.apache.camel.component.seda.SedaEndpoint;

import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.process.CamelManager;

public class PostConsumer {

	Logger logger = Logger.getLogger(PostConsumer.class.getName());
	SedaConsumer consumer =  null;

	@Inject
	CamelManager camelMgr;

	@Inject
	Processor processor;
	
	
	/*public PostConsumer(EndPoint endpoint, CamelManager camelMgr) {		
		//CamelManager camelMgr = new CamelManager();
		//logger.info("camelCtx = " + camelMgr.getCamelContext());
		//RouteContext routeCtx = new DefaultRouteContext(camelMgr.getCamelContext());
		//logger.info("routeCtx = " + routeCtx);
		//Processor processor = new PostProcessor();
		((PostProcessor)processor).setEndPoint(endpoint);
		logger.info("processor = " + processor);
		Endpoint ep = camelMgr.getCamelEndpoint(endpoint.getUrl());
		logger.info("endPoint = " + ep);
		//consumer =  new EventDrivenConsumerRoute(routeCtx, ep, processor);
		consumer =  new SedaConsumer((SedaEndpoint) ep, processor);
		logger.info("consumer = " + consumer);
	}*/
	
	
	public void createConsumer(EndPoint endpoint){
		((PostProcessor)processor).setEndPoint(endpoint);
		Endpoint ep = camelMgr.getCamelEndpoint(endpoint.getUrl());
		logger.info("endPoint = " + ep);
		consumer =  new SedaConsumer((SedaEndpoint) ep, processor);
	}


	public void start() throws Exception{
		consumer.start();
	}

	
	public void stop() throws Exception{
		consumer.stop();
	}

}
