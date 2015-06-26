package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.cdi.CdiCamelContext;
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

	
	public void postMessage(EndPoint ep, String message) throws Exception{
		Endpoint endpoint = camelMgr.getCamelEndpoint(ep.getUrl());
		DefaultProducerTemplate producer = new DefaultProducerTemplate(camelCtx);
		producer.start();
		producer.sendBody(endpoint, message);
		producer.stop();
	}


	public List<Post> getAllPosts() {
		return postDAO.getAllPosts();
	}

	
}
