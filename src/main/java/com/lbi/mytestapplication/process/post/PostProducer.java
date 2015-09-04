package com.lbi.mytestapplication.process.post;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.impl.DefaultProducerTemplate;

import com.lbi.mytestapplication.process.CamelManager;

public class PostProducer {
	Logger logger = Logger.getLogger(PostProducer.class.getName());
	
	@Inject
	CamelManager camelMgr;
	
    @Inject
    CdiCamelContext camelCtx;

	
	public void postMessage(String queue, String message) throws Exception{
		Endpoint endpoint = camelMgr.getCamelEndpoint(queue);
		DefaultProducerTemplate producer = new DefaultProducerTemplate(camelCtx);
		producer.start();
		logger.info("producer started - sending message to queue : " + queue + "=> camel endpoint = " + endpoint);
		producer.sendBody(endpoint, message);
		logger.info("message sent to : " + queue);
		producer.stop();
		//SedaEndpoint seda = (SedaEndpoint) endpoint;
		//logger.info("number of message in queue : " + seda.getExchanges().size());
	}
}
