package com.lbi.mytestapplication.process.post;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.process.PostManager;

public class PostProcessor implements Processor {
	
	Logger logger = Logger.getLogger(PostProcessor.class.getName());
	
	@Inject
	PostManager postmgr;
	
	@Override
	public void process(Exchange exch) throws Exception {
		Post p = new Post();
		logger.info("Exchange : " + exch);
		p.setQueue(exch.getFromEndpoint().getEndpointUri());
		p.setMessage((String)exch.getIn().getBody());
		logger.info("consuming from : " + exch.getFromEndpoint().getEndpointUri() +" => message : " + exch.getIn().getBody());
		postmgr.createPost(p);
	}
}
