package com.lbi.mytestapplication.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lbi.mytestapplication.domain.entity.Connector;

public class ConnectorDAO {
	
    @PersistenceContext(name="primary")
    EntityManager em;

	
	public void createConnector(Connector c) {
        em.persist(c);
	}


	/*private EndPoint getEndpointByName(String application) {
		Query query = em.createQuery("SELECT e FROM EndPoint e WHERE e.application='" + application + "'");
		List<EndPoint> result = (List<EndPoint>)query.getResultList();
	    return result.get(0);
	}*/


	@SuppressWarnings("unchecked")
	public List<Connector> getAllConnectors() {
		Query query = em.createQuery("SELECT c FROM Connector c");
	    return (List<Connector>) query.getResultList();
	}


}
