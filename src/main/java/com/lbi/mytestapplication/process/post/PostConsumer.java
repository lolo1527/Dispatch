package com.lbi.mytestapplication.process.post;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.component.seda.SedaConsumer;
import org.apache.camel.component.seda.SedaEndpoint;

import com.lbi.mytestapplication.process.CamelManager;

public class PostConsumer {

	Logger logger = Logger.getLogger(PostConsumer.class.getName());
	SedaConsumer consumer =  null;

	@Inject
	CamelManager camelMgr;

	@Inject
	Processor processor;
	
/*	public void createConsumer(EndPoint endpoint){
		Endpoint ep = camelMgr.getCamelEndpoint(endpoint.getUrl());
		logger.info("endPoint = " + ep);
		consumer =  new SedaConsumer((SedaEndpoint) ep, processor);
	}
*/

	public void createConsumer(String queue) throws Exception{
		Endpoint ep = camelMgr.getCamelEndpoint(queue);
		logger.info("endPoint = " + ep);
		consumer =  new SedaConsumer((SedaEndpoint) ep, processor);
		consumer.start();
	}

	public void start() throws Exception{
		consumer.start();
	}

	public void startConsumer(String queue) throws Exception{
		Endpoint ep = camelMgr.getCamelEndpoint(queue);
		logger.info("endPoint = " + ep);
		consumer =  ((SedaEndpoint) ep).getConsumers().iterator().next();
		consumer.start();
	}
	
	public void stop() throws Exception{
		consumer.stop();
	}

}
