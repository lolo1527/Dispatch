package com.lbi.mytestapplication.common;

import com.lbi.mytestapplication.domain.entity.Connector;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.domain.entity.Route;

public class EntityMapper {

	
    public static com.lbi.mytestapplication.rest.ressource.Route mapDomainEntityToRestResource(Route ep){
    	com.lbi.mytestapplication.rest.ressource.Route epr = new com.lbi.mytestapplication.rest.ressource.Route();
    	epr.setId(ep.getId());
    	epr.setSource(mapDomainEntityToRestResource(ep.getSource()));
    	epr.setDestination(mapDomainEntityToRestResource(ep.getDestination()));
    	epr.setStatus(ep.getStatus());
    	return epr;
    }
    

    public static Route mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Route epr){
    	Route ep = new Route();
    	ep.setId(epr.getId());
    	ep.setSource(mapRestResourceToDomainEntity(epr.getSource()));
    	ep.setDestination(mapRestResourceToDomainEntity(epr.getDestination()));
    	ep.setStatus(epr.getStatus());
    	return ep;
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
