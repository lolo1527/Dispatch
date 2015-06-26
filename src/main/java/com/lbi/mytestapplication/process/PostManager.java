package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.apache.camel.Endpoint;
import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.seda.SedaEndpoint;
import org.apache.camel.impl.DefaultProducerTemplate;

import com.lbi.mytestapplication.domain.PostDAO;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Post;

public class PostManager {

	Logger logger = Logger.getLogger(EndPointManager.class.getName());
	
	@Inject
	CamelManager camelMgr;
	
    @Inject
    CdiCamelContext camelCtx;
    
    @Inject
    PostDAO postDAO;

	@Inject
    private UserTransaction utx;

	
	public void postMessage(EndPoint ep, String message) throws Exception{
		Endpoint endpoint = camelMgr.getCamelEndpoint(ep.getUrl());
		DefaultProducerTemplate producer = new DefaultProducerTemplate(camelCtx);
		producer.start();
		logger.info("producer started - sending message...");
		producer.sendBody(endpoint, message);
		logger.info("message sent to : " + ep);
		producer.stop();
		SedaEndpoint seda = (SedaEndpoint) endpoint;
		logger.info("number of message in queue : " + seda.getExchanges().size());
	}


	public List<Post> getAllPosts() {
		return postDAO.getAllPosts();
	}

	public void createPost(Post p){
		try {
			utx.begin();
			postDAO.createPost(p);
			utx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
			try {
				utx.rollback();
			} catch (Exception e1) {
				logger.log(Level.SEVERE, e.getMessage(),e);
			}
		}

	}
}
