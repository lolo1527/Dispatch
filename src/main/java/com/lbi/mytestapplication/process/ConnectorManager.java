package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.lbi.mytestapplication.domain.entity.Connector;
import com.lbi.mytestapplication.process.post.PostConsumer;

public class ConnectorManager {

	Logger logger = Logger.getLogger(ConnectorManager.class.getName());
	
	@Inject
	CamelManager camelMgr;
	
	@Inject
	PostConsumer consumer;
	

	
	public void createConnector(Connector connect){
		logger.info("create consumer...");
		//PostConsumer consumer = new PostConsumer(connect.getEndpoint(), camelMgr);
		logger.info("consumer : " + consumer);
		try {
			consumer.createConsumer(connect.getEndpoint());
			consumer.start();
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}


	public List<Connector> getAllConnectors() {
		// TODO Auto-generated method stub
		return null;
	}


}
