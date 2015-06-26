package com.lbi.mytestapplication.process.post;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.process.PostManager;

public class PostProcessor implements Processor {
	
	@Inject
	PostManager postmgr;
	
	EndPoint endPoint = null; 


	@Override
	public void process(Exchange exch) throws Exception {
		Post p = new Post();
		p.setEndpoint(endPoint);
		p.setMessage((String)exch.getIn().getBody());
		//postDAO = new PostDAO();
		postmgr.createPost(p);
		// TODO getEndpoint from exchange...
	}


	/**
	 * @return the endPoint
	 */
	public EndPoint getEndPoint() {
		return endPoint;
	}


	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(EndPoint endPoint) {
		this.endPoint = endPoint;
	}

}
