package com.lbi.mytestapplication.common;

import com.lbi.mytestapplication.domain.entity.Connector;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.domain.entity.Route;

public class EntityMapper {

	
    public static com.lbi.mytestapplication.rest.ressource.Route mapDomainEntityToRestResource(Route route){
    	com.lbi.mytestapplication.rest.ressource.Route routeRsc = new com.lbi.mytestapplication.rest.ressource.Route();
    	routeRsc.setId(route.getId());
    	routeRsc.setSource(mapDomainEntityToRestResource(route.getSource()));
    	routeRsc.setDestination(mapDomainEntityToRestResource(route.getDestination()));
    	routeRsc.setStatus(route.getStatus());
    	if(route.getRouteId() != null){
    		routeRsc.setRouteId(route.getRouteId());
    	} else{
    		String id = route.getSource().getUrl()+ route.getDestination().getUrl();
    		routeRsc.setRouteId(String.valueOf(id.hashCode()));
    	}
    	return routeRsc;
    }
    

    public static Route mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Route routeRsc){
    	Route route = new Route();
    	route.setId(routeRsc.getId());
    	route.setSource(mapRestResourceToDomainEntity(routeRsc.getSource()));
    	route.setDestination(mapRestResourceToDomainEntity(routeRsc.getDestination()));
    	route.setStatus(routeRsc.getStatus());
    	if(routeRsc.getRouteId() != null){
    		route.setRouteId(routeRsc.getRouteId());
    	} else{
    		String id = routeRsc.getSource().getUrl()+ routeRsc.getDestination().getUrl();
    		route.setRouteId(String.valueOf(id.hashCode()));
    	}
    	return route;
    }

    public static com.lbi.mytestapplication.rest.ressource.EndPoint mapDomainEntityToRestResource(EndPoint ep){
    	com.lbi.mytestapplication.rest.ressource.EndPoint epr = new com.lbi.mytestapplication.rest.ressource.EndPoint();
    	epr.setId(ep.getId());
    	epr.setApplication(ep.getApplication());
    	epr.setUrl(ep.getUrl());
    	return epr;
    }
    

    public static EndPoint mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.EndPoint epr){
    	EndPoint ep = new EndPoint();
    	ep.setId(epr.getId());
    	ep.setApplication(epr.getApplication());
    	ep.setUrl(epr.getUrl());
    	return ep;
    }


	public static com.lbi.mytestapplication.rest.ressource.Post mapDomainEntityToRestResource(Post post) {
		com.lbi.mytestapplication.rest.ressource.Post postRsc = new com.lbi.mytestapplication.rest.ressource.Post();
		postRsc.setMessage(post.getMessage());
		postRsc.setEndpoint(mapDomainEntityToRestResource(post.getEndpoint()));
		postRsc.setId(post.getId());
		return postRsc;
	}


	public static Post mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Post postRsc) {
		Post post = new Post();
		post.setEndpoint(mapRestResourceToDomainEntity(postRsc.getEndpoint()));
		post.setMessage(postRsc.getMessage());
		post.setId(postRsc.getId());
		return post;
	}


	public static Connector mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Connector connector) {
		Connector connect = new Connector();
		connect.setEndpoint(mapRestResourceToDomainEntity(connector.getEndpoint()));
		connect.setName(connector.getName());
		connect.setId(connector.getId());
		return connect;
	}


	public static com.lbi.mytestapplication.rest.ressource.Connector mapDomainEntityToRestResource(Connector c) {
		com.lbi.mytestapplication.rest.ressource.Connector connectRsc = new com.lbi.mytestapplication.rest.ressource.Connector();
		connectRsc.setName(c.getName());
		connectRsc.setEndpoint(mapDomainEntityToRestResource(c.getEndpoint()));
		connectRsc.setId(c.getId());
		return connectRsc;
	}

}
