package com.lbi.mytestapplication.domain;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lbi.mytestapplication.domain.entity.Application;
import com.lbi.mytestapplication.domain.entity.Queue;

public class ApplicationDAO {

	static Logger logger = Logger.getLogger(ApplicationDAO.class.getName());
	
    @PersistenceContext(name="primary")
    EntityManager em;

	public List<Application> getAllApplications() {
    	Query query = em.createQuery("SELECT distinct a FROM Application a LEFT JOIN FETCH a.queues");
        return (List<Application>) query.getResultList();
	}

	public void createApplication(Application app) {
		List<Queue> queues = app.getQueues();
		logger.info("Queue list size = " + queues.size());
        em.persist(app);
        for(Queue q : queues){
        	em.persist(q);
        }
	}

}
