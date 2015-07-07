package com.lbi.mytestapplication.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lbi.mytestapplication.common.Status;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Route;

public class RouteDAO {
	
    @PersistenceContext(name="primary")
    EntityManager em;

	
	public void createRoute(Route r) {
		EndPoint source = getEndpointByName(r.getSource().getApplication());
		EndPoint dest = getEndpointByName(r.getDestination().getApplication());
		r.setSource(source);
		r.setDestination(dest);
        em.persist(r);
	}


	private EndPoint getEndpointByName(String application) {
		Query query = em.createQuery("SELECT e FROM EndPoint e WHERE e.application='" + application + "'");
		List<EndPoint> result = (List<EndPoint>)query.getResultList();
	    return result.get(0);
	}


	@SuppressWarnings("unchecked")
	public List<Route> getAllRoutes() {
		Query query = em.createQuery("SELECT r FROM Route r");
	    return (List<Route>) query.getResultList();
	}


	public Route getRouteById(long id) {
		Query query = em.createQuery("SELECT r FROM Route r where r.id = " + id);
	    return ((List<Route>) query.getResultList()).get(0);
	}


	public void setRouteStatus(Route r, Status status) {
		Route route = getRouteById(r.getId());
		route.setStatus(status);
        em.persist(route);
	}


}
