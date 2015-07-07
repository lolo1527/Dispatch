package com.lbi.mytestapplication.process.post;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.process.PostManager;

public class PostProcessor implements Processor {
	
	@Inject
	PostManager postmgr;
	
	@Override
	public void process(Exchange exch) throws Exception {
		Post p = new Post();
		p.setQueue(exch.getFromEndpoint().getEndpointUri());
		p.setMessage((String)exch.getIn().getBody());
		postmgr.createPost(p);
	}
}
