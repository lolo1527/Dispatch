package com.lbi.mytestapplication.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lbi.mytestapplication.domain.entity.Post;

public class PostDAO {
	
    @PersistenceContext(name="primary")
    EntityManager em;

	
	public void createPost(Post p) {
		//EndPoint ep = getEndpointByName(p.getEndpoint().getApplication());
		p.setQueue(p.getQueue());
		p.setMessage(p.getMessage());
        em.persist(p);
	}


	/*private EndPoint getEndpointByName(String application) {
		Query query = em.createQuery("SELECT e FROM EndPoint e WHERE e.application='" + application + "'");
		List<EndPoint> result = (List<EndPoint>)query.getResultList();
	    return result.get(0);
	}*/


	@SuppressWarnings("unchecked")
	public List<Post> getAllPosts() {
		Query query = em.createQuery("SELECT p FROM Post p");
	    return (List<Post>) query.getResultList();
	}


	public List<Post> getPostsByApp(String application) {
		Query query = em.createQuery("SELECT p FROM Post p where lower(p.queue) like '%" + application.toLowerCase() + "%'");
	    return (List<Post>) query.getResultList();
	}


}
