package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.lbi.mytestapplication.domain.ConnectorDAO;
import com.lbi.mytestapplication.domain.entity.Connector;
import com.lbi.mytestapplication.process.post.PostConsumer;
import com.lbi.mytestapplication.process.post.PostProducer;

public class ConnectorManager {

	Logger logger = Logger.getLogger(ConnectorManager.class.getName());
	
	@Inject
	PostConsumer consumer;
	
	@Inject
	PostProducer producer;
	
	@Inject
	ConnectorDAO connectDao;

	@Inject
    private UserTransaction utx;

	public void createConnector(Connector connect){
		try {
			utx.begin();
			connectDao.createConnector(connect);
			if(connect.getConsumeQueue()!= null){
				consumer.createConsumer(connect.getConsumeQueue());
				consumer.start();
			}
			utx.commit();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			try {
				utx.rollback();
			} catch (Exception e1) {
				logger.log(Level.SEVERE, e.getMessage(),e);
			}
		}
	}

	/**
	 * start the connector passed in parameter in the camel context
	 * @param connect
	 */
	/* unused 
	public void startConnector(Connector connect){
		try {
			if(connect.getConsumeQueue()!= null){
				consumer.startConsumer(connect.getConsumeQueue());
				// TODO manage status
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}*/
	
	/**
	 * allow a connector to post a message in its defined produceQueue
	 * @param queue
	 * @param message
	 */
    public void postMessage(String queue, String message) {
        logger.info("post message to queue : " + queue + " - message : " + message);
        try {
			if( queue != null){
				producer.postMessage(queue, message);
			} else{
				logger.severe("producer queue is null => message can't be sent.");
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
    }


	/**
	 * return the connector list persisted in DB
	 * @return
	 */
    public List<Connector> getAllConnectors() {
		return connectDao.getAllConnectors();
	}


}
